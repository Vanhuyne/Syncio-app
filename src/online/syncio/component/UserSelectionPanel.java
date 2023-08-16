package online.syncio.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.Conversation;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;

public class UserSelectionPanel extends javax.swing.JPanel {

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private List<User> userList = new ArrayList<>();
    private List<String> selectedUserList = new ArrayList<>();

    private int selectedUsers = 0;

    public UserSelectionPanel() {
        initComponents();
        this.userList = userDAO.getAll();

        int me = 0;

        for (User user : userList) {
            if (user.getIdAsString().equalsIgnoreCase(LoggedInUser.getCurrentUser().getIdAsString())) {
                me = userList.indexOf(user);
                break;
            }
        }

        this.userList.remove(me);

        pnlResult.setLayout(new BoxLayout(pnlResult, BoxLayout.Y_AXIS));
        btnCreate.setEnabled(false);
        btnCreate.setBackground(new Color(219, 219, 219));

        selectedUserList.add(LoggedInUser.getCurrentUser().getIdAsString());

        loadAllUser();
    }

    public void loadAllUser() {
        MouseListener mouseEvent = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SearchedCard card = (SearchedCard) e.getSource();
                String userID = card.getUser().getIdAsString();

                Color background = card.getBackground();

                if (background.equals(new Color(219, 219, 219))) {
                    card.setBackground(new Color(255, 255, 255));
                    selectedUsers--;
                    selectedUserList.remove(userID);
                } else {
                    card.setBackground(new Color(219, 219, 219));
                    selectedUsers++;
                    selectedUserList.add(userID);
                }

                if (selectedUsers >= 2) {
                    btnCreate.setEnabled(true);
                    btnCreate.setBackground(new Color(0, 149, 246));
                } else {
                    btnCreate.setEnabled(false);
                    btnCreate.setBackground(new Color(219, 219, 219));
                }
            }
        };

        for (User user : userList) {
            SearchedCard card = new SearchedCard(user);
            card.setSize(new Dimension(290, 60));
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            card.addMouseListener(mouseEvent);

            pnlResult.add(card);
        }
        pnlResult.revalidate();
        pnlResult.repaint();
    }

    public void createGroupChat() {
        Conversation con = new Conversation(selectedUserList, new ArrayList<>());
        MongoDBConnect.getConversationDAO().add(con);
        GlassPanePopup.closePopup("userSelector");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new online.syncio.component.MyScrollPane();
        pnlResult = new javax.swing.JPanel();
        myLabel1 = new online.syncio.component.MyLabel();
        btnCreate = new online.syncio.component.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);

        pnlResult.setBackground(new java.awt.Color(255, 255, 255));
        pnlResult.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(pnlResult);

        myLabel1.setForeground(new java.awt.Color(0, 0, 0));
        myLabel1.setText("<html>Select 2 users to<br> create a group chat</html>");
        myLabel1.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N

        btnCreate.setBackground(new java.awt.Color(0, 149, 246));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("CREATE");
        btnCreate.setBorderThickness(0);
        btnCreate.setFocusable(false);
        btnCreate.setFont(new java.awt.Font("SF Pro Display Medium", 1, 16)); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(548, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 60, Short.MAX_VALUE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        createGroupChat();
    }//GEN-LAST:event_btnCreateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnCreate;
    private online.syncio.component.MyLabel myLabel1;
    private javax.swing.JPanel pnlResult;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
