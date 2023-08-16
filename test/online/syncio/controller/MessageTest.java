package online.syncio.controller;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import online.syncio.component.SearchedCard;
import online.syncio.component.message.ChatBox;
import online.syncio.dao.ConversationDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.Conversation;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Message;
import online.syncio.model.User;
import online.syncio.view.login.Login;
import org.assertj.swing.edt.GuiActionRunner;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import static org.assertj.swing.timing.Pause.pause;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    FrameFixture window;
    UserDAO userDAO = MongoDBConnect.getUserDAO();
    ConversationDAO conversationDAO = MongoDBConnect.getConversationDAO();

    final String username = "thuanID";
    final String password = "1";
    User currentUser;
    String currentUserID;

    @Before
    public void setUp() {
        currentUser = userDAO.getByUsername(username);
        currentUserID = currentUser.getIdAsString();

        Login frame = GuiActionRunner.execute(() -> new Login());
        window = new FrameFixture(frame);
        window.show();
        window.textBox("txtUser").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.button("btnLogin").click();

        // Wait until the main frame becomes visible
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());

        pause(1000);
        mainWindow.button("message").click();
        pause(1000);
    }

    @Test
    public void shouldShowMessagedUsersList() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());

        JPanel pnlHistoryUserList = mainWindow.panel("userList").target();

        List<String> historyList = conversationDAO.getAllMessageHistory(currentUserID);

        int userCount = historyList.size();

        int compCount = 0;

        for (Component comp : pnlHistoryUserList.getComponents()) {
            if (comp instanceof SearchedCard) {
                compCount++;
            }
        }

        assertEquals(userCount, compCount);

        for (String stuff : historyList) {
            assertEquals(stuff, mainWindow.panel(stuff).target().getName());
        }
    }

    @Test
    public void shouldShowMessagedHistoryWithAUser() {
        List<String> historyList = conversationDAO.getAllMessageHistory(currentUserID);

        Collections.shuffle(Arrays.asList(historyList));

        int userCount = historyList.toArray().length;

        if (userCount != 0) {
            FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
            JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

            String conversationID = historyList.iterator().next();

            mainWindow.panel("userList").panel(conversationID).click();
            pause(1000);

            pnlChatArea.requireVisible();
            pnlChatArea.panel(conversationID).requireVisible();

            Conversation converstaion = conversationDAO.getByID(conversationID);

            int messageCount = converstaion.getMessagesHistory().size();

            int chatBoxCount = 0;

            Component[] compList = pnlChatArea.panel(conversationID).panel("body").target().getComponents();

            for (Component cmp : compList) {
                if (cmp instanceof ChatBox) {
                    chatBoxCount++;
                }
            }

            assertEquals(messageCount, chatBoxCount);
        }
    }

    @Test
    public void shouldMessageBeSent() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());

        List<String> historyList = conversationDAO.getAllMessageHistory(currentUserID);

        String conversationID = historyList.iterator().next();

        mainWindow.panel("userList").panel(conversationID).click();
        pause(1000);

        Conversation conversation = conversationDAO.getByID(conversationID);
        List<String> participants = conversation.getParticipants();
        participants.remove(LoggedInUser.getCurrentUser().getIdAsString());

        String messageContent = "This is a message to see if it can be sent to MongoDB!!!";
        Message m = new Message(participants.get(0), messageContent);

        boolean messageSent = conversationDAO.addMessage(conversation.getId(), m);

        assertEquals(true, messageSent);
    }

    @Test
    public void shouldShowMessagesAfterSending() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
        JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

        List<String> historyList = conversationDAO.getAllMessageHistory(currentUserID);

        String conversationID = historyList.iterator().next();

        mainWindow.panel("userList").panel(conversationID).click();
        pause(1000);

        Conversation conversation = conversationDAO.getByID(conversationID);

        String messageContent = "This is a message to see if it can be sent to MongoDB!!!";

        pnlChatArea.textBox("textMessage").enterText(messageContent);
        pnlChatArea.textBox("textMessage").pressAndReleaseKeys(KeyEvent.VK_ENTER);

        pause(3000);

        Component[] compList = pnlChatArea.panel(conversationID).panel("body").target().getComponents();
        Collections.reverse(Arrays.asList(compList));

        Component lastestMessage = compList[0];

        if (lastestMessage instanceof ChatBox chatBox) {
            Message msg = chatBox.getMessage();

            // Người gửi có phải là người đăng nhập
            assertEquals(currentUserID, msg.getSenderID());

            // Tin nhắn có trùng nội dùng
            assertEquals(messageContent, msg.getText());

            // Vị trí hiển thị tin nhắn có đúng
            assertEquals(ChatBox.BoxType.RIGHT, chatBox.getBoxType());
        }
    }

    @Test
    public void shouldShowMessagesAfterRecieving() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
        JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

        List<String> historyList = conversationDAO.getAllMessageHistory(currentUserID);
        String conversatonID = historyList.iterator().next();

        mainWindow.panel("userList").panel(conversatonID).click();
        pause(1000);

        Conversation conversation = conversationDAO.getByID(conversatonID);
        List<String> participants = conversation.getParticipants();
        participants.remove(LoggedInUser.getCurrentUser().getIdAsString());

        String messageContent = "This is a message to see if the logged in user has recieved this message!!!";
        Message testMessage = new Message(participants.get(0), messageContent);

        boolean messageSent = conversationDAO.addMessage(conversation.getId(), testMessage);

        assertEquals(true, messageSent);
        pause(3000);

        Component[] compList = pnlChatArea.panel(conversatonID).panel("body").target().getComponents();
        Collections.reverse(Arrays.asList(compList));

        Component lastestMessage = compList[0];

        if (lastestMessage instanceof ChatBox chatBox) {
            Message msg = chatBox.getMessage();

            // Người gửi có phải là người đang nhắn tin
            assertEquals(participants.get(0), msg.getSenderID());

            // Tin nhắn có trùng nội dung
            assertEquals(messageContent, msg.getText());

            // Vị trí hiển thị tin nhắn có đúng
            assertEquals(ChatBox.BoxType.LEFT, chatBox.getBoxType());
        }
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
