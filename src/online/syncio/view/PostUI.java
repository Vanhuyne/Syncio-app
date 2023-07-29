package online.syncio.view;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyLabel;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.utils.ImageFilter;
import online.syncio.utils.ImageHelper;
import online.syncio.utils.TimeHelper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class PostUI extends javax.swing.JPanel {

    private MongoDatabase database = MongoDBConnect.getDatabase();
    private PostDAO postDAO = new PostDAOImpl(database);
    private UserDAO userDAO = new UserDAOImpl(database);
    private String userID;
    private String postID;
    private Post post;
    private int totalLike;
    private int imageIndex = 0;

    ImageIcon liked = new ImageIcon();
    ImageIcon unliked = new ImageIcon();

    public PostUI(String postID, String userID) {
        this.userID = userID;
        this.postID = postID;
        post = postDAO.getByID(postID);
        initComponents();
        
        try {
            liked = new ImageIcon(ImageIO.read(getClass().getResource("/online/syncio/resources/images/icons/heart-red_24px.png")));
            unliked = new ImageIcon(ImageIO.read(getClass().getResource("/online/syncio/resources/images/icons/heart-white_24px.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        showInfoPost(postID);
    }
    
    
    
    public boolean isLiked() {
        // Check if any documents matched the condition
        if (post.getLikeList().stream().anyMatch(entry -> entry.getUserID().equals(userID))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
    public void updateLike() {
        if(isLiked()) {
            lblHeart.setIcon(unliked);
            postDAO.removeLike(postID, userID);
        }
        else {
            post.getLikeList().add(new UserIDAndDate(userID));
            lblHeart.setIcon(liked);
            postDAO.addLike(postID, userID);
        }
        
        post = postDAO.getByID(postID);
        lblTotalLike.setText(post.getLikeList().size()+" likes");
    }

    
    
    private void showInfoPost(String postID) {
        lblUsername.setText(userDAO.getByID(post.getUserID()).getUsername());
        lblUsername2.setText(userDAO.getByID(post.getUserID()).getUsername());
        lblDateCreated.setText(post.getDatePosted());
        txtCaption.setText(post.getCaption());
        lblTotalLike.setText(post.getLikeList().size()+" likes");
        if (isLiked()) {
            lblHeart.setIcon(liked);
        }
        
        //raito
        pnlImages.setSize(400, 400);
        if (post.getPhotoList().size() > 0) {
            if(post.getPhotoList().size() <= 1) {
                btnNext.setVisible(false);
                btnPrev.setVisible(false);
            }
            
            imageIndex = 0;
            pnlImages.setImg(ImageHelper.readBinaryAsBufferedImage(post.getPhotoList().get(imageIndex)));
            int imgHeight = pnlImages.getImgHeight();

            if (imgHeight > 300) {
                pnlImages.setPreferredSize(new Dimension(400, 400));
            } else if (imgHeight > 300) {
                pnlImages.setPreferredSize(new Dimension(400, 300));
            } else if (imgHeight > 200) {
                pnlImages.setPreferredSize(new Dimension(400, 200));
            } else {
                pnlImages.setPreferredSize(new Dimension(400, 100));
            }
        }
        else {
            pnlImages.setPreferredSize(new Dimension(0, 0));
            pnlImages.setImg("");
        }
    }
    
    
    
    public void selectImage(int i) {
//        Post post = postDAO.getByID(postID);
        if (i >= 0 && i < post.getPhotoList().size()) {
            imageIndex = i;
            lblCountImage.setText(imageIndex + 1 + "/" + post.getPhotoList().size());
            pnlImages.setImg(ImageHelper.readBinaryAsBufferedImage(post.getPhotoList().get(i)));
            pnlImages.revalidate();
            pnlImages.repaint();
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        lblDateCreated = new javax.swing.JLabel();
        lblHeart = new online.syncio.component.MyLabel();
        lblComment = new online.syncio.component.MyLabel();
        lblTotalLike = new javax.swing.JLabel();
        lblUsername2 = new javax.swing.JLabel();
        lblViewAllCmt = new javax.swing.JLabel();
        pnlImages = new online.syncio.component.MyPanel();
        btnNext = new online.syncio.component.MyButton();
        btnPrev = new online.syncio.component.MyButton();
        lblCountImage = new online.syncio.component.MyLabel();
        txtCaption = new online.syncio.component.MyTextPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        setMaximumSize(new java.awt.Dimension(400, 32767));
        setMinimumSize(new java.awt.Dimension(400, 0));

        lblUsername.setFont(new java.awt.Font("SF Pro Display", 1, 14)); // NOI18N
        lblUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile_24px.png"))); // NOI18N
        lblUsername.setText(" sanhvc");
        lblUsername.setToolTipText("");

        lblDateCreated.setFont(new java.awt.Font("SF Pro Display", 0, 12)); // NOI18N
        lblDateCreated.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDateCreated.setText("02:42 05/07/2023");

        lblHeart.setForeground(new java.awt.Color(255, 0, 0));
        lblHeart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/heart-white_24px.png"))); // NOI18N
        lblHeart.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblHeart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHeart.setMaximumSize(new java.awt.Dimension(20, 49));
        lblHeart.setMinimumSize(new java.awt.Dimension(20, 49));
        lblHeart.setPreferredSize(new java.awt.Dimension(20, 49));
        lblHeart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHeartMousePressed(evt);
            }
        });

        lblComment.setForeground(new java.awt.Color(255, 0, 0));
        lblComment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/comment_24px.png"))); // NOI18N
        lblComment.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblComment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblComment.setMaximumSize(new java.awt.Dimension(20, 49));
        lblComment.setMinimumSize(new java.awt.Dimension(20, 49));
        lblComment.setPreferredSize(new java.awt.Dimension(20, 49));

        lblTotalLike.setFont(new java.awt.Font("SF Pro Display", 0, 14)); // NOI18N
        lblTotalLike.setText("0 likes");

        lblUsername2.setFont(new java.awt.Font("SF Pro Display", 1, 14)); // NOI18N
        lblUsername2.setText("sanhvc");

        lblViewAllCmt.setFont(new java.awt.Font("SF Pro Display", 0, 14)); // NOI18N
        lblViewAllCmt.setForeground(new java.awt.Color(102, 102, 102));
        lblViewAllCmt.setText("View all comments");
        lblViewAllCmt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewAllCmtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblViewAllCmtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblViewAllCmtMouseExited(evt);
            }
        });

        pnlImages.setMaximumSize(new java.awt.Dimension(400, 400));
        pnlImages.setMinimumSize(new java.awt.Dimension(400, 400));
        pnlImages.setPreferredSize(new java.awt.Dimension(400, 400));

        btnNext.setBackground(null);
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/next_24px.png"))); // NOI18N
        btnNext.setBorderThickness(0);
        btnNext.setRadius(24);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setBackground(null);
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/previous_24px.png"))); // NOI18N
        btnPrev.setBorderThickness(0);
        btnPrev.setRadius(24);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblCountImage.setBackground(new Color(0f, 0f, 0f, 0f));
        lblCountImage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblCountImage.setForeground(new java.awt.Color(219, 219, 219));
        lblCountImage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlImagesLayout = new javax.swing.GroupLayout(pnlImages);
        pnlImages.setLayout(pnlImagesLayout);
        pnlImagesLayout.setHorizontalGroup(
            pnlImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlImagesLayout.createSequentialGroup()
                .addGroup(pnlImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlImagesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlImagesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        pnlImagesLayout.setVerticalGroup(
            pnlImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImagesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addGroup(pnlImagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        txtCaption.setEditable(false);
        txtCaption.setBackground(new java.awt.Color(255, 255, 255));
        txtCaption.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 0, 1, 1));
        txtCaption.setBorderThickness(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(lblDateCreated))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHeart, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblComment, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalLike, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblViewAllCmt))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lblUsername2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtCaption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(lblDateCreated))
                .addGap(10, 10, 10)
                .addComponent(pnlImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeart, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComment, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lblTotalLike)
                .addGap(10, 10, 10)
                .addComponent(lblUsername2)
                .addGap(5, 5, 5)
                .addComponent(txtCaption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(lblViewAllCmt)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblViewAllCmtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewAllCmtMouseEntered
        lblViewAllCmt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblViewAllCmtMouseEntered

    private void lblViewAllCmtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewAllCmtMouseExited
        lblViewAllCmt.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_lblViewAllCmtMouseExited

    private void lblViewAllCmtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewAllCmtMouseClicked
        GlassPanePopup.showPopup(new PostDetailUI(), "postdetail");
    }//GEN-LAST:event_lblViewAllCmtMouseClicked

    private void lblHeartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeartMousePressed
        updateLike();
    }//GEN-LAST:event_lblHeartMousePressed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        selectImage(imageIndex + 1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        selectImage(imageIndex - 1);
    }//GEN-LAST:event_btnPrevActionPerformed

    public MyLabel getLblComment() {
        return lblComment;
    }

    public MyLabel getLblHeart() {
        return lblHeart;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnNext;
    private online.syncio.component.MyButton btnPrev;
    private online.syncio.component.MyLabel lblComment;
    private online.syncio.component.MyLabel lblCountImage;
    private javax.swing.JLabel lblDateCreated;
    private online.syncio.component.MyLabel lblHeart;
    private javax.swing.JLabel lblTotalLike;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername2;
    private javax.swing.JLabel lblViewAllCmt;
    private online.syncio.component.MyPanel pnlImages;
    private online.syncio.component.MyTextPane txtCaption;
    // End of variables declaration//GEN-END:variables
}
