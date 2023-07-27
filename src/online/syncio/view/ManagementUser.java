package online.syncio.view;

import com.mongodb.client.FindIterable;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import online.syncio.component.ConnectionPanel;
import online.syncio.controller.ManagementUserController;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.User;
import online.syncio.utils.Export;

public class ManagementUser extends ConnectionPanel {
    private ManagementUserController controller;
    DefaultTableModel model;
    private UserDAO userDAO;
    private FindIterable<User> lUser;
    ManagementUser managementUser;
    
    public ManagementUser() {
        this.database = MongoDBConnect.getDatabase();
        this.userDAO = new UserDAOImpl(database);
        this.managementUser = this;
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        //table
        //tao model
        model = new DefaultTableModel();

        // Set the table model to the tblAlbum table
        tblUser.setModel(model);
        
        //disable table editing
        tblUser.setDefaultEditor(Object.class, null); 
        
        //table header
        String [] colNames = {"ID", "Email", "Username", "Following", "Post", "Date Created", "Role", "Status"};
        model.setColumnIdentifiers(colNames);

        //column width
        tblUser.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblUser.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblUser.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblUser.getColumnModel().getColumn(3).setPreferredWidth(10);
        tblUser.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblUser.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblUser.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblUser.getColumnModel().getColumn(7).setPreferredWidth(10);
        
        //data
        fillToTable();
        
        //Double click on Table
        tblUser.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                int row = tblUser.getSelectedRow();
                if (mouseEvent.getClickCount() == 2 && tblUser.getSelectedRow() != -1) {
                    new ManagementUserDetail(managementUser, userDAO.getByID(tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString())).setVisible(true);
                }
            };
        });
        
        this.controller = new ManagementUserController(this);
    }
    
    
    
    public FindIterable find() {
        String searchText = txtSearch.getText().trim();
        if(searchText.equalsIgnoreCase("Search by email or username") || searchText.isEmpty() || searchText == null) searchText = null;
        
        Integer role = null;
        if(cboRole.getSelectedItem().equals("User")) role = 0;
        else if(cboRole.getSelectedItem().equals("Admin")) role = 1;
        
        Integer status = null;
        if(cboStatus.getSelectedItem().equals("Active")) status = 0;
        else if(cboStatus.getSelectedItem().equals("Deactivated")) status = 1;
        
        return userDAO.getAllByUsernameOrEmailRoleFlag(searchText, role, status);
    }
    
    
    
    public void fillToTable() {
        model.setRowCount(0); //clear rows in the table
        
        lUser = find();

        //them tung dong vao
        if(lUser != null) {
            for(User user : lUser) {
                model.addRow(new Object[] {user.getId(), user.getEmail(), user.getUsername(), user.getFollowers().size(), userDAO.countPost(user.getId().toString()), user.getDateCreated(), user.getRole()== 0 ? "User" : "Admin", user.getFlag() == 0 ? "Active" : "Deactived"});
            }
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        myPanel1 = new online.syncio.component.MyPanel();
        pnlTitle = new online.syncio.component.MyPanel();
        lblTitle1 = new online.syncio.component.MyLabel();
        btnPrint = new online.syncio.component.MyButton();
        pnlMain = new online.syncio.component.MyPanel();
        txtSearch = new online.syncio.component.MyTextField();
        cboRole = new online.syncio.component.MyComboBoxSuggestionsAutocomplete();
        lblRole = new online.syncio.component.MyLabel();
        lblStatus = new online.syncio.component.MyLabel();
        cboStatus = new online.syncio.component.MyComboBoxSuggestionsAutocomplete();
        myScrollPane1 = new online.syncio.component.MyScrollPane();
        tblUser = new online.syncio.component.MyTable();
        btnNew = new online.syncio.component.MyButton();
        btnReset = new online.syncio.component.MyButton();

        setLayout(new java.awt.BorderLayout());

        pnlContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        myPanel1.setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTitle.setPreferredSize(new java.awt.Dimension(0, 40));

        lblTitle1.setText("User Management");

        btnPrint.setBackground(new java.awt.Color(254, 255, 255));
        btnPrint.setText("Export to Excel");
        btnPrint.setFont(new java.awt.Font("SF Pro Display Medium", 0, 13)); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 825, Short.MAX_VALUE)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        myPanel1.add(pnlTitle, java.awt.BorderLayout.PAGE_START);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setPlaceholder("Search by email or username");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cboRole.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(219, 219, 219)));
        cboRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "User", "Admin" }));
        cboRole.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboRoleItemStateChanged(evt);
            }
        });

        lblRole.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        lblRole.setText("Role:");

        lblStatus.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        lblStatus.setText("Status:");

        cboStatus.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(219, 219, 219)));
        cboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Active", "Deactivated" }));
        cboStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStatusItemStateChanged(evt);
            }
        });

        myScrollPane1.setViewportView(tblUser);

        btnNew.setBackground(new java.awt.Color(0, 149, 246));
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("+ New User");
        btnNew.setBorderColor(new java.awt.Color(0, 149, 246));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(254, 255, 255));
        btnReset.setText("Reset Filter");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboRole, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addComponent(myScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        myPanel1.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlContainer.add(myPanel1, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        fillToTable();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cboRoleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboRoleItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            fillToTable();
        }
    }//GEN-LAST:event_cboRoleItemStateChanged

    private void cboStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboStatusItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            fillToTable();
        }
    }//GEN-LAST:event_cboStatusItemStateChanged

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        new ManagementUserDetail(managementUser, null).setVisible(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtSearch.setText("");
        cboRole.setSelectedItem("All");
        cboStatus.setSelectedItem("All");
        fillToTable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Export.writeToExcell(tblUser, "users");
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnNew;
    private online.syncio.component.MyButton btnPrint;
    private online.syncio.component.MyButton btnReset;
    private online.syncio.component.MyComboBoxSuggestionsAutocomplete cboRole;
    private online.syncio.component.MyComboBoxSuggestionsAutocomplete cboStatus;
    private online.syncio.component.MyLabel lblRole;
    private online.syncio.component.MyLabel lblStatus;
    private online.syncio.component.MyLabel lblTitle1;
    private online.syncio.component.MyPanel myPanel1;
    private online.syncio.component.MyScrollPane myScrollPane1;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlTitle;
    private online.syncio.component.MyTable tblUser;
    private online.syncio.component.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
