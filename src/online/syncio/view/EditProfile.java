package online.syncio.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import online.syncio.component.MyLabel;
import online.syncio.resources.fonts.MyFont;

public class EditProfile extends javax.swing.JPanel {

    private Font regularFont = new MyFont().getSFProDisplayRegular(), boldFont = new MyFont().getSFProDisplayBold();

    private MyLabel[] changeLabelList;
    private Main main;

    public EditProfile(Main main) {
        this.main = main;

        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        changeLabelList = new MyLabel[]{lblChangeUsername, lblChangePassword, lblChangeEmail, lblChangeBio};

        for (MyLabel lbl : changeLabelList) {
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    lbl.setFont(boldFont);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lbl.setFont(regularFont);
                }
            });
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

        lblAccount.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(15, 10, 15, 10)));
        lblAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile.png"))); // NOI18N
        lblAccount.setText(" 56duong");
        lblAccount.setMaximumSize(new java.awt.Dimension(57, 54));
        lblAccount.setMinimumSize(new java.awt.Dimension(57, 54));
        lblAccount.setPreferredSize(new java.awt.Dimension(57, 54));

        myLabel1.setText("Edit Profile");

        lblSepratorLine1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine1.setPreferredSize(new java.awt.Dimension(2, 1));

        lblLogout.setForeground(new java.awt.Color(255, 0, 0));
        lblLogout.setText("Logout");
        lblLogout.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N

        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setText("Username");

        txtUsername.setBackground(new java.awt.Color(240, 243, 245));
        txtUsername.setText("duong_user");

        lblChangeUsername.setForeground(new java.awt.Color(51, 204, 255));
        lblChangeUsername.setText("Change");

        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword.setText("Password");

        lblChangePassword.setForeground(new java.awt.Color(51, 204, 255));
        lblChangePassword.setText("Change");

        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("Email");

        txtEmail.setBackground(new java.awt.Color(240, 243, 245));
        txtEmail.setText("duongcontact@gmail.com");

        lblChangeEmail.setForeground(new java.awt.Color(51, 204, 255));
        lblChangeEmail.setText("Change");
        lblChangeEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChangeEmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblChangeEmailMouseExited(evt);
            }
        });

        lblBio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBio.setText("Bio");

        lblChangeBio.setForeground(new java.awt.Color(51, 204, 255));
        lblChangeBio.setText("Change");
        lblChangeBio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblChangeBioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblChangeBioMouseExited(evt);
            }
        });

        txtBio.setColumns(20);
        txtBio.setRows(5);
        txtBio.setText("fancy bio\n");
        jScrollPane1.setViewportView(txtBio);

        txtPassword.setBackground(new java.awt.Color(240, 243, 245));
        txtPassword.setText("matKhauSieuCapVjpPr0");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 712, Short.MAX_VALUE)
                        .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addComponent(lblSepratorLine1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(lblBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
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

    private void lblChangeEmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeEmailMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblChangeEmailMouseEntered

    private void lblChangeEmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeEmailMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblChangeEmailMouseExited

    private void lblChangeBioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeBioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblChangeBioMouseEntered

    private void lblChangeBioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangeBioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblChangeBioMouseExited

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
