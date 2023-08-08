package online.syncio.view.user;

import online.syncio.view.login.Login;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.utils.OtherHelper;
import online.syncio.utils.Validator;
import online.syncio.view.admin.MainAdmin;

public final class EditProfile extends JPanel {

    private User currentUser;
    private UserDAO userDAO;

    public EditProfile() {
        this.userDAO = MongoDBConnect.getUserDAO();
        this.currentUser = LoggedInUser.getCurrentUser();

        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        if (currentUser != null) {
            loadUserData();
        }
    }

    public void loadUserData() {
        lblAccount.setText(currentUser.getUsername());
        txtUsername.setText(currentUser.getUsername());
        txtPassword.setText(currentUser.getPassword());
        txtEmail.setText(currentUser.getEmail());

        try {
            txtBio.setText(currentUser.getBio());
        } catch (NullPointerException e) {
            txtBio.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        lblSepratorLine = new javax.swing.JLabel();
        lblAccount = new online.syncio.component.MyLabel();
        lblTitle = new online.syncio.component.MyLabel();
        lblSepratorLine1 = new javax.swing.JLabel();
        lblLogout = new online.syncio.component.MyLabel();
        lblUsername = new online.syncio.component.MyLabel();
        txtUsername = new online.syncio.component.MyTextField();
        lblChangeUsername = new online.syncio.component.MyLabel();
        lblPassword = new online.syncio.component.MyLabel();
        lblEmail = new online.syncio.component.MyLabel();
        txtEmail = new online.syncio.component.MyTextField();
        lblBio = new online.syncio.component.MyLabel();
        lblChangeBio = new online.syncio.component.MyLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBio = new online.syncio.component.MyTextArea();
        txtPassword = new online.syncio.component.MyPasswordField();
        lblPasswordQuestion = new online.syncio.component.MyLabel();
        lblEmailQuestion = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        lblAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile_24px.png"))); // NOI18N
        lblAccount.setText(" 56duong");
        lblAccount.setMaximumSize(new java.awt.Dimension(57, 54));
        lblAccount.setMinimumSize(new java.awt.Dimension(57, 54));
        lblAccount.setPreferredSize(new java.awt.Dimension(57, 54));

        lblTitle.setText("Edit Profile");

        lblSepratorLine1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine1.setPreferredSize(new java.awt.Dimension(2, 1));

        lblLogout.setForeground(new java.awt.Color(237, 73, 86));
        lblLogout.setText("Logout");
        lblLogout.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N
        lblLogout.setFontBold(2);
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLogoutMousePressed(evt);
            }
        });

        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username");

        txtUsername.setBackground(new java.awt.Color(239, 239, 239));
        txtUsername.setText("duong_user");
        txtUsername.setBorderColor(new java.awt.Color(239, 239, 239));

        lblChangeUsername.setForeground(new java.awt.Color(0, 149, 246));
        lblChangeUsername.setText("Change");
        lblChangeUsername.setFontBold(2);
        lblChangeUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblChangeUsernameMousePressed(evt);
            }
        });

        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword.setText("Password");

        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("Email");

        txtEmail.setEditable(false);
        txtEmail.setText("duongcontact@gmail.com");
        txtEmail.setBorderColor(new java.awt.Color(239, 239, 239));
        txtEmail.setEnabled(false);

        lblBio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBio.setText("Bio");

        lblChangeBio.setForeground(new java.awt.Color(0, 149, 246));
        lblChangeBio.setText("Change");
        lblChangeBio.setFontBold(2);
        lblChangeBio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblChangeBioMousePressed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        txtBio.setBackground(new java.awt.Color(239, 239, 239));
        txtBio.setColumns(20);
        txtBio.setRows(4);
        txtBio.setText("fancy bio\n");
        txtBio.setBorderColor(new java.awt.Color(239, 239, 239));
        jScrollPane1.setViewportView(txtBio);

        txtPassword.setEditable(false);
        txtPassword.setText("matKhauSieuCapVjpPr0");
        txtPassword.setBorderColor(new java.awt.Color(239, 239, 239));
        txtPassword.setEnabled(false);

        lblPasswordQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblPasswordQuestion.setToolTipText("Email cannot be updated");

        lblEmailQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblEmailQuestion.setToolTipText("Use \"Forgot Password\" for password updates");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSepratorLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 738, Short.MAX_VALUE)
                        .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addComponent(lblSepratorLine1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChangeUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChangeBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblPasswordQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblEmailQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(315, 315, 315))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(lblSepratorLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblChangeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblPasswordQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblEmailQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblChangeBio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(277, Short.MAX_VALUE))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMousePressed
        if(OtherHelper.getMainAdmin(this) != null && OtherHelper.getMainAdmin(this).isVisible()) {
            //admin
            LoggedInUser.logOut();
            OtherHelper.getMainAdmin(this).dispose();
            new Login().setVisible(true);
        }
        else if(LoggedInUser.getCurrentUser() != null) {
            //user
            if (OtherHelper.getMainFrame(this).getGOLBAL_DATE() != null) {
                OtherHelper.getMainFrame(this).getPnlNotifications().getController().writeDesiredDateTime(LoggedInUser.getCurrentUser().getId().toString(), OtherHelper.getMainFrame(this).getGOLBAL_DATE());
            }
            
            LoggedInUser.logOut();
            OtherHelper.deleteSessionValue("LOGGED_IN_USER");
            new Login().setVisible(true);
            Main.getInstance().dispose();
        }
    }//GEN-LAST:event_lblLogoutMousePressed

    private void lblChangeUsernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeUsernameMousePressed
        //validate
        String username = txtUsername.getText().trim();
        String errors = Validator.allowNumberTextUnderline((JTextField) txtUsername, "Username", username, false, "Username");
        if (!errors.equals("")) {
            GlassPanePopup.showPopup(new MyDialog("Error", errors), "dialog");
        } else {
            //update
            int result = userDAO.updateUsernameByEmail(username, txtEmail.getText());
            if (result <= 0) {
                GlassPanePopup.showPopup(new MyDialog("Error", "An error occurs when updating your Username"), "dialog");
            } else {
                GlassPanePopup.showPopup(new MyDialog("Update Successful", "Updated successfully. Reopen the app to see the change."), "dialog");
            }
        }
    }//GEN-LAST:event_lblChangeUsernameMousePressed

    private void lblChangeBioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeBioMousePressed
        //validate
        String bio = txtBio.getText().trim();

        //update
        int result = userDAO.updateBioByEmail(bio, txtEmail.getText());
        if (result <= 0) {
            GlassPanePopup.showPopup(new MyDialog("Error", "An error occurs when updating your Bio"), "dialog");
        } else {
            GlassPanePopup.showPopup(new MyDialog("Update Successful", "Your Bio has been updated successfully."), "dialog");
        }
    }//GEN-LAST:event_lblChangeBioMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private online.syncio.component.MyLabel lblAccount;
    private online.syncio.component.MyLabel lblBio;
    private online.syncio.component.MyLabel lblChangeBio;
    private online.syncio.component.MyLabel lblChangeUsername;
    private online.syncio.component.MyLabel lblEmail;
    private online.syncio.component.MyLabel lblEmailQuestion;
    private online.syncio.component.MyLabel lblLogout;
    private online.syncio.component.MyLabel lblPassword;
    private online.syncio.component.MyLabel lblPasswordQuestion;
    private javax.swing.JLabel lblSepratorLine;
    private javax.swing.JLabel lblSepratorLine1;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyLabel lblUsername;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyTextArea txtBio;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
