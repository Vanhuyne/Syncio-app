package online.syncio.view.admin;

import online.syncio.view.user.PostUI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.controller.user.LoginController;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;
import online.syncio.utils.Validator;

/**
 * The UserDetail class represents a graphical user interface for displaying and editing user details.
 * It extends the javax.swing.JFrame class to provide a window for interacting with user information.
 */
public class UserDetail extends javax.swing.JFrame {

    private LoginController controller;
    private MongoDatabase database;
    private UserDAO userDAO;
    private PostDAO postDAO;
    FindIterable<Post> posts;
    User currentUser;
    UserManager managementUser;

    public UserDetail(UserManager managementUser, User user) {
        this.userDAO = MongoDBConnect.getUserDAO();
        this.postDAO = MongoDBConnect.getPostDAO();
        this.currentUser = user;
        this.managementUser = managementUser;

        setUndecorated(true);
        initComponents();
        GlassPanePopup.install(this);
        setBackground(new Color(0f, 0f, 0f, 0f));
        setLocationRelativeTo(null);

        if (user == null) {
            //new
            txtID.setVisible(false);
            txtDateCreated.setVisible(false);
            lblLoading.setText("No posts found for this profile.");
            lblLoading.setIcon(null);
            txtEmail.setEditable(true);
            txtUsername.setEditable(true);
            txtPassword.setEditable(true);
            txtBio.setEditable(true);
            txtEmail.requestFocus();
        } else {
            //edit
            txtID.setText(user.getId().toString());
            txtEmail.setText(user.getEmail());
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
            txtDateCreated.setText(user.getDateCreated());
            if (user.getBio() != null) {
                txtBio.setText(user.getBio());
            }
            lblPost.setText(userDAO.countPost(user.getId().toString()) + " posts");
            lblFollowing.setText(user.getFollowing().size() + " following");
            if (user.getRole() == 0) {
                rdoUser.setSelected(true);
                rdoAdmin.setVisible(false);
            } else {
                rdoAdmin.setSelected(true);
                rdoAdmin.setVisible(true);
            }

            txtEmail.setForeground(Color.BLACK);
            txtUsername.setForeground(Color.BLACK);
            txtPassword.setForeground(Color.BLACK);
            txtBio.setForeground(Color.BLACK);

            flag();

            User u = new User();
            u.setId(user.getId());
            u.setFollowing(new ArrayList<>(Collections.singletonList(new UserIDAndDate(user.getId().toString()))));
            posts = postDAO.getAllPostOfFollowers(u);

            // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            loadMorePosts();
        }
    }

    /**
     * Adds loading indicator to the feed panel.
     */
    public void addLoading() {
        feedPanel.add(lblLoading);
    }

    /**
     * Removes loading indicator from the feed panel.
     */
    public void removeLoading() {
        lblLoading.setText("");
        feedPanel.remove(lblLoading);
    }

    /**
     * Loads more posts using a separate thread.
     */
    private void loadMorePosts() {
        // Create a thread for loading and displaying posts
        Thread thread = new Thread(() -> {
            int count = 0;
            for (Post post : posts) {
                PostUI postUI = new PostUI(post.getId().toString(), currentUser.getId().toString());
                postUI.getLblHeart().setVisible(false);
                postUI.getLblComment().setVisible(false);
                postUI.getLblReport().setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    removeLoading();
                    feedPanel.add(postUI);
                    addLoading();
                });
                feedPanel.revalidate();
                feedPanel.repaint();

                count++;
            }

            if (count != 0) {
                removeLoading();
            } else {
                lblLoading.setText("No posts found for this profile.");
                lblLoading.setIcon(null);
            }
        });

        // Start the thread
        thread.start();
    }

    /**
     * Sets components editable status based on flag.
     */
    public void flag() {
        if (currentUser.getFlag() == 0) {
            txtUsername.setEditable(true);
            txtBio.setEditable(true);
            btnSetFlag.setText("Set Flag");
            txtUsername.requestFocus();
        } else {
            txtEmail.setEditable(false);
            txtUsername.setEditable(false);
            txtPassword.setEditable(false);
            txtBio.setEditable(false);
            btnSetFlag.setText("Unflag");
        }
    }

    /**
     * Toggles the user's flag status.
     */
    public void toggleFlag() {
        if (currentUser.getFlag() == 0) {
            currentUser.setFlag(1);
        } else {
            currentUser.setFlag(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupRole = new javax.swing.ButtonGroup();
        pnlContainer = new online.syncio.component.MyPanel();
        pnlTitle = new online.syncio.component.MyPanel();
        lblTitle = new online.syncio.component.MyLabel();
        btnSetFlag = new online.syncio.component.MyButton();
        pnlMain = new online.syncio.component.MyPanel();
        pnlRight = new online.syncio.component.MyPanel();
        lblAvt = new online.syncio.component.MyLabel();
        lblPost = new online.syncio.component.MyLabel();
        lblFollowing = new online.syncio.component.MyLabel();
        txtID = new online.syncio.component.MyTextField();
        txtEmail = new online.syncio.component.MyTextField();
        txtUsername = new online.syncio.component.MyTextField();
        txtDateCreated = new online.syncio.component.MyTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBio = new online.syncio.component.MyTextArea();
        lblRole = new online.syncio.component.MyLabel();
        rdoAdmin = new online.syncio.component.MyRadioButton();
        rdoUser = new online.syncio.component.MyRadioButton();
        btnSave = new online.syncio.component.MyButton();
        btnCancel = new online.syncio.component.MyButton();
        txtPassword = new online.syncio.component.MyPasswordField();
        pnlLeft = new online.syncio.component.MyPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        feedPanel = new online.syncio.component.MyPanel();
        lblLoading = new online.syncio.component.MyLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlContainer.setBorderColor(new java.awt.Color(219, 219, 219));
        pnlContainer.setBorderThickness(2);
        pnlContainer.setPreferredSize(new java.awt.Dimension(1080, 679));
        pnlContainer.setRoundBottomLeft(20);
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setRoundTopLeft(20);
        pnlContainer.setRoundTopRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTitle.setBorderColor(new java.awt.Color(219, 219, 219));
        pnlTitle.setBorderThickness(2);
        pnlTitle.setPreferredSize(new java.awt.Dimension(0, 40));
        pnlTitle.setRoundTopLeft(20);
        pnlTitle.setRoundTopRight(20);

        lblTitle.setText("User Details");

        btnSetFlag.setBackground(new java.awt.Color(238, 84, 102));
        btnSetFlag.setForeground(new java.awt.Color(255, 255, 255));
        btnSetFlag.setText("Set Flag");
        btnSetFlag.setBorderColor(new java.awt.Color(238, 84, 102));
        btnSetFlag.setFont(new java.awt.Font("SF Pro Display Medium", 0, 13)); // NOI18N
        btnSetFlag.setRadius(10);
        btnSetFlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetFlagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSetFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pnlContainer.add(pnlTitle, java.awt.BorderLayout.NORTH);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorderColor(new java.awt.Color(219, 219, 219));
        pnlMain.setBorderThickness(2);
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlRight.setBackground(new java.awt.Color(255, 255, 255));
        pnlRight.setBorderColor(new java.awt.Color(219, 219, 219));
        pnlRight.setBorderThickness(2);
        pnlRight.setPreferredSize(new java.awt.Dimension(360, 639));
        pnlRight.setRoundBottomRight(20);

        lblAvt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/avt_128px.png"))); // NOI18N

        lblPost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPost.setText("0 posts");
        lblPost.setPreferredSize(new java.awt.Dimension(320, 18));

        lblFollowing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFollowing.setText("0 following");
        lblFollowing.setPreferredSize(new java.awt.Dimension(320, 18));

        txtID.setEditable(false);
        txtID.setPreferredSize(new java.awt.Dimension(320, 35));

        txtEmail.setEditable(false);
        txtEmail.setPlaceholder("Email");
        txtEmail.setPreferredSize(new java.awt.Dimension(320, 35));

        txtUsername.setPlaceholder("Username");
        txtUsername.setPreferredSize(new java.awt.Dimension(320, 35));

        txtDateCreated.setEditable(false);
        txtDateCreated.setPlaceholder("Date created");
        txtDateCreated.setPreferredSize(new java.awt.Dimension(320, 35));

        jScrollPane1.setBorder(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(230, 60));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(230, 60));

        txtBio.setColumns(18);
        txtBio.setRows(3);
        txtBio.setPlaceholder("Bio");
        txtBio.setPreferredSize(new java.awt.Dimension(230, 60));
        jScrollPane1.setViewportView(txtBio);

        lblRole.setText("Role:");

        groupRole.add(rdoAdmin);
        rdoAdmin.setText("Admin");

        groupRole.add(rdoUser);
        rdoUser.setSelected(true);
        rdoUser.setText("User");

        btnSave.setBackground(new java.awt.Color(0, 149, 246));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setBorderColor(new java.awt.Color(0, 149, 246));
        btnSave.setRadius(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setRadius(10);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtPassword.setEditable(false);
        txtPassword.setPlaceholder("Password");
        txtPassword.setPreferredSize(new java.awt.Dimension(320, 35));

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                                    .addComponent(lblAvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(116, 116, 116))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                                    .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblFollowing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(20, 20, 20))))
                        .addGroup(pnlRightLayout.createSequentialGroup()
                            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                        .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlRightLayout.createSequentialGroup()
                                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(rdoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(rdoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlRightLayout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblFollowing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txtDateCreated, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pnlMain.add(pnlRight, java.awt.BorderLayout.CENTER);

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setPreferredSize(new java.awt.Dimension(720, 638));
        pnlLeft.setRoundBottomLeft(20);
        pnlLeft.setLayout(new java.awt.BorderLayout());

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        feedPanel.setBackground(new java.awt.Color(255, 255, 255));
        feedPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 160, 0, 160));
        feedPanel.setBorderColor(new java.awt.Color(219, 219, 219));
        feedPanel.setBorderThickness(2);
        feedPanel.setMaximumSize(new java.awt.Dimension(1080, 679));
        feedPanel.setMinimumSize(new java.awt.Dimension(1080, 679));
        feedPanel.setRoundBottomLeft(20);
        feedPanel.setLayout(new javax.swing.BoxLayout(feedPanel, javax.swing.BoxLayout.Y_AXIS));

        lblLoading.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/loading.gif"))); // NOI18N
        lblLoading.setText("It may take some time for loading the first post...");
        lblLoading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLoading.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        feedPanel.add(lblLoading);

        scrollPane.setViewportView(feedPanel);

        pnlLeft.add(scrollPane, java.awt.BorderLayout.CENTER);

        pnlMain.add(pnlLeft, java.awt.BorderLayout.WEST);

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        boolean isExist = userDAO.checkEmail(email);

        //validate
        ArrayList<String> errors = new ArrayList<>();
        errors.add(Validator.email((JTextField) txtEmail, "Email", email, false, "Email"));
        errors.add(Validator.allowNumberText((JTextField) txtUsername, "Username", username, false, "Username"));
        if (!isExist) {
            errors.add(Validator.allowNumberText((JTextField) txtPassword, "Password", password, false, "Password"));
        }

        Collections.reverse(errors);
        String e = "";
        for (String s : errors) {
            e += s;
        }

        //co loi
        if (!e.isEmpty()) {
            GlassPanePopup.showPopup(new MyDialog("Error", e), "dialog");
            return;
        }

        //check add or update
        if (isExist) {
            //update
            if (!txtID.isVisible()) {
                GlassPanePopup.showPopup(new MyDialog("Email Address Already Taken", "The email address you entered is already taken.\nPlease use a different email address."), "dialog");
                return;
            }
            User u = new User(txtUsername.getText(), currentUser.getPassword(), currentUser.getEmail(), txtBio.getText().equals("Bio") ? null : txtBio.getText(), rdoAdmin.isSelected() ? 1 : 0, currentUser.getFlag(), currentUser.getFollowing());
            u.setId(currentUser.getId());
            if (!u.getUsername().equals(currentUser.getUsername())) {
                if (userDAO.checkUsername(username)) {
                    GlassPanePopup.showPopup(new MyDialog("Username Already Taken", "The username you've chosen is already taken.\nPlease select a different username."), "dialog");
                    return;
                }
            }

            boolean result = userDAO.updateByID(u);
            if (result) {
                dispose();
                managementUser.fillToTable(true);
            }
        } else {
            //add
            if (userDAO.checkUsername(username)) {
                GlassPanePopup.showPopup(new MyDialog("Username Already Taken", "The username you've chosen is already taken.\nPlease select a different username."), "dialog");
                return;
            }

            //gui email
            String subject = "WELCOME TO SYNCIO";
            String recipientName = email;
            String content = "<tr>\n"
                    + "<td class=\"text-center\" style=\"padding: 80px 0 !important;\">\n"
                    + "<h4>" + subject + "</h4>\n"
                    + "<br>\n"
                    + "Dear " + recipientName + ",<br>\n"
                    + "Thank you for creating your personal account on SYNCIO.<br>\n"
                    + "<br><br>\n"
                    + "An account has been created for you in Syncio by \n" + LoggedInUser.getCurrentUser().getUsername() + ".\n"
                    + "<br><br>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<p class=\"text-center\">If you did not request this account or have any questions, please contact our support team.</p>\n"
                    + "</td>\n"
                    + "</tr>\n";

            boolean sendStatus = SendEmail.sendFormat(email, email, subject, content);

            if (!sendStatus) {
                GlassPanePopup.showPopup(new MyDialog("Error", "An error occurred while sending the email"), "dialog");
            } else {
                // hash password
                password = TextHelper.HashPassword(password);
                boolean result = userDAO.add(new User(username, password, email, null, rdoAdmin.isSelected() ? 1 : 0, btnSetFlag.getText().equalsIgnoreCase("set flag") ? 0 : 1, null));
                if (result) {
                    dispose();
                    managementUser.fillToTable(true);
                }
            }
        }

//        u.setFlag(currentUser.getFlag());
//        u.setRole(rdoAdmin.isSelected() ? 1 : 0);
//        System.out.println(currentUser.getBio());
//        System.out.println(u.getBio());
//        System.out.println(currentUser.equals(u));
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSetFlagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetFlagActionPerformed
        toggleFlag();
        flag();
    }//GEN-LAST:event_btnSetFlagActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

//        java.awt.EventQueue.invokeLater(() -> {
//            new ManagementUserDetail().setVisible(true);
//        });

//        java.awt.EventQueue.invokeLater(() -> {
//            new UserDetail().setVisible(true);
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnCancel;
    private online.syncio.component.MyButton btnSave;
    private online.syncio.component.MyButton btnSetFlag;
    private online.syncio.component.MyPanel feedPanel;
    private javax.swing.ButtonGroup groupRole;
    private javax.swing.JScrollPane jScrollPane1;
    private online.syncio.component.MyLabel lblAvt;
    private online.syncio.component.MyLabel lblFollowing;
    private online.syncio.component.MyLabel lblLoading;
    private online.syncio.component.MyLabel lblPost;
    private online.syncio.component.MyLabel lblRole;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlLeft;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlRight;
    private online.syncio.component.MyPanel pnlTitle;
    private online.syncio.component.MyRadioButton rdoAdmin;
    private online.syncio.component.MyRadioButton rdoUser;
    private online.syncio.component.MyScrollPane scrollPane;
    private online.syncio.component.MyTextArea txtBio;
    private online.syncio.component.MyTextField txtDateCreated;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyTextField txtID;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
