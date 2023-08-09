package online.syncio.controller;

import com.mongodb.client.FindIterable;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import javax.swing.JPanel;
import online.syncio.component.message.ChatBox;
import online.syncio.dao.MessageDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.model.Message;
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
    MessageDAO msgDAO = MongoDBConnect.getMessageDAO();

    final String username = "thuanID";
    final String password = "1";

    @Before
    public void setUp() {
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

        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);

        int userCount = messagedUserSet.toArray().length;

        int compCount = pnlHistoryUserList.getComponentCount();

        assertEquals(userCount, compCount);

        for (String name : messagedUserSet) {
            assertEquals(name, mainWindow.panel(name).target().getName());
        }
    }

    @Test
    public void shouldShowMessagedHistoryWithAUser() {
        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);
        Collections.shuffle(Arrays.asList(messagedUserSet));

        int userCount = messagedUserSet.toArray().length;

        if (userCount != 0) {
            FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
            JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

            String messagingUser = messagedUserSet.iterator().next();

            mainWindow.panel("userList").panel(messagingUser).click();

            pause(1000);

            pnlChatArea.requireVisible();
            pnlChatArea.panel(messagingUser).requireVisible();

            FindIterable<Message> msgList = MongoDBConnect.getMessageDAO().findAllByTwoUsers(username, messagingUser);

            int messageCount = msgList.into(new ArrayList<>()).size();

            int chatBoxCount = 0;

            Component[] compList = pnlChatArea.panel(messagingUser).panel("body").target().getComponents();

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

        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);

        String messagingUser = messagedUserSet.iterator().next();

        mainWindow.panel("userList").panel(messagingUser).click();
        pause(1000);

        String messageContent = "This is a message to see if it can be sent to MongoDB!!!";
        Message m = new Message(username, messagingUser, messageContent);
        boolean messageSent = MongoDBConnect.getMessageDAO().add(m);

        assertEquals(true, messageSent);
    }

    @Test
    public void shouldShowMessagesAfterSending() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
        JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);

        String messagingUser = messagedUserSet.iterator().next();

        mainWindow.panel("userList").panel(messagingUser).click();
        pause(1000);

        String messageContent = "This is a message to test if the Logged In User's GUI is working properly!!!";

        pnlChatArea.textBox("textMessage").enterText(messageContent);
        pnlChatArea.textBox("textMessage").pressAndReleaseKeys(KeyEvent.VK_ENTER);

        pause(3000);

        Component[] compList = pnlChatArea.panel(messagingUser).panel("body").target().getComponents();
        Collections.reverse(Arrays.asList(compList));

        Component lastestMessage = compList[0];

        if (lastestMessage instanceof ChatBox chatBox) {
            Message msg = chatBox.getMessage();

            // Người gửi có phải là người đăng
            assertEquals(username, msg.getSender());

            // Người nhận có phải là người đang nhắn
            assertEquals(messagingUser, msg.getRecipient());

            // Tin nhắn có trùng nội dùng
            assertEquals(messageContent, msg.getMessage());

            // Vị trí hiển thị tin nhắn có đúng
            assertEquals(ChatBox.BoxType.RIGHT, chatBox.getBoxType());
        }
    }

    @Test
    public void shouldShowMessagesAfterRecieving() {
        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
        JPanelFixture pnlChatArea = mainWindow.panel("chatArea");

        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);

        String messagingUser = messagedUserSet.iterator().next();

        mainWindow.panel("userList").panel(messagingUser).click();
        pause(1000);

        String messageContent = "This is a message to see if the logged in user has recieved this message!!!";
        Message testMessage = new Message(messagingUser, username, messageContent);
        boolean messageSent = MongoDBConnect.getMessageDAO().add(testMessage);

        assertEquals(true, messageSent);
        pause(3000);

        Component[] compList = pnlChatArea.panel(messagingUser).panel("body").target().getComponents();
        Collections.reverse(Arrays.asList(compList));

        Component lastestMessage = compList[0];

        if (lastestMessage instanceof ChatBox chatBox) {
            Message msg = chatBox.getMessage();

            // Người gửi có phải là người đang
            assertEquals(messagingUser, msg.getSender());

            // Người nhận có phải là người đăng
            assertEquals(username, msg.getRecipient());

            // Tin nhắn có trùng nội dùng
            assertEquals(messageContent, msg.getMessage());

            // Vị trí hiển thị tin nhắn có đúng
            assertEquals(ChatBox.BoxType.LEFT, chatBox.getBoxType());
        }
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}
