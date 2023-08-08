package online.syncio.view.user;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import online.syncio.controller.user.MessageController;
import online.syncio.model.LoggedInUser;

public class MessagePanel extends JPanel {

    private CardLayout cardLayout;

    private MessageController controller;

    public MessagePanel() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        pnlChatArea.setLayout(new CardLayout());
        cardLayout = (CardLayout) pnlChatArea.getLayout();

        pnlUserList.setLayout(new BoxLayout(pnlUserList, BoxLayout.Y_AXIS));

        controller = new MessageController(this);

        if(LoggedInUser.getCurrentUser() != null) controller.addUserToHistoryPanel();
    }

    public JPanel getChatArea() {
        return pnlChatArea;
    }

    public JPanel getPnlUserList() {
        return pnlUserList;
    }

    public MessageController getController() {
        return controller;
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
