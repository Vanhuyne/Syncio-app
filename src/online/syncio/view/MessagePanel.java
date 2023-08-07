package online.syncio.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import online.syncio.component.SearchedUserCard;
import online.syncio.component.message.ChatArea;
import online.syncio.dao.MessageDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;

public class MessagePanel extends JPanel {

    private CardLayout cardLayout;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private MessageDAO messageDAO = MongoDBConnect.getMessageDAO();
    private Set<String> messageUserSet = new HashSet<>();

    public MessagePanel() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        pnlChatArea.setLayout(new CardLayout());
        cardLayout = (CardLayout) pnlChatArea.getLayout();

        pnlUserList.setLayout(new BoxLayout(pnlUserList, BoxLayout.Y_AXIS));
        addUserToHistoryPanel();
    }

    public void addUserToHistoryPanel() {
        pnlUserList.removeAll();

        messageUserSet = messageDAO.getMessagingUsers(LoggedInUser.getCurrentUser().getUsername());

        for (String username : messageUserSet) {
            MessagePanel.this.createCardForHistoryPanel(username);
        }

        pnlUserList.revalidate();
        pnlUserList.repaint();
    }

    public void createCardForHistoryPanel(String username) {
        User user = userDAO.getByUsername(username);
        SearchedUserCard card = new SearchedUserCard(user);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openMessage(card.getUser());
            }
        });

        pnlUserList.add(card);
        Box.createVerticalStrut(20);

        pnlUserList.revalidate();
        pnlUserList.repaint();
    }

    public void createCardForHistoryPanel(User user) {
        SearchedUserCard card = new SearchedUserCard(user);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openMessage(card.getUser());
            }
        });

        pnlUserList.add(card);
        Box.createVerticalStrut(20);

        pnlUserList.revalidate();
        pnlUserList.repaint();
    }

    public void openMessage(String messagingUsername) {
        Component[] componentList = pnlChatArea.getComponents();

        boolean found = false;

        User messagingUser = userDAO.getByUsername(messagingUsername);

        try {

            for (Component c : componentList) {
                if (c instanceof ChatArea
                        && c.getName().equalsIgnoreCase(messagingUser.getUsername())) {
                    cardLayout.show(pnlChatArea, messagingUser.getUsername().toLowerCase());
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

    public void openMessage(User messagingUser) {
        Component[] componentList = pnlChatArea.getComponents();

        boolean found = false;

        try {
            for (Component c : componentList) {
                if (c instanceof ChatArea
                        && c.getName().equalsIgnoreCase(messagingUser.getUsername())) {
                    cardLayout.show(pnlChatArea, messagingUser.getUsername().toLowerCase());
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

        pnlChatArea.add(ca, ca.getName().toLowerCase());
        cardLayout.show(pnlChatArea, messagingUser.getUsername().toLowerCase());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUsers = new javax.swing.JPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        pnlUserList = new javax.swing.JPanel();
        pnlChatArea = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 204, 0));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));

        pnlUsers.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(219, 219, 219)));

        scrollPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlUserList.setBackground(new java.awt.Color(255, 255, 255));
        pnlUserList.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        pnlUserList.setPreferredSize(new java.awt.Dimension(270, 726));
        pnlUserList.setLayout(new javax.swing.BoxLayout(pnlUserList, javax.swing.BoxLayout.LINE_AXIS));
        scrollPane.setViewportView(pnlUserList);

        javax.swing.GroupLayout pnlUsersLayout = new javax.swing.GroupLayout(pnlUsers);
        pnlUsers.setLayout(pnlUsersLayout);
        pnlUsersLayout.setHorizontalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        pnlUsersLayout.setVerticalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        pnlChatArea.setBackground(new java.awt.Color(255, 255, 255));
        pnlChatArea.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 279, Short.MAX_VALUE)
                    .addComponent(pnlChatArea, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 798, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlChatArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlChatArea;
    private javax.swing.JPanel pnlUserList;
    private javax.swing.JPanel pnlUsers;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
