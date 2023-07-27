package online.syncio.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.MyLabel;
import online.syncio.component.MyProfileFollowLabel;
import online.syncio.component.MyRoundLabel;
import online.syncio.component.ProfilePostPanel;
import online.syncio.controller.ProfileController;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.resources.fonts.MyFont;

public class OtherUserProfile extends ConnectionPanel {

    private final Font regularFont = new MyFont().getSFProDisplayRegular();

    private ProfileController profileController;

    public OtherUserProfile() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
    }

    @Override
    public void setConnection(Main main) {
        this.main = main;
        this.database = main.getDatabase();

        profileController = new ProfileController(this);
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    public MyRoundLabel getLblAvatar() {
        return lblAvatar;
    }

    public MyProfileFollowLabel getLblFollowerNum() {
        return lblFollowerNum;
    }

    public MyProfileFollowLabel getLblFollowingNum() {
        return lblFollowingNum;
    }

    public MyProfileFollowLabel getLblPostNum() {
        return lblPostNum;
    }

    public MyLabel getLblUsername() {
        return lblUsername;
    }

    public MyLabel getLblBio() {
        return lblBio;
    }

    public ProfilePostPanel getPnlProfilePost() {
        return pnlProfilePost;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        lblAvatar = new online.syncio.component.MyRoundLabel();
        btnFollow = new online.syncio.component.MyButton();
        lblSepratorLine = new javax.swing.JLabel();
        lblUsername = new online.syncio.component.MyLabel();
        lblPostNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowerNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowingNum = new online.syncio.component.MyProfileFollowLabel();
        btnMessage = new online.syncio.component.MyButton();
        lblBio = new online.syncio.component.MyLabel();
        lblPost = new online.syncio.component.MyLabel();
        pnlProfilePost = new online.syncio.component.ProfilePostPanel();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        btnFollow.setBackground(new java.awt.Color(0, 149, 246));
        btnFollow.setForeground(new java.awt.Color(255, 255, 255));
        btnFollow.setText("Follow");
        btnFollow.setBorderColor(new java.awt.Color(255, 255, 255));
        btnFollow.setRadius(10);

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        lblUsername.setText("adele");
        lblUsername.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N

        lblPostNum.setText("448 posts");

        lblFollowerNum.setText("53.6M followers");

        lblFollowingNum.setText("1 following");

        btnMessage.setBackground(new java.awt.Color(239, 239, 239));
        btnMessage.setText("Message");
        btnMessage.setBorderColor(new java.awt.Color(255, 255, 255));
        btnMessage.setRadius(10);

        lblBio.setText("adele.com");

        lblPost.setText("POSTS");
        lblPost.setFont(regularFont);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 518, Short.MAX_VALUE)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(518, 518, 518))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblPostNum, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(btnFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(btnMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSepratorLine, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                            .addComponent(pnlProfilePost, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnFollow, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(btnMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPostNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlProfilePost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Image adeleImage = new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/other/adele.png")).getImage();
        lblAvatar.setImage(adeleImage);

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnFollow;
    private online.syncio.component.MyButton btnMessage;
    private online.syncio.component.MyRoundLabel lblAvatar;
    private online.syncio.component.MyLabel lblBio;
    private online.syncio.component.MyProfileFollowLabel lblFollowerNum;
    private online.syncio.component.MyProfileFollowLabel lblFollowingNum;
    private online.syncio.component.MyLabel lblPost;
    private online.syncio.component.MyProfileFollowLabel lblPostNum;
    private javax.swing.JLabel lblSepratorLine;
    private online.syncio.component.MyLabel lblUsername;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.ProfilePostPanel pnlProfilePost;
    // End of variables declaration//GEN-END:variables
}
