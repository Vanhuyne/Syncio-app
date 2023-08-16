package online.syncio.controller.user;

import com.mongodb.client.ChangeStreamIterable;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import online.syncio.component.SearchedCard;
import online.syncio.component.message.ChatArea;
import online.syncio.dao.ConversationDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.Conversation;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.view.user.MessagePanel;

public class MessageController {

    private MessagePanel pnlMsg;
    private CardLayout cardLayout;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private ConversationDAO conversationDAO = MongoDBConnect.getConversationDAO();
    private List<String> historyList = new ArrayList<>();

    private HashMap<String, Component> chatAreas = new HashMap<>();

    public MessageController(MessagePanel pnlMsg) {
        this.pnlMsg = pnlMsg;
        cardLayout = (CardLayout) pnlMsg.getChatArea().getLayout();
    }

    public void recheckLoggedInUser() {
        if (LoggedInUser.isUser()) {
            addUserToHistoryPanel();
        } else {
            System.out.println("chưa đăng nhập");
        }
    }

    public void addUserToHistoryPanel() {
        pnlMsg.getPnlUserList().removeAll();

        if (!historyList.isEmpty()) {
            historyList.clear();
        }

        historyList = conversationDAO.getAllMessageHistory(LoggedInUser.getCurrentUser().getIdAsString());

        Thread thread = new Thread(() -> {
            for (String str : historyList) {
                Conversation con = conversationDAO.getByID(str);
                List<String> participants = con.getParticipants();
                participants.remove(LoggedInUser.getCurrentUser().getIdAsString());

                SwingUtilities.invokeLater(() -> {
                    if (participants.size() == 1) {
                        User user = userDAO.getByID(participants.get(0));
                        createCard(str, user);
                    }

                    if (participants.size() >= 2) {
                        createCard(str, null);
                    }
                });
            }

            pnlMsg.getPnlUserList().revalidate();
            pnlMsg.getPnlUserList().repaint();
        });
        thread.start();

        getConversationChangeStream();
        getUsersChangeStream();
    }

    private void createCard(String identifier, User user) {
        SearchedCard card = new SearchedCard();

        if (user == null) {
            card.setConversationID(identifier, null);
        } else {
            card.setConversationID(identifier, user);
        }

        card.setName(identifier);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openMessage(card.getConversationID());
            }
        });

        pnlMsg.getPnlUserList().add(card);
        pnlMsg.getPnlUserList().add(Box.createVerticalStrut(20));
    }

    public void openMessageFromProfie(String userID) {
        User user = userDAO.getByID(userID);

        String[] testParticipants = new String[]{LoggedInUser.getCurrentUser().getIdAsString(), user.getIdAsString()};

        Conversation con = conversationDAO.getByParticipants(Arrays.asList(testParticipants));

        if (con == null) {
            con = new Conversation(Arrays.asList(testParticipants), new ArrayList<>());
            conversationDAO.add(con);
        }
    }

    public void openMessage(String conversationID) {
        Component chatArea = chatAreas.get(conversationID);

        if (chatArea == null) {
            createChatArea(conversationID);
        } else {
            cardLayout.show(pnlMsg.getChatArea(), conversationID);
        }
    }

    public void createChatArea(String conversationID) {
        if (!historyList.contains(conversationID)) {
            Conversation con = conversationDAO.getByID(conversationID);
            List<String> participants = con.getParticipants();
            participants.remove(LoggedInUser.getCurrentUser().getIdAsString());

            if (participants.size() == 1) {
                User user = userDAO.getByID(participants.get(0));
                createCard(conversationID, user);
            }

            if (participants.size() >= 2) {
                createCard(conversationID, null);
            }

            historyList.add(conversationID);
        }

        if (!chatAreas.containsKey(conversationID)) {
            ChatArea ca = new ChatArea();
            ca.setConversationID(conversationID);
            ca.setName(conversationID);
            pnlMsg.getChatArea().add(ca, conversationID);

            chatAreas.put(conversationID, ca);

            System.out.println(ca.getName());
        }

        cardLayout.show(pnlMsg.getChatArea(), conversationID);
    }

    // Dùng để update giao diện, kiểm tra người dùng này có được người mới nhăn tin
    // hoặc được mời vào group chat
    public void getConversationChangeStream() {
        ChangeStreamIterable<Conversation> changeStream = conversationDAO.getChangeStream();

        Thread changeStreamThread = new Thread(() -> {
            changeStream.forEach(changeDocument -> {
                Conversation conversation = changeDocument.getFullDocument();

                List<String> participants = conversation.getParticipants();

                if (participants.contains(LoggedInUser.getCurrentUser().getIdAsString())) {
                    createChatArea(conversation.getIdAsString());
                }

                pnlMsg.getPnlUserList().revalidate();
                pnlMsg.getPnlUserList().repaint();
            });
        });

        changeStreamThread.start();
    }

    // Dùng để update giao diện, kiểm tra người dùng có thay đổi thông tin cá nhân
    public void getUsersChangeStream() {
        ChangeStreamIterable<User> changeStream = userDAO.getChangeStream();

        Thread changeStreamThread = new Thread(() -> {
            changeStream.forEach(changeDocument -> {
                User user = changeDocument.getFullDocument();

                if (user != null) {

                    String[] testParticipants = new String[]{LoggedInUser.getCurrentUser().getIdAsString(), user.getIdAsString()};

                    Conversation con = conversationDAO.getByParticipants(Arrays.asList(testParticipants));

                    if (con != null) {
                        addUserToHistoryPanel();
                    }

                }
            });
            pnlMsg.getPnlUserList().revalidate();
            pnlMsg.getPnlUserList().repaint();
        });

        changeStreamThread.start();
    }
}
