package online.syncio.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import online.syncio.component.ConnectionPanel;
import online.syncio.model.User;
import online.syncio.resources.fonts.MyFont;

public class Profile extends ConnectionPanel {

    private Font regularFont = new MyFont().getSFProDisplayRegular(), boldFont = new MyFont().getSFProDisplayBold();

    public Profile() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        lblAvatar = new online.syncio.component.MyRoundLabel();
        btnEditProfile = new online.syncio.component.MyButton();
        lblSepratorLine = new javax.swing.JLabel();
        lblUsername = new online.syncio.component.MyLabel();
        lblPostNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowerNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowingNum = new online.syncio.component.MyProfileFollowLabel();
        lblPost = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        btnEditProfile.setBackground(new java.awt.Color(239, 239, 239));
        btnEditProfile.setText("Edit profile");
        btnEditProfile.setBorderColor(new java.awt.Color(255, 255, 255));
        btnEditProfile.setRadius(10);
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        lblUsername.setText("56duong");
        lblUsername.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N

        lblPostNum.setText("0 posts");
        lblPostNum.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N

        lblFollowerNum.setText("10 followers");
        lblFollowerNum.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N

        lblFollowingNum.setText("5 following");
        lblFollowingNum.setFont(new java.awt.Font("SF Pro Display Bold", 0, 14)); // NOI18N

        lblPost.setText("POSTS");
        lblPost.setFont(regularFont);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPostNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 518, Short.MAX_VALUE)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(518, 518, 518))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(btnEditProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPostNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(382, Short.MAX_VALUE))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        CardLayout c = (CardLayout) this.main.getPnlTabContent().getLayout();
        c.show(this.main.getPnlTabContent(), "editprofile");
    }//GEN-LAST:event_btnEditProfileActionPerformed

    public void loadUserData(User user) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnEditProfile;
    private online.syncio.component.MyRoundLabel lblAvatar;
    private online.syncio.component.MyProfileFollowLabel lblFollowerNum;
    private online.syncio.component.MyProfileFollowLabel lblFollowingNum;
    private online.syncio.component.MyLabel lblPost;
    private online.syncio.component.MyProfileFollowLabel lblPostNum;
    private javax.swing.JLabel lblSepratorLine;
    private online.syncio.component.MyLabel lblUsername;
    private online.syncio.component.MyPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
