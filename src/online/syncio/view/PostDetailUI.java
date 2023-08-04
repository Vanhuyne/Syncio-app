package online.syncio.view;

import com.mongodb.MongoInterruptedException;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import online.syncio.component.MyNotification;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.UserIDAndDateAndText;
import online.syncio.utils.ImageHelper;

public class PostDetailUI extends javax.swing.JPanel {

    private PostDAO postDAO;
    private UserDAO userDAO;
    private String userID;
    private String postID;
    private Post post;
    private int imageIndex = 0;
    private ChangeStreamIterable<Post> changeStreamPosts;
    private Thread changeStreamThread;

    public PostDetailUI(String postID) {
        MongoDBConnect.connect();
        this.userDAO = MongoDBConnect.getUserDAO();
        this.postDAO = MongoDBConnect.getPostDAO();

        this.postID = postID;
        post = postDAO.getByID(postID);

        initComponents();

        // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        //pnlCmtContainer.setPreferredSize(new Dimension(lblAccount.getWidth(), pnlCmtContainer.getHeight()));

        showInfoPost(postID);

        changeStreamPosts = postDAO.getChangeStream();

        // Start the change stream listener
        startPostChangeStream();
    }

    private void startPostChangeStream() {
        changeStreamThread = new Thread(() -> {
            try {
                ChangeStreamIterable<Post> changeStreamPosts = postDAO.getChangeStream();

                // ChangeStreamDocuments should be iterated using the iterator() method
                MongoCursor<ChangeStreamDocument<Post>> iterator = changeStreamPosts.iterator();
                while (iterator.hasNext()) {
                    ChangeStreamDocument<Post> changeDocument = iterator.next();

                    // Handle the change event here
                    Post updatedPost = changeDocument.getFullDocument();

                    if (updatedPost != null) {
                        List<UserIDAndDateAndText> commentList = updatedPost.getCommentList();

                        // Check if the 'postID' variable is initialized and matches the updated post's ID
                        // If postID is not initialized, this condition will be skipped
                        if (commentList != null && updatedPost.getId().toString().equals(postID)) {
                            System.out.println("co");

                            // Assuming 'post' is the instance of the current post being displayed in your GUI
                            List<UserIDAndDateAndText> existingComments = post.getCommentList();

                            // Check if the number of comments has increased
                            if (existingComments.size() < commentList.size()) {
                                // Add the new comment(s) to the UI
                                SwingUtilities.invokeLater(() -> {
                                    for (int i = existingComments.size(); i < commentList.size(); i++) {
                                        UserIDAndDateAndText newComment = commentList.get(i);
                                        String user_id = userDAO.getByID(newComment.getUserID()).getUsername();
                                        String text = newComment.getText();
                                        String date = newComment.getDate();
                                        pnlCmt.add(new CommentUI(user_id, text, date));
                                    }

                                    pnlCmt.revalidate();
                                    pnlCmt.repaint();
                                });
                            }
                        }
                    }
                }
            } catch (MongoInterruptedException e) {
                // Handle any exceptions here
                e.printStackTrace();
            }
        });
        changeStreamThread.start();
    }

    public void addEmoji(JLabel lbl, Color color) {
        int length = txtCmt.getDocument().getLength();

        if (length < 500) {
            txtCmt.append(lbl.getText(), color);
            txtCmt.append("", Color.BLACK);
            txtCmt.requestFocus();
        }
    }

    private void loadCmt(String postID) {
        String userName = userDAO.getByID(post.getUserID()).getUsername();
        List<UserIDAndDateAndText> listCmt = post.getCommentList();
        lblAccount.setText(userName);

        for (UserIDAndDateAndText cmt : listCmt) {
            pnlCmt.add(new CommentUI(userDAO.getByID(cmt.getUserID()).getUsername(), cmt.getText(), cmt.getDate()));
        }
        pnlCmt.revalidate();
        pnlCmt.repaint();
    }

    private void showInfoPost(String postID) {
        loadCmt(postID);
        //GlassPanePopup.showPopup(new CommentUI2(user, "xinh vc"), "cmtui");
        //raito
        pnlLeft.setSize(400, 400);
        if (post.getPhotoList().size() > 0) {
            if (post.getPhotoList().size() <= 1) {
                btnNext.setVisible(false);
                btnPrev.setVisible(false);
            } else {
                btnNext.setVisible(true);
                btnPrev.setVisible(true);
            }

            imageIndex = 0;
            pnlLeft.setImg(ImageHelper.readBinaryAsBufferedImage(post.getPhotoList().get(imageIndex)));
            int imgHeight = pnlLeft.getImgHeight();

            if (imgHeight > 300) {
                pnlLeft.setPreferredSize(new Dimension(400, 400));
            } else if (imgHeight > 300) {
                pnlLeft.setPreferredSize(new Dimension(400, 300));
            } else if (imgHeight > 200) {
                pnlLeft.setPreferredSize(new Dimension(400, 200));
            } else {
                pnlLeft.setPreferredSize(new Dimension(400, 100));
            }
        } else {
            pnlLeft.setPreferredSize(new Dimension(0, 0));
            pnlLeft.setImg("");
        }
    }

    public void selectImage(int i) {
//        Post post = postDAO.getByID(postID);
        if (i >= 0 && i < post.getPhotoList().size()) {
            imageIndex = i;
            lblCountImage.setText(imageIndex + 1 + "/" + post.getPhotoList().size());
            pnlLeft.setImg(ImageHelper.readBinaryAsBufferedImage(post.getPhotoList().get(i)));
            pnlLeft.revalidate();
            pnlLeft.repaint();
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

        pnlMain = new online.syncio.component.MyPanel();
        pnlContent = new online.syncio.component.MyPanel();
        pnlLeft = new online.syncio.component.MyPanel();
        btnNext = new online.syncio.component.MyButton();
        btnPrev = new online.syncio.component.MyButton();
        lblCountImage = new online.syncio.component.MyLabel();
        pnlRight = new online.syncio.component.MyPanel();
        lblAccount = new online.syncio.component.MyLabel();
        pnlIcon = new online.syncio.component.MyPanel();
        lblOK = new online.syncio.component.MyLabel();
        lblLike = new online.syncio.component.MyLabel();
        lblHeart = new online.syncio.component.MyLabel();
        lblCamera = new online.syncio.component.MyLabel();
        lblSparkles = new online.syncio.component.MyLabel();
        lblSmile = new online.syncio.component.MyLabel();
        lblLaugh = new online.syncio.component.MyLabel();
        pnlCmtContainer = new online.syncio.component.MyPanel();
        myPanel1 = new online.syncio.component.MyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCmt = new online.syncio.component.MyTextPane();
        btnSend = new online.syncio.component.MyButton();
        scrollPane = new online.syncio.component.MyScrollPane();
        pnlCmt = new online.syncio.component.MyPanel();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRadius(10);
        pnlMain.setRoundBottomLeft(10);
        pnlMain.setRoundBottomRight(10);
        pnlMain.setRoundTopLeft(10);
        pnlMain.setRoundTopRight(10);
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setRoundBottomLeft(10);
        pnlContent.setRoundBottomRight(10);
        pnlContent.setLayout(new java.awt.BorderLayout());

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        pnlLeft.setPreferredSize(new java.awt.Dimension(466, 472));
        pnlLeft.setRoundBottomLeft(10);

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

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(0, 0, 0))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(197, 197, 197)
                .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pnlContent.add(pnlLeft, java.awt.BorderLayout.LINE_START);

        pnlRight.setBackground(new java.awt.Color(255, 255, 255));
        pnlRight.setRoundBottomRight(10);
        pnlRight.setLayout(new java.awt.BorderLayout());

        lblAccount.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(15, 10, 15, 10)));
        lblAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile_24px.png"))); // NOI18N
        lblAccount.setText(" 56duong");
        lblAccount.setFontBold(2);
        lblAccount.setMaximumSize(new java.awt.Dimension(57, 54));
        lblAccount.setMinimumSize(new java.awt.Dimension(57, 54));
        lblAccount.setPreferredSize(new java.awt.Dimension(57, 54));
        pnlRight.add(lblAccount, java.awt.BorderLayout.PAGE_START);

        pnlIcon.setBackground(new java.awt.Color(255, 255, 255));
        pnlIcon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        pnlIcon.setMaximumSize(new java.awt.Dimension(276, 360));
        pnlIcon.setMinimumSize(new java.awt.Dimension(276, 360));
        pnlIcon.setPreferredSize(new java.awt.Dimension(57, 50));
        pnlIcon.setRoundBottomRight(10);
        pnlIcon.setLayout(new java.awt.GridLayout(1, 0, 0, 10));

        lblOK.setForeground(new java.awt.Color(255, 204, 0));
        lblOK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOK.setText("👌");
        lblOK.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblOK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblOK.setMaximumSize(new java.awt.Dimension(20, 49));
        lblOK.setMinimumSize(new java.awt.Dimension(20, 49));
        lblOK.setName(""); // NOI18N
        lblOK.setPreferredSize(new java.awt.Dimension(20, 49));
        lblOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblOKMousePressed(evt);
            }
        });
        pnlIcon.add(lblOK);

        lblLike.setForeground(new java.awt.Color(255, 204, 0));
        lblLike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLike.setText("👍");
        lblLike.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblLike.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLike.setMaximumSize(new java.awt.Dimension(20, 49));
        lblLike.setMinimumSize(new java.awt.Dimension(20, 49));
        lblLike.setPreferredSize(new java.awt.Dimension(20, 49));
        lblLike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLikeMousePressed(evt);
            }
        });
        pnlIcon.add(lblLike);

        lblHeart.setForeground(new java.awt.Color(255, 0, 0));
        lblHeart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeart.setText("❤");
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
        pnlIcon.add(lblHeart);

        lblCamera.setForeground(new java.awt.Color(102, 102, 102));
        lblCamera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCamera.setText("📸");
        lblCamera.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblCamera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCamera.setMaximumSize(new java.awt.Dimension(20, 49));
        lblCamera.setMinimumSize(new java.awt.Dimension(20, 49));
        lblCamera.setPreferredSize(new java.awt.Dimension(20, 49));
        lblCamera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCameraMousePressed(evt);
            }
        });
        pnlIcon.add(lblCamera);

        lblSparkles.setForeground(new java.awt.Color(255, 204, 0));
        lblSparkles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSparkles.setText("✨");
        lblSparkles.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblSparkles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSparkles.setMaximumSize(new java.awt.Dimension(20, 49));
        lblSparkles.setMinimumSize(new java.awt.Dimension(20, 49));
        lblSparkles.setPreferredSize(new java.awt.Dimension(20, 49));
        lblSparkles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSparklesMousePressed(evt);
            }
        });
        pnlIcon.add(lblSparkles);

        lblSmile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSmile.setText("😂");
        lblSmile.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblSmile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSmile.setMaximumSize(new java.awt.Dimension(20, 49));
        lblSmile.setMinimumSize(new java.awt.Dimension(20, 49));
        lblSmile.setPreferredSize(new java.awt.Dimension(20, 49));
        lblSmile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSmileMousePressed(evt);
            }
        });
        pnlIcon.add(lblSmile);

        lblLaugh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLaugh.setText("😁");
        lblLaugh.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblLaugh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLaugh.setMaximumSize(new java.awt.Dimension(20, 49));
        lblLaugh.setMinimumSize(new java.awt.Dimension(20, 49));
        lblLaugh.setPreferredSize(new java.awt.Dimension(20, 49));
        lblLaugh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblLaughMousePressed(evt);
            }
        });
        pnlIcon.add(lblLaugh);

        pnlRight.add(pnlIcon, java.awt.BorderLayout.PAGE_END);

        pnlCmtContainer.setMaximumSize(new java.awt.Dimension(278, 355));
        pnlCmtContainer.setPreferredSize(new java.awt.Dimension(278, 355));
        pnlCmtContainer.setLayout(new java.awt.BorderLayout());

        myPanel1.setBackground(new java.awt.Color(254, 255, 255));
        myPanel1.setPreferredSize(new java.awt.Dimension(278, 50));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtCmt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));
        txtCmt.setBorderThickness(0);
        jScrollPane1.setViewportView(txtCmt);

        btnSend.setBackground(new java.awt.Color(254, 255, 255));
        btnSend.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));
        btnSend.setForeground(new java.awt.Color(0, 149, 246));
        btnSend.setText("Post");
        btnSend.setBorderThickness(0);
        btnSend.setFontBold(2);
        btnSend.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSend.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSend.setRadius(0);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myPanel1Layout = new javax.swing.GroupLayout(myPanel1);
        myPanel1.setLayout(myPanel1Layout);
        myPanel1Layout.setHorizontalGroup(
            myPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        myPanel1Layout.setVerticalGroup(
            myPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(btnSend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pnlCmtContainer.add(myPanel1, java.awt.BorderLayout.PAGE_END);

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlCmt.setBackground(new java.awt.Color(255, 255, 255));
        pnlCmt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        pnlCmt.setLayout(new javax.swing.BoxLayout(pnlCmt, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnlCmt);

        pnlCmtContainer.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlRight.add(pnlCmtContainer, java.awt.BorderLayout.CENTER);

        pnlContent.add(pnlRight, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlContent, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOKMousePressed
        addEmoji(lblOK, new Color(255, 204, 0));
    }//GEN-LAST:event_lblOKMousePressed

    private void lblLikeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLikeMousePressed
        addEmoji(lblLike, new Color(255, 204, 0));
    }//GEN-LAST:event_lblLikeMousePressed

    private void lblHeartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHeartMousePressed
        addEmoji(lblHeart, new Color(255, 0, 0));
    }//GEN-LAST:event_lblHeartMousePressed

    private void lblCameraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCameraMousePressed
        addEmoji(lblCamera, new Color(102, 102, 102));
    }//GEN-LAST:event_lblCameraMousePressed

    private void lblSparklesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSparklesMousePressed
        addEmoji(lblSparkles, new Color(255, 204, 0));
    }//GEN-LAST:event_lblSparklesMousePressed

    private void lblSmileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSmileMousePressed
        addEmoji(lblSmile, Color.BLACK);
    }//GEN-LAST:event_lblSmileMousePressed

    private void lblLaughMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLaughMousePressed
        addEmoji(lblLaugh, Color.BLACK);
    }//GEN-LAST:event_lblLaughMousePressed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        selectImage(imageIndex + 1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        selectImage(imageIndex - 1);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String cmt = txtCmt.getText().trim();
        String uID = LoggedInUser.getCurrentUser().getId().toString();

        if (postDAO.addComment(cmt, uID, postID)) {
            System.out.println("Đã gửi comment");
            txtCmt.setText("");
            new MyNotification((JFrame) SwingUtilities.getWindowAncestor(this), true, "Sent a Comment").setVisible(true);
            post = postDAO.getByID(postID);
            pnlCmt.removeAll();
            loadCmt(postID);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnNext;
    private online.syncio.component.MyButton btnPrev;
    private online.syncio.component.MyButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private online.syncio.component.MyLabel lblAccount;
    private online.syncio.component.MyLabel lblCamera;
    private online.syncio.component.MyLabel lblCountImage;
    private online.syncio.component.MyLabel lblHeart;
    private online.syncio.component.MyLabel lblLaugh;
    private online.syncio.component.MyLabel lblLike;
    private online.syncio.component.MyLabel lblOK;
    private online.syncio.component.MyLabel lblSmile;
    private online.syncio.component.MyLabel lblSparkles;
    private online.syncio.component.MyPanel myPanel1;
    private online.syncio.component.MyPanel pnlCmt;
    private online.syncio.component.MyPanel pnlCmtContainer;
    private online.syncio.component.MyPanel pnlContent;
    private online.syncio.component.MyPanel pnlIcon;
    private online.syncio.component.MyPanel pnlLeft;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlRight;
    private online.syncio.component.MyScrollPane scrollPane;
    private online.syncio.component.MyTextPane txtCmt;
    // End of variables declaration//GEN-END:variables
}
