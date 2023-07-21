package online.syncio.view;

import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import static online.syncio.view.Main.prevTab;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyNotification;
import online.syncio.component.MyTextPane;
import online.syncio.controller.CreateNewPostController;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;

public class PopupCreateNewPost extends javax.swing.JPanel {

    private ArrayList<String> imagePaths = new ArrayList<>();
    private int imageIndex = 0;

    private Main main;
    private CreateNewPostController controller;
    private MongoDatabase database;
    private PostDAO postDAO;
    private UserDAO userDAO;

    public PopupCreateNewPost(Main main) {
        this.main = main;
        this.database = main.getDatabase();

        postDAO = new PostDAOImpl(database);
        userDAO = new UserDAOImpl(database);

        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        txtCaption.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        txtCaption.setFont(new Font("SansSerif", Font.PLAIN, 16));

        txtCaption.setContentType("text/html");
        showCaptionLength();

        txtCaption.setPlaceholder("Write a caption...");
        btnSelectImage.requestFocus();

        lblAccount.setText(LoggedInUser.getCurrentUser().getUsername());

        imageSelected(false);

        this.controller = new CreateNewPostController(this);
    }

    public void addEmoji(JLabel lbl, Color color) {
        int length = txtCaption.getDocument().getLength();

        if (length < 500) {
            txtCaption.append(lbl.getText(), color);
            txtCaption.append("", Color.BLACK);
            txtCaption.requestFocus();
        }
    }

    public void showCaptionLength() {
        int length = txtCaption.getDocument().getLength();
        lblCountNumber.setText(length + "/500");
    }

    public void imageSelected(boolean isSelected) {
        if (isSelected) {
            lblImage.setVisible(false);
            btnSelectImage.setVisible(false);
            btnRemoveImage.setVisible(true);
            lblCountImage.setVisible(true);
            if (imagePaths.size() > 1) {
                lblCountImage.setText(imageIndex + 1 + "/" + imagePaths.size());
                btnPrev.setVisible(true);
                btnNext.setVisible(true);
            }
        } else {
            lblImage.setVisible(true);
            btnSelectImage.setVisible(true);
            btnRemoveImage.setVisible(false);
            lblCountImage.setVisible(false);
            btnPrev.setVisible(false);
            btnNext.setVisible(false);
        }

        pnlLeft.revalidate();
        pnlLeft.repaint();
    }

    public void selectImage(int i) {
        if (i >= 0 && i < imagePaths.size()) {
            imageIndex = i;
            lblCountImage.setText(imageIndex + 1 + "/" + imagePaths.size());
            pnlLeft.setImg(imagePaths.get(i));
            pnlLeft.revalidate();
            pnlLeft.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        pnlTop = new online.syncio.component.MyPanel();
        btnClose = new online.syncio.component.MyButton();
        lblTitle = new online.syncio.component.MyLabel();
        btnShare = new online.syncio.component.MyButton();
        pnlContent = new online.syncio.component.MyPanel();
        pnlLeft = new online.syncio.component.MyPanel();
        btnSelectImage = new online.syncio.component.MyButton();
        btnRemoveImage = new online.syncio.component.MyButton();
        lblImage = new online.syncio.component.MyLabel();
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
        myPanel1 = new online.syncio.component.MyPanel();
        lblCountNumber = new online.syncio.component.MyLabel();
        myScrollPane2 = new online.syncio.component.MyScrollPane();
        txtCaption = new online.syncio.component.MyTextPane();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRadius(10);
        pnlMain.setRoundBottomLeft(10);
        pnlMain.setRoundBottomRight(10);
        pnlMain.setRoundTopLeft(10);
        pnlMain.setRoundTopRight(10);
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTop.setRoundTopLeft(10);
        pnlTop.setRoundTopRight(10);

        btnClose.setBackground(null);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/close_24px-2.png"))); // NOI18N
        btnClose.setBorderThickness(0);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblTitle.setText("Create new post");

        btnShare.setBackground(null);
        btnShare.setForeground(new java.awt.Color(0, 149, 246));
        btnShare.setText("Share");
        btnShare.setBorderThickness(0);
        btnShare.setFontBold(2);
        btnShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
            .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTopLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(274, 274, 274)
                    .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
            .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTopLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnShare, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(7, Short.MAX_VALUE)))
        );

        pnlMain.add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setRoundBottomLeft(10);
        pnlContent.setRoundBottomRight(10);
        pnlContent.setLayout(new java.awt.BorderLayout());

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        pnlLeft.setPreferredSize(new java.awt.Dimension(466, 472));
        pnlLeft.setRoundBottomLeft(10);

        btnSelectImage.setBackground(new java.awt.Color(0, 149, 246));
        btnSelectImage.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectImage.setText("Select photo from computer");
        btnSelectImage.setBorderThickness(0);
        btnSelectImage.setMaximumSize(new java.awt.Dimension(55555555, 5533333));
        btnSelectImage.setRadius(10);
        btnSelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImageActionPerformed(evt);
            }
        });

        btnRemoveImage.setBackground(new Color(0f, 0f, 0f, 0f));
        btnRemoveImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/close_24px-2.png"))); // NOI18N
        btnRemoveImage.setBorderPainted(false);
        btnRemoveImage.setBorderThickness(0);
        btnRemoveImage.setRadius(24);
        btnRemoveImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveImageActionPerformed(evt);
            }
        });

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/other/image-select.png"))); // NOI18N

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
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnSelectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(122, 122, 122))
                    .addComponent(btnRemoveImage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(401, 401, 401)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnRemoveImage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(lblCountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pnlContent.add(pnlLeft, java.awt.BorderLayout.LINE_START);

        pnlRight.setBackground(new java.awt.Color(255, 255, 255));
        pnlRight.setRoundBottomRight(10);
        pnlRight.setLayout(new java.awt.BorderLayout());

        lblAccount.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(15, 10, 15, 10)));
        lblAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile.png"))); // NOI18N
        lblAccount.setText(" 56duong");
        lblAccount.setMaximumSize(new java.awt.Dimension(57, 54));
        lblAccount.setMinimumSize(new java.awt.Dimension(57, 54));
        lblAccount.setPreferredSize(new java.awt.Dimension(57, 54));
        pnlRight.add(lblAccount, java.awt.BorderLayout.PAGE_START);

        pnlIcon.setBackground(new java.awt.Color(255, 255, 255));
        pnlIcon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        pnlIcon.setMaximumSize(new java.awt.Dimension(170, 90));
        pnlIcon.setMinimumSize(new java.awt.Dimension(170, 90));
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

        myPanel1.setPreferredSize(new java.awt.Dimension(278, 407));
        myPanel1.setLayout(new java.awt.BorderLayout());

        lblCountNumber.setBackground(new java.awt.Color(254, 255, 255));
        lblCountNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblCountNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCountNumber.setText(" ");
        myPanel1.add(lblCountNumber, java.awt.BorderLayout.PAGE_END);

        myScrollPane2.setBorder(null);

        txtCaption.setBorderThickness(0);
        txtCaption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaptionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCaptionKeyTyped(evt);
            }
        });
        myScrollPane2.setViewportView(txtCaption);

        myPanel1.add(myScrollPane2, java.awt.BorderLayout.CENTER);

        pnlRight.add(myPanel1, java.awt.BorderLayout.LINE_END);

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

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        GlassPanePopup.closePopup("createnewpost");
        this.main.showTab(prevTab);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void lblOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOKMousePressed
        addEmoji(lblOK, new Color(255, 204, 0));
    }//GEN-LAST:event_lblOKMousePressed

    private void btnSelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImageActionPerformed
        uploadImage();
    }//GEN-LAST:event_btnSelectImageActionPerformed

    private void btnRemoveImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveImageActionPerformed
        pnlLeft.setImg("");
        imagePaths.clear();
        imageSelected(false);
    }//GEN-LAST:event_btnRemoveImageActionPerformed

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

    private void txtCaptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaptionKeyTyped
        int length = txtCaption.getDocument().getLength();

        if (length >= 500 && !(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCaptionKeyTyped

    private void btnShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShareActionPerformed
        controller.uploadPost();
    }//GEN-LAST:event_btnShareActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        selectImage(imageIndex + 1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        selectImage(imageIndex - 1);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void txtCaptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaptionKeyReleased
        showCaptionLength();
    }//GEN-LAST:event_txtCaptionKeyReleased

    public void uploadImage() {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpeg, *.jpg, *.png", new String[]{"jpeg", "png", "jpg"});
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);

        int result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fc.getSelectedFiles();

            for (File file : files) {
                imagePaths.add(file.getAbsolutePath());
            }

            pnlLeft.setImg(imagePaths.get(imageIndex));
            imageSelected(true);
        }
    }

    public void uploadNotification() {
        GlassPanePopup.closePopup("createnewpost");
        this.main.showTab(prevTab);
        new MyNotification((JFrame) SwingUtilities.getWindowAncestor(this), true, "Post shared").setVisible(true);
    }

    public MyTextPane getTxtCaption() {
        return txtCaption;
    }

    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnClose;
    private online.syncio.component.MyButton btnNext;
    private online.syncio.component.MyButton btnPrev;
    private online.syncio.component.MyButton btnRemoveImage;
    private online.syncio.component.MyButton btnSelectImage;
    private online.syncio.component.MyButton btnShare;
    private online.syncio.component.MyLabel lblAccount;
    private online.syncio.component.MyLabel lblCamera;
    private online.syncio.component.MyLabel lblCountImage;
    private online.syncio.component.MyLabel lblCountNumber;
    private online.syncio.component.MyLabel lblHeart;
    private online.syncio.component.MyLabel lblImage;
    private online.syncio.component.MyLabel lblLaugh;
    private online.syncio.component.MyLabel lblLike;
    private online.syncio.component.MyLabel lblOK;
    private online.syncio.component.MyLabel lblSmile;
    private online.syncio.component.MyLabel lblSparkles;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyPanel myPanel1;
    private online.syncio.component.MyScrollPane myScrollPane2;
    private online.syncio.component.MyPanel pnlContent;
    private online.syncio.component.MyPanel pnlIcon;
    private online.syncio.component.MyPanel pnlLeft;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlRight;
    private online.syncio.component.MyPanel pnlTop;
    private online.syncio.component.MyTextPane txtCaption;
    // End of variables declaration//GEN-END:variables
}
