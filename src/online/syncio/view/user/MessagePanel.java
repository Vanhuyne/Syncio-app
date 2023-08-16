package online.syncio.view.user;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.UserSelectionPanel;
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

        if (LoggedInUser.getCurrentUser() != null) {
            controller = new MessageController(this);
            controller.addUserToHistoryPanel();
        }
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
        btnCreateNewGroup = new javax.swing.JButton();
        pnlChatArea = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 204, 0));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        setPreferredSize(new java.awt.Dimension(1080, 679));

        pnlUsers.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(219, 219, 219)));

        scrollPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlUserList.setBackground(new java.awt.Color(255, 255, 255));
        pnlUserList.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        pnlUserList.setName("userList"); // NOI18N
        pnlUserList.setPreferredSize(new java.awt.Dimension(270, 726));
        pnlUserList.setLayout(new javax.swing.BoxLayout(pnlUserList, javax.swing.BoxLayout.LINE_AXIS));
        scrollPane.setViewportView(pnlUserList);

        btnCreateNewGroup.setBackground(new java.awt.Color(0, 149, 246));
        btnCreateNewGroup.setFont(new java.awt.Font("Fira Sans", 1, 16)); // NOI18N
        btnCreateNewGroup.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateNewGroup.setText("CREATE NEW GROUP CHAT");
        btnCreateNewGroup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateNewGroup.setFocusable(false);
        btnCreateNewGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUsersLayout = new javax.swing.GroupLayout(pnlUsers);
        pnlUsers.setLayout(pnlUsersLayout);
        pnlUsersLayout.setHorizontalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnCreateNewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlUsersLayout.setVerticalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateNewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlChatArea.setBackground(new java.awt.Color(255, 255, 255));
        pnlChatArea.setName("chatArea"); // NOI18N
        pnlChatArea.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 279, Short.MAX_VALUE)
                .addComponent(pnlChatArea, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 798, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlChatArea, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateNewGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewGroupActionPerformed
        GlassPanePopup.showPopup(new UserSelectionPanel(), "userSelector");
    }//GEN-LAST:event_btnCreateNewGroupActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateNewGroup;
    private javax.swing.JPanel pnlChatArea;
    private javax.swing.JPanel pnlUserList;
    private javax.swing.JPanel pnlUsers;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
