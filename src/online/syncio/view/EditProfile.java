package online.syncio.view;

import java.awt.Color;
import java.awt.Font;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.MyLabel;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.resources.fonts.MyFont;

public class EditProfile extends ConnectionPanel {

    private Font regularFont = new MyFont().getSFProDisplayRegular(), boldFont = new MyFont().getSFProDisplayBold();

    private MyLabel[] changeLabelList;
    private Main main;

    private User currentUser = LoggedInUser.getCurrentUser();

    public EditProfile() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        loadUserData();
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
        myLabel1 = new online.syncio.component.MyLabel();
        lblSepratorLine1 = new javax.swing.JLabel();
        lblLogout = new online.syncio.component.MyLabel();
        lblUsername = new online.syncio.component.MyLabel();
        txtUsername = new online.syncio.component.MyTextField();
        lblChangeUsername = new online.syncio.component.MyLabel();
        lblPassword = new online.syncio.component.MyLabel();
        lblChangePassword = new online.syncio.component.MyLabel();
        lblEmail = new online.syncio.component.MyLabel();
        txtEmail = new online.syncio.component.MyTextField();
        lblChangeEmail = new online.syncio.component.MyLabel();
        lblBio = new online.syncio.component.MyLabel();
        lblChangeBio = new online.syncio.component.MyLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBio = new online.syncio.component.MyTextArea();
        txtPassword = new online.syncio.component.MyPasswordField();

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

        myLabel1.setText("Edit Profile");

        lblSepratorLine1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine1.setPreferredSize(new java.awt.Dimension(2, 1));

        lblLogout.setForeground(new java.awt.Color(237, 73, 86));
        lblLogout.setText("Logout");
        lblLogout.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N
        lblLogout.setFontBold(2);

        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username");

        txtUsername.setBackground(new java.awt.Color(240, 243, 245));
        txtUsername.setText("duong_user");
        txtUsername.setBorderColor(new java.awt.Color(240, 243, 245));

        lblChangeUsername.setForeground(new java.awt.Color(0, 149, 246));
        lblChangeUsername.setText("Change");
        lblChangeUsername.setFontBold(2);

        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword.setText("Password");

        lblChangePassword.setForeground(new java.awt.Color(0, 149, 246));
        lblChangePassword.setText("Change");
        lblChangePassword.setFontBold(2);

        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("Email");

        txtEmail.setBackground(new java.awt.Color(240, 243, 245));
        txtEmail.setText("duongcontact@gmail.com");
        txtEmail.setBorderColor(new java.awt.Color(240, 243, 245));

        lblChangeEmail.setForeground(new java.awt.Color(0, 149, 246));
        lblChangeEmail.setText("Change");
        lblChangeEmail.setFontBold(2);

        lblBio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBio.setText("Bio");

        lblChangeBio.setForeground(new java.awt.Color(0, 149, 246));
        lblChangeBio.setText("Change");
        lblChangeBio.setFontBold(2);

        jScrollPane1.setBorder(null);

        txtBio.setBackground(new java.awt.Color(240, 243, 245));
        txtBio.setColumns(20);
        txtBio.setRows(4);
        txtBio.setText("fancy bio\n");
        txtBio.setBorderColor(new java.awt.Color(240, 243, 245));
        jScrollPane1.setViewportView(txtBio);

        txtPassword.setBackground(new java.awt.Color(240, 243, 245));
        txtPassword.setText("matKhauSieuCapVjpPr0");
        txtPassword.setBorderColor(new java.awt.Color(240, 243, 245));

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
                    .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addComponent(lblSepratorLine1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(lblBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChangeUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChangeEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChangeBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(315, 315, 315))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChangeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblChangeBio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(277, Short.MAX_VALUE))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private online.syncio.component.MyLabel lblAccount;
    private online.syncio.component.MyLabel lblBio;
    private online.syncio.component.MyLabel lblChangeBio;
    private online.syncio.component.MyLabel lblChangeEmail;
    private online.syncio.component.MyLabel lblChangePassword;
    private online.syncio.component.MyLabel lblChangeUsername;
    private online.syncio.component.MyLabel lblEmail;
    private online.syncio.component.MyLabel lblLogout;
    private online.syncio.component.MyLabel lblPassword;
    private javax.swing.JLabel lblSepratorLine;
    private javax.swing.JLabel lblSepratorLine1;
    private online.syncio.component.MyLabel lblUsername;
    private online.syncio.component.MyLabel myLabel1;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyTextArea txtBio;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
