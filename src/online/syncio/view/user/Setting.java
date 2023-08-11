package online.syncio.view.user;

import java.awt.Color;
import javax.swing.JPanel;
import online.syncio.config.Version;
import online.syncio.model.LoggedInUser;
import online.syncio.utils.OtherHelper;
import online.syncio.view.login.Login;

public class Setting extends JPanel {

    public Setting() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        lblVersion.setText("<html><b>Version</b>: " + Version.CURRENT_VERSION + "</html>");
        if(LoggedInUser.getCurrentUser() == null) {
            lblLogout.setVisible(false);
        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        lblSepratorLine = new javax.swing.JLabel();
        lblTitle = new online.syncio.component.MyLabel();
        lblLogout = new online.syncio.component.MyLabel();
        lblImage = new online.syncio.component.MyLabel();
        lblVersion = new online.syncio.component.MyLabel();
        lblInfo = new online.syncio.component.MyLabel();
        lblDescription = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        lblTitle.setText("Setting");

        lblLogout.setForeground(new java.awt.Color(237, 73, 86));
        lblLogout.setText("Logout");
        lblLogout.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N
        lblLogout.setFontBold(2);
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLogoutMousePressed(evt);
            }
        });

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/other/about_480px.png"))); // NOI18N

        lblVersion.setText("<html><b>Version</b>: 0.0.0</html>");

        lblInfo.setText("<html>\n  <p><strong>Developed by:</strong></p>\n  <ul>\n    <li>Nguyen Ngoc Thuy Duong (56duong@gmail.com)</li>\n    <li>Than Van Huy (thanvanhuyy@gmail.com)</li>\n    <li>Nguyen Xuan Sanh (conroitinhyeu02@gmail.com)</li>\n    <li>Nguyen Duong Quoc Thuan (quocthuan85082@gmail.com)</li>\n  </ul>\n  <br>\n  <p><strong>Website:</strong> https://syncio.online/</p>\n</html>");
        lblInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblDescription.setText("<html><b>Syncio</b> is a social media platform built with Java by a team of 4 developers as part of our \"Du an 1\" class assignment at FPT College. We aim to provide users with a seamless and interactive experience for connecting, sharing, and communicating.</html>");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSepratorLine, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                        .addComponent(lblInfo)
                        .addComponent(lblVersion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(584, Short.MAX_VALUE))
        );

        add(pnlMain, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMousePressed
        if (OtherHelper.getMainAdmin(this) != null && OtherHelper.getMainAdmin(this).isVisible()) {
            //admin
            LoggedInUser.logOut();
            OtherHelper.getMainAdmin(this).dispose();
            new Login().setVisible(true);
        } else if (LoggedInUser.getCurrentUser() != null) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyLabel lblDescription;
    private online.syncio.component.MyLabel lblImage;
    private online.syncio.component.MyLabel lblInfo;
    private online.syncio.component.MyLabel lblLogout;
    private javax.swing.JLabel lblSepratorLine;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyLabel lblVersion;
    private online.syncio.component.MyPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
