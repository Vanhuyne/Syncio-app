package online.syncio.controller.user;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Box;
import online.syncio.component.SearchedUserCard;
import online.syncio.component.message.ChatArea;
import online.syncio.dao.MessageDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.view.user.MessagePanel;

public class MessageController {

    private MessagePanel pnlMsg;
    private CardLayout cardLayout;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private MessageDAO messageDAO = MongoDBConnect.getMessageDAO();
    private Set<String> messageUserSet = new HashSet<>();

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

        messageUserSet = messageDAO.getMessagingUsers(LoggedInUser.getCurrentUser().getUsername());

        for (String username : messageUserSet) {
            createCardForHistoryPanel(username);
        }

        pnlMsg.getPnlUserList().revalidate();
        pnlMsg.getPnlUserList().repaint();
    }

    public void createCardForHistoryPanel(String username) {
        User user = userDAO.getByUsername(username);

        SearchedUserCard card = new SearchedUserCard(user);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openMessage(card.getUser().getUsername().trim());
            }
        });

        pnlMsg.getPnlUserList().add(card);
        Box.createVerticalStrut(20);

        pnlMsg.getPnlUserList().revalidate();
        pnlMsg.getPnlUserList().repaint();
    }

    public void openMessage(String messagingUsername) {
        Component[] componentList = pnlMsg.getChatArea().getComponents();

        boolean found = false;

        User messagingUser = userDAO.getByUsername(messagingUsername);

        try {
            for (Component c : componentList) {
                if (c instanceof ChatArea
                        && c.getName().equalsIgnoreCase(messagingUser.getUsername())) {
                    cardLayout.show(pnlMsg.getChatArea(), messagingUser.getUsername().toLowerCase());
                    found = true;

                    break;
                }
            }
        } catch (Exception e) {
            createMessage(messagingUser);
            found = true;
        }

        if (!found) {
            createMessage(messagingUser);
        }
    }

    public void createMessage(User messagingUser) {
        ChatArea ca = new ChatArea();
        ca.setMessagingUser(messagingUser);

        pnlMsg.getChatArea().add(ca, ca.getName().toLowerCase());
        cardLayout.show(pnlMsg.getChatArea(), messagingUser.getUsername().toLowerCase());
    }
}
