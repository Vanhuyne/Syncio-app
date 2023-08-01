package online.syncio.view;

import com.mongodb.client.ChangeStreamIterable;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import online.syncio.component.ConnectionPanel;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.utils.ImageHelper;

public class Profile extends ConnectionPanel {

    private Image defaultImage = new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/avt_128px.png")).getImage();
    private UserDAO userDAO;
    private PostDAO postDAO;
    List<Post> posts;
    private User user;

    public Profile(User user) {
        MongoDBConnect.connect();
        this.userDAO = MongoDBConnect.getUserDAO();
        this.postDAO = MongoDBConnect.getPostDAO();
        this.user = user;
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        loadProfile(user);
    }
    
    
    
    public void loadProfile(User user) {
        this.user = user;
        this.posts = postDAO.getAllByUserID(user.getId().toString());
        
        ArrayList<UserIDAndDate> following = new ArrayList<>();
        following = user.getFollowing();
         
        if(user.getId().toString().equals(LoggedInUser.getCurrentUser().getId().toString())) {
            // own profile
            user = LoggedInUser.getCurrentUser();
            btnEditProfileMessage.setText("Edit profile");
            btnFollow.setVisible(false);
            
            lblAvatar.setSize(128, 128);
            ImageIcon resizeImg = ImageHelper.resizing(defaultImage, lblAvatar.getWidth(), lblAvatar.getHeight());
            lblAvatar.setIcon(ImageHelper.toRoundImage(resizeImg, lblAvatar.getWidth()));
        }
        else {
            // another user profile
            btnEditProfileMessage.setText("Message");
            btnFollow.setVisible(true);
            
            boolean f = false;
            for(UserIDAndDate u : following) {
                if(u.getUserID().equals(user.getId().toString())) {
                    btnFollow.setText("Unfollow");
                    f = true;
                    break;
                }
            }
            if(!f) btnFollow.setText("Follow");
        }
        
        String username = user.getUsername();
        String bio = user.getBio();
        int postNum = posts.size();

        int followersSize = following.size();

        lblUsername.setText(username);
        lblBio.setText(bio);
        lblPostNum.setText(postNum + " posts");
        lblFollowingNum.setText(followersSize + " following");
        
        pnlProfilePost.setUserPosts(posts);
        
        Thread thread = new Thread(() -> {
            // Set up MongoDB change stream for the user's posts
            ChangeStreamIterable<Post> changeStream = postDAO.getChangeStream();

            // Listen for change stream events in a separate thread
            Thread changeStreamThread = new Thread(() -> {
                changeStream.forEach(changeDocument -> {
                    // Handle the change event here
                    // For example, extract the updated post data from changeDocument and update pnlProfilePost
                    Post updatedPost = changeDocument.getFullDocument();
                    if (updatedPost != null && updatedPost.getUserID().equals(this.user.getId().toString())) {
                        SwingUtilities.invokeLater(() -> {
                            // Update the user's posts UI
                            pnlProfilePost.addUserPost(updatedPost);
                        });
                    }
                });
            });
            changeStreamThread.start();

            // Load initial posts for the user from the regular database query
            SwingUtilities.invokeLater(() -> {
                pnlProfilePost.setUserPosts(posts);
            });

            // Wait for the change stream thread to finish (you can use other synchronization mechanisms if needed)
            try {
                changeStreamThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the thread
        thread.start();
    }
    
    
    
    public void toggleFollow() {
        if(btnFollow.getText().equalsIgnoreCase("follow")) {
            btnFollow.setText("Unfollow");
        }
        else {
            btnFollow.setText("Follow");
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new online.syncio.component.MyScrollPane();
        pnlMain = new online.syncio.component.MyPanel();
        btnEditProfileMessage = new online.syncio.component.MyButton();
        lblSepratorLine = new javax.swing.JLabel();
        lblUsername = new online.syncio.component.MyLabel();
        lblPostNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowerNum = new online.syncio.component.MyProfileFollowLabel();
        lblFollowingNum = new online.syncio.component.MyProfileFollowLabel();
        lblPost = new online.syncio.component.MyLabel();
        pnlProfilePost = new online.syncio.component.ProfilePostPanel();
        lblBio = new online.syncio.component.MyLabel();
        lblAvatar = new online.syncio.component.MyLabel();
        btnFollow = new online.syncio.component.MyButton();

        setLayout(new java.awt.BorderLayout());

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMaximumSize(new java.awt.Dimension(1080, 679));
        scrollPane.setMinimumSize(new java.awt.Dimension(1080, 679));
        scrollPane.setPreferredSize(new java.awt.Dimension(1080, 679));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        btnEditProfileMessage.setBackground(new java.awt.Color(239, 239, 239));
        btnEditProfileMessage.setText("Edit profile");
        btnEditProfileMessage.setBorderColor(new java.awt.Color(239, 239, 239));
        btnEditProfileMessage.setRadius(10);
        btnEditProfileMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileMessageActionPerformed(evt);
            }
        });

        lblSepratorLine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 228, 228), 2));
        lblSepratorLine.setPreferredSize(new java.awt.Dimension(2, 1));

        lblUsername.setText("56duong");
        lblUsername.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N

        lblPostNum.setText("0 posts");

        lblFollowerNum.setText("0 followers");

        lblFollowingNum.setText("5 following");

        lblPost.setText("POSTS");

        pnlProfilePost.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfilePost.setOpaque(true);

        lblBio.setText("adele.com");

        lblAvatar.setOpaque(true);

        btnFollow.setBackground(new java.awt.Color(0, 149, 246));
        btnFollow.setForeground(new java.awt.Color(255, 255, 255));
        btnFollow.setText("Follow");
        btnFollow.setBorderColor(new java.awt.Color(0, 149, 246));
        btnFollow.setRadius(10);
        btnFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFollowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPostNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditProfileMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFollow, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSepratorLine, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                            .addComponent(pnlProfilePost, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(518, 518, 518))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnEditProfileMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnFollow, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPostNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFollowingNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(lblSepratorLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlProfilePost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        scrollPane.setViewportView(pnlMain);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditProfileMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileMessageActionPerformed
        if(btnEditProfileMessage.getText().equalsIgnoreCase("edit profile")) {
            CardLayout c = (CardLayout) this.main.getPnlTabContent().getLayout();
            c.show(this.main.getPnlTabContent(), "editprofile");
        }
    }//GEN-LAST:event_btnEditProfileMessageActionPerformed

    private void btnFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFollowActionPerformed
        int result = userDAO.toggleFollow(LoggedInUser.getCurrentUser().getId().toString(), user.getId().toString());
        if(result > 0) {
            toggleFollow();
        }
        else {
            System.out.println(result);
        }
    }//GEN-LAST:event_btnFollowActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnEditProfileMessage;
    private online.syncio.component.MyButton btnFollow;
    private online.syncio.component.MyLabel lblAvatar;
    private online.syncio.component.MyLabel lblBio;
    private online.syncio.component.MyProfileFollowLabel lblFollowerNum;
    private online.syncio.component.MyProfileFollowLabel lblFollowingNum;
    private online.syncio.component.MyLabel lblPost;
    private online.syncio.component.MyProfileFollowLabel lblPostNum;
    private javax.swing.JLabel lblSepratorLine;
    private online.syncio.component.MyLabel lblUsername;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.ProfilePostPanel pnlProfilePost;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
