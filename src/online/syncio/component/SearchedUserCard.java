package online.syncio.component;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.ImageIcon;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.utils.ImageHelper;
import online.syncio.view.user.PostDetailUI;
import org.bson.types.Binary;

public class SearchedUserCard extends javax.swing.JPanel {

    private User user;
    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private PostDAO postDAO = MongoDBConnect.getPostDAO();

    public SearchedUserCard(User user) {
        this.user = user;

        initComponents();
        lblAvatar.setSize(60, 60);

        setPreferredSize(new Dimension(290, 90));
        setMaximumSize(new Dimension(290, 90));
        setMinimumSize(new Dimension(290, 90));

        ImageHelper.setAvatarToLabel(user, lblAvatar, 60);

        lblUsername.setText(user.getUsername());
        lblFollowers.setText(userDAO.getFollowerCount(user.getId().toString()) + " followers");

    }

    public SearchedUserCard(User user, Color backgroundColor) {

        this.user = user;

        initComponents();
        lblAvatar.setSize(60, 60);

        setPreferredSize(new Dimension(290, 90));
        setMaximumSize(new Dimension(290, 90));
        setMinimumSize(new Dimension(290, 90));
        setBackground(backgroundColor);

        lblAvatar.setBackground(backgroundColor);

        ImageHelper.setAvatarToLabel(user, lblAvatar, 60);

        lblUsername.setText(user.getUsername());
        lblFollowers.setText("");
    }

    //notification
    public SearchedUserCard(String postID, String notificationText, String dateTime) {

        initComponents();

        lblUsername.setFont(lblUsername.getFont().deriveFont(14f));
        lblFollowers.setFont(lblFollowers.getFont().deriveFont(10f));

        Post post = postDAO.getByID(postID);
        lblAvatar.setSize(60, 60);

        setPreferredSize(new Dimension(314, 90));
        setMaximumSize(new Dimension(314, 90));
        setMinimumSize(new Dimension(314, 90));

        ImageIcon resizeImg;
        List<Binary> photos = post.getPhotoList();

        if (!photos.isEmpty()) {
            resizeImg = new ImageIcon(ImageHelper.resizeImageToFit(ImageHelper.readBinaryAsBufferedImage(photos.get(0)), 60));
            lblAvatar.setIcon(resizeImg);
        } else {
            ImageHelper.setAvatarToLabel(user, lblAvatar, 60);
        }

        lblUsername.setText(notificationText);
        lblFollowers.setText(dateTime);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GlassPanePopup.showPopup(new PostDetailUI(postID), "postdetail");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAvatar = new online.syncio.component.MyLabel();
        lblUsername = new online.syncio.component.MyLabel();
        lblFollowers = new online.syncio.component.MyLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(314, 90));

        lblAvatar.setBackground(new java.awt.Color(255, 255, 255));
        lblAvatar.setPreferredSize(new java.awt.Dimension(60, 60));

        lblUsername.setText("adele");
        lblUsername.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N

        lblFollowers.setForeground(new java.awt.Color(115, 115, 115));
        lblFollowers.setText("53.6M followers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFollowers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFollowers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyLabel lblAvatar;
    private online.syncio.component.MyLabel lblFollowers;
    private online.syncio.component.MyLabel lblUsername;
    // End of variables declaration//GEN-END:variables
}
