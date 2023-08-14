package online.syncio.view.login;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import java.awt.Color;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyCheckBox;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.controller.user.LoginController;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.GoogleOAuthHelper;
import online.syncio.utils.TextHelper;
import online.syncio.view.admin.MainAdmin;
import online.syncio.view.user.Main;

/**
 * Represents the Login JFrame, which allows users to log in using their credentials.
 */
public class Login extends javax.swing.JFrame {

    // Constants for Gmail API authentication
    private static final String APPLICATION_NAME = "Syncio";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/online/syncio/config/credentials.json";

    // Controller for handling login logic
    private LoginController controller;

    
    /**
     * Creates a new instance of the Login JFrame.
     */
    public Login() {
        MongoDBConnect.connect();

        setUndecorated(true);
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        // Install GlassPanePopup to handle popups
        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        TextHelper.addPlaceholderText(txtUser, "Username");
        TextHelper.addPlaceholderText(txtPassword, "Password");

        txtUser.requestFocus();

        // Initialize the LoginController to handle login actions
        this.controller = new LoginController(this);

        //press Enter => click btnLogin
        ActionHelper.assignEnterKeyListener(btnLogin, txtUser, txtPassword);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        windowTitleBar1 = new online.syncio.component.WindowTitleBar();
        pnlMain = new online.syncio.component.MyPanel();
        pnlForm = new javax.swing.JPanel();
        myLabel1 = new online.syncio.component.MyLabel();
        txtUser = new online.syncio.component.MyTextField();
        btnLogin = new online.syncio.component.MyButton();
        lblForgetPassword = new online.syncio.component.MyLabel();
        lblCreateAccount = new online.syncio.component.MyLabel();
        lblContinue = new online.syncio.component.MyLabel();
        txtPassword = new online.syncio.component.MyPasswordField();
        btnContinueWithGoogle = new online.syncio.component.MyButton();
        chkRememberMe = new online.syncio.component.MyCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Login"); // NOI18N

        pnlContainer.setRoundBottomLeft(20);
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setRoundTopLeft(20);
        pnlContainer.setRoundTopRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        windowTitleBar1.setFrame(this);
        pnlContainer.add(windowTitleBar1, java.awt.BorderLayout.PAGE_START);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setPreferredSize(new java.awt.Dimension(412, 454));

        myLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myLabel1.setText("Login");
        myLabel1.setFont(new java.awt.Font("SF Pro Display Bold", 0, 36)); // NOI18N
        myLabel1.setFontBold(2);

        txtUser.setName("txtUser"); // NOI18N

        btnLogin.setBackground(new java.awt.Color(0, 149, 246));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorderColor(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnLogin.setName("btnLogin"); // NOI18N
        btnLogin.setPreferredSize(new java.awt.Dimension(92, 20));
        btnLogin.setRadius(10);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblForgetPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblForgetPassword.setText("FORGOT PASSWORD");
        lblForgetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgetPasswordMouseClicked(evt);
            }
        });

        lblCreateAccount.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblCreateAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreateAccount.setText("CREATE AN ACCOUNT");
        lblCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCreateAccountMousePressed(evt);
            }
        });

        lblContinue.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblContinue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContinue.setText("CONTINUE AS A GUEST");
        lblContinue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblContinueMousePressed(evt);
            }
        });

        txtPassword.setText("myPasswordField1");
        txtPassword.setName("txtPassword"); // NOI18N

        btnContinueWithGoogle.setBackground(new java.awt.Color(254, 255, 255));
        btnContinueWithGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/google_28px.png"))); // NOI18N
        btnContinueWithGoogle.setText(" Continue with Google");
        btnContinueWithGoogle.setBorderColor(new java.awt.Color(219, 219, 219));
        btnContinueWithGoogle.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnContinueWithGoogle.setPreferredSize(new java.awt.Dimension(92, 20));
        btnContinueWithGoogle.setRadius(10);
        btnContinueWithGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueWithGoogleActionPerformed(evt);
            }
        });

        chkRememberMe.setText("Remember me");

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addComponent(chkRememberMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblForgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addGap(0, 132, Short.MAX_VALUE)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(125, 125, 125))))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnContinueWithGoogle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRememberMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnContinueWithGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(434, 434, 434)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(434, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        controller.loginAuthentication();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblCreateAccountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccountMousePressed
        dispose();
        new Signup().setVisible(true);
    }//GEN-LAST:event_lblCreateAccountMousePressed

    private void lblForgetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgetPasswordMouseClicked
        dispose();
        new Forgot().setVisible(true);
    }//GEN-LAST:event_lblForgetPasswordMouseClicked

    private void btnContinueWithGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueWithGoogleActionPerformed
        UserDAO userDAO = MongoDBConnect.getUserDAO();
        String userEmail;

        try {
            // Build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleOAuthHelper.getCredentials(HTTP_TRANSPORT, CREDENTIALS_FILE_PATH, JSON_FACTORY, SCOPES))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // Get the user's email address
            String user = "me";
            com.google.api.services.gmail.model.Profile profile = service.users().getProfile(user).execute();
            userEmail = profile.getEmailAddress();

            User u = userDAO.getByEmail(userEmail);

            if (u != null && u.getPassword().equals("")) {
                if (u.getFlag() == 1) {
                    GlassPanePopup.showPopup(new MyDialog("Account Unavailable", "We're sorry, but your account is currently unavailable.\nPlease try again later or contact support for assistance."), "dialog");
                    return;
                }

                // log in
                LoggedInUser.setCurrentUser(u); //set loggedin user

                // check role
                if (LoggedInUser.isAdmin()) {
                    new MainAdmin().setVisible(true);
                    dispose();
                } else {
                    new Main().setVisible(true);
                    dispose();
                }
            } else if (u != null && !u.getPassword().equals("")) {
                GlassPanePopup.showPopup(new MyDialog("Login Method Notice", "This email already linked to standard account.\nPlease sign in using your original username and password."), "dialog");
            } else {
                //not found account
                GlassPanePopup.showPopup(new MyDialog("Account Not Found", "You don't have a linked Syncio account with your Google Account.\nTry logging in with your username. If you don't have an account, please sign up."), "dialog");
            }
        } catch (IOException | GeneralSecurityException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnContinueWithGoogleActionPerformed

    private void lblContinueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinueMousePressed
        new Main().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblContinueMousePressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        ActionHelper.registerShutdownHook(); // Register the shutdown hook

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    /**
    * Returns the MyPasswordField component representing the password input field in the login form.
    *
    * @return The MyPasswordField component for password input.
    */
    public MyPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
    * Returns the MyTextField component representing the username input field in the login form.
    *
    * @return The MyTextField component for username input.
    */
    public MyTextField getTxtUser() {
        return txtUser;
    }
    
    /**
    * Returns the MyCheckBox component representing the "Remember Me" checkbox in the login form.
    *
    * @return The MyCheckBox component for the "Remember Me" checkbox.
    */
    public MyCheckBox getChkRememberMe() {
        return chkRememberMe;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnContinueWithGoogle;
    private online.syncio.component.MyButton btnLogin;
    private online.syncio.component.MyCheckBox chkRememberMe;
    private online.syncio.component.MyLabel lblContinue;
    private online.syncio.component.MyLabel lblCreateAccount;
    private online.syncio.component.MyLabel lblForgetPassword;
    private online.syncio.component.MyLabel myLabel1;
    private online.syncio.component.MyPanel pnlContainer;
    private javax.swing.JPanel pnlForm;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyTextField txtUser;
    private online.syncio.component.WindowTitleBar windowTitleBar1;
    // End of variables declaration//GEN-END:variables
}
