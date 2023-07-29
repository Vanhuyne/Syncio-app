package online.syncio.view;

import com.mongodb.client.FindIterable;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.Box;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.SearchedUserCard;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.User;

public class SearchUserPanel extends ConnectionPanel {

    private UserDAO userDAO;
    private FindIterable<User> userList;

    public SearchUserPanel() {
        initComponents();
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        setOpaque(true);

        pnlResult.setLayout(new BoxLayout(pnlResult, BoxLayout.Y_AXIS));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    @Override
    public void setConnection(Main main) {
        this.main = main;
        this.database = main.getDatabase();
        userDAO = new UserDAOImpl(database);
    }

    public void find() {
        String searchText = txtSearch.getText().trim();

        if (searchText.equalsIgnoreCase("Looking for someone?") || searchText.isBlank()) {
            searchText = null;
        }

        userList = userDAO.getAllByUsernameOrEmailRoleFlag(searchText, 0, 0);

        if (userList != null) {
            loadResult();
        }
    }

    private void loadResult() {
        pnlResult.removeAll();
        Box.createVerticalStrut(20);

        for (User user : userList) {

            pnlResult.add(new SearchedUserCard(user));
            Box.createVerticalStrut(20);

            pnlResult.revalidate();
            pnlResult.repaint();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        txtSearch = new online.syncio.component.MyTextField();
        lblSepratorLine = new javax.swing.JLabel();
        scrollPane = new online.syncio.component.MyScrollPane();
        pnlResult = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 219, 219), 2));

        txtSearch.setBackground(new java.awt.Color(239, 239, 239));
        txtSearch.setOpaque(true);
        txtSearch.setPlaceholder("Looking for someone?");
        txtSearch.setPreferredSize(new java.awt.Dimension(260, 45));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setOpaque(true);
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));

        pnlResult.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(pnlResult);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(lblSepratorLine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(584, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                    .addContainerGap(119, Short.MAX_VALUE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        find();
    }//GEN-LAST:event_txtSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSepratorLine;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlResult;
    private online.syncio.component.MyScrollPane scrollPane;
    private online.syncio.component.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
