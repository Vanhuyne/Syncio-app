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

/**
 * Controller class for managing user messaging functionality.
 */
public class MessageController {

    private MessagePanel pnlMsg;
    private CardLayout cardLayout;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private MessageDAO messageDAO = MongoDBConnect.getMessageDAO();
    private Set<String> messageUserSet = new HashSet<>();

    /**
     * Constructs a MessageController with the provided MessagePanel.
     *
     * @param pnlMsg The MessagePanel instance.
     */
    public MessageController(MessagePanel pnlMsg) {
        this.pnlMsg = pnlMsg;

        cardLayout = (CardLayout) pnlMsg.getChatArea().getLayout();
    }

    /**
     * Checks if a user is logged in and adds them to the history panel.
     */
    public void recheckLoggedInUser() {
        if (LoggedInUser.isUser()) {
            addUserToHistoryPanel();
        } else {
            System.out.println("chưa đăng nhập");
        }
    }

    /**
     * Adds messaging users to the history panel.
     */
    public void addUserToHistoryPanel() {
        pnlMsg.getPnlUserList().removeAll();

        messageUserSet = messageDAO.getMessagingUsers(LoggedInUser.getCurrentUser().getUsername());

        for (String username : messageUserSet) {
            createCardForHistoryPanel(username);
        }

        pnlMsg.getPnlUserList().revalidate();
        pnlMsg.getPnlUserList().repaint();
    }

    /**
     * Creates a card for the history panel with the given username.
     *
     * @param username The username to create the card for.
     */
    public void createCardForHistoryPanel(String username) {
        User user = userDAO.getByUsername(username);

        SearchedUserCard card = new SearchedUserCard(user);
        card.setName(username.trim());

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

    /**
     * Opens a message chat with the given messaging username.
     *
     * @param messagingUsername The username of the messaging user.
     */
    public void openMessage(String messagingUsername) {
        Component[] componentList = pnlMsg.getChatArea().getComponents();

        boolean found = false;

        User messagingUser = userDAO.getByUsername(messagingUsername);

        try {
            for (Component c : componentList) {
                if (c instanceof ChatArea
                        && c.getName().equalsIgnoreCase(messagingUser.getUsername())) {
                    cardLayout.show(pnlMsg.getChatArea(), messagingUser.getUsername());
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

    /**
     * Creates a message chat area for the given messaging user.
     *
     * @param messagingUser The messaging user.
     */
    public void createMessage(User messagingUser) {
        String username = messagingUser.getUsername();

        if (!messageUserSet.contains(username)) {
            createCardForHistoryPanel(username);
        }

        ChatArea ca = new ChatArea();
        ca.setMessagingUser(messagingUser);
        ca.setName(username.trim());

        pnlMsg.getChatArea().add(ca, ca.getName());
        cardLayout.show(pnlMsg.getChatArea(), username);
    }
}
