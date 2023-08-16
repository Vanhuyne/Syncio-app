package online.syncio.view.login;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyButton;
import online.syncio.component.MyDialog;
import online.syncio.component.MyLabel;
import online.syncio.component.MyPanel;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.controller.user.SignupController;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.User;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.GoogleOAuthHelper;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;
import online.syncio.view.user.ErrorDetail;
import online.syncio.view.user.Main;

/**
 * A JFrame class representing the user interface for user registration (signup).
 */
public class Signup extends javax.swing.JFrame {

    /**
    * The name of the application for the signup process.
    */
    private static String APPLICATION_NAME = "Syncio";
    
    /**
    * The JsonFactory instance for handling JSON data.
    */
    private static JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    
    /**
    * The path to the directory where tokens are stored.
    */
    private static String TOKENS_DIRECTORY_PATH = "tokens";
    
    /**
    * The list of scopes required for Gmail access.
    */
    private static List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    
    /**
    * The path to the credentials JSON file.
    */
    private static String CREDENTIALS_FILE_PATH = "/online/syncio/config/credentials.json";
    
    /**
    * The controller for handling signup functionality.
    */
    private SignupController controller;
    private UserDAO userDAO;
    private int otp = -1;

    /**
    * Constructs a new Signup frame.
    * Initializes the database connection and sets up the UI components for signup.
    */
    public Signup() {
        MongoDBConnect.connect();
        this.userDAO = MongoDBConnect.getUserDAO();

        setUndecorated(true);
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        TextHelper.addPlaceholderText(txtEmail, "Email");
        TextHelper.addPlaceholderText(txtUsername, "Username");
        TextHelper.addPlaceholderText(txtPassword, "Password");
        TextHelper.addPlaceholderText(txtPasswordConfirm, "Password Confirm");

        txtEmail.requestFocus();

        this.controller = new SignupController(this);

        //press Enter => click btnLogin
        ActionHelper.assignEnterKeyListener(btnSignup, txtEmail, txtUsername, txtPassword, txtPasswordConfirm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        windowTitleBar1 = new online.syncio.component.WindowTitleBar();
        pnlMain = new online.syncio.component.MyPanel();
        pnlForm = new javax.swing.JPanel();
        lblTitle = new online.syncio.component.MyLabel();
        txtEmail = new online.syncio.component.MyTextField();
        btnSignup = new online.syncio.component.MyButton();
        lblLogin = new online.syncio.component.MyLabel();
        lblContinue = new online.syncio.component.MyLabel();
        txtUsername = new online.syncio.component.MyTextField();
        pnlPassword = new online.syncio.component.MyPanel();
        txtPassword = new online.syncio.component.MyPasswordField();
        txtPasswordConfirm = new online.syncio.component.MyPasswordField();
        btnContinueWithGoogle = new online.syncio.component.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Signup"); // NOI18N

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

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Sign up");
        lblTitle.setFont(new java.awt.Font("SF Pro Display Bold", 0, 36)); // NOI18N
        lblTitle.setFontBold(2);

        txtEmail.setName("txtEmail"); // NOI18N

        btnSignup.setBackground(new java.awt.Color(0, 149, 246));
        btnSignup.setForeground(new java.awt.Color(255, 255, 255));
        btnSignup.setText("Sign up");
        btnSignup.setBorderColor(new java.awt.Color(255, 255, 255));
        btnSignup.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnSignup.setName("btnSignup"); // NOI18N
        btnSignup.setPreferredSize(new java.awt.Dimension(92, 20));
        btnSignup.setRadius(10);
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        lblLogin.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("ALREADY HAVE AN ACCOUNT? LOG IN");
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
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

        txtUsername.setName("txtUsername"); // NOI18N

        pnlPassword.setBackground(new java.awt.Color(255, 255, 255));

        txtPassword.setText("myPasswordField1");
        txtPassword.setName("txtPassword"); // NOI18N

        txtPasswordConfirm.setText("myPasswordField1");
        txtPasswordConfirm.setName("txtPasswordConfirm"); // NOI18N

        javax.swing.GroupLayout pnlPasswordLayout = new javax.swing.GroupLayout(pnlPassword);
        pnlPassword.setLayout(pnlPasswordLayout);
        pnlPasswordLayout.setHorizontalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasswordLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        pnlPasswordLayout.setVerticalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasswordLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnContinueWithGoogle.setBackground(new java.awt.Color(254, 255, 255));
        btnContinueWithGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/google_28px.png"))); // NOI18N
        btnContinueWithGoogle.setText(" Sign up with Google");
        btnContinueWithGoogle.setBorderColor(new java.awt.Color(219, 219, 219));
        btnContinueWithGoogle.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnContinueWithGoogle.setPreferredSize(new java.awt.Dimension(92, 20));
        btnContinueWithGoogle.setRadius(10);
        btnContinueWithGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueWithGoogleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSignup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119))
                            .addComponent(pnlPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnContinueWithGoogle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnContinueWithGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        int type = evt.getActionCommand().equalsIgnoreCase("sign up") ? 0 : 1;
        controller.signupAuthentication(type);
    }//GEN-LAST:event_btnSignupActionPerformed

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_lblLoginMouseClicked

    private void btnContinueWithGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueWithGoogleActionPerformed
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
                GlassPanePopup.showPopup(new MyDialog("Account Already Exists", "This email is already connected to your Google Account.\nPlease use a different email or sign in with your Google account."), "dialog");
            } else if (u != null && !u.getPassword().equals("")) {
                GlassPanePopup.showPopup(new MyDialog("Account Already Exists", "Please sign in using your original username and password."), "dialog");
            } else {
                //sign up
                String username = TextHelper.generateUniqueUsernameFromEmail(userEmail);

                //gui email
                String subject = "WELCOME TO SYNCIO";
                String recipientName = userEmail;
                String content = "<tr>\n"
                        + "<td class=\"text-center\" style=\"padding: 80px 0 !important;\">\n"
                        + "<h4>" + subject + "</h4>\n"
                        + "<br>\n"
                        + "Dear " + recipientName + ",<br>\n"
                        + "Thank you for creating your personal account with the username <b>" + username + "</b> on SYNCIO.<br>\n"
                        + "</td>\n"
                        + "</tr>\n"
                        + "<tr>\n"
                        + "<td>\n"
                        + "<p class=\"text-center\">If you did not request to create an account, please ignore this email, no changes will be made to your account. Another user may have entered your username by mistake, but we encourage you to view our tips for Protecting Your Account if you have any concerns.</p>\n"
                        + "</td>\n"
                        + "</tr>\n";

                boolean sendStatus = SendEmail.sendFormat(userEmail, userEmail, subject, content);

                if (!sendStatus) {
                    GlassPanePopup.showPopup(new MyDialog("Error", "An error occurred while sending the email"), "dialog");
                } else {
                    // success send email
                    //add
                    boolean added = userDAO.add(new User(username, "", userEmail, null, 0, 0, null));
                    if (added) {
                        dispose();
                        new Login().setVisible(true);
                        GlassPanePopup.showPopup(new MyDialog("Account Created", "Your account with the username " + username + " has been successfully created."), "dialog");
                    }
                }
            }
        } catch (Exception ex) {
            String errorInfo = ex.getMessage();
            GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
        }
    }//GEN-LAST:event_btnContinueWithGoogleActionPerformed

    private void lblContinueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinueMousePressed
        dispose();
        new Main().setVisible(true);
    }//GEN-LAST:event_lblContinueMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        ActionHelper.registerShutdownHook(); // Register the shutdown hook

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }
    
    /**
    * Returns the MyLabel component representing the title label in the signup form.
    *
    * @return The MyLabel component for the title label.
    */
    public MyLabel getLblTitle() {
        return lblTitle;
    }
    
    /**
    * Returns the MyTextField component representing the email input field in the signup form.
    *
    * @return The MyTextField component for email input.
    */
    public MyTextField getTxtEmail() {
        return txtEmail;
    }
    
    /**
    * Returns the MyPasswordField component representing the password input field in the signup form.
    *
    * @return The MyPasswordField component for password input.
    */
    public MyPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
    * Returns the MyPasswordField component representing the password confirmation input field in the signup form.
    *
    * @return The MyPasswordField component for password confirmation input.
    */
    public MyPasswordField getTxtPasswordConfirm() {
        return txtPasswordConfirm;
    }

    /**
    * Returns the MyTextField component representing the username input field in the signup form.
    *
    * @return The MyTextField component for username input.
    */
    public MyTextField getTxtUsername() {
        return txtUsername;
    }
    
    /**
    * Returns the MyButton component representing the signup button in the signup form.
    *
    * @return The MyButton component for the signup button.
    */
    public MyButton getBtnSignup() {
        return btnSignup;
    }

    /**
    * Returns the MyPanel component representing the password panel in the signup form.
    *
    * @return The MyPanel component for the password panel.
    */
    public MyPanel getPnlPassword() {
        return pnlPassword;
    }

    /**
    * Sets the MyPanel component representing the password panel in the signup form.
    *
    * @param pnlPassword The MyPanel component for the password panel.
    */
    public void setPnlPassword(MyPanel pnlPassword) {
        this.pnlPassword = pnlPassword;
    }

    /**
    * Returns the UserDAO instance used for database operations.
    *
    * @return The UserDAO instance.
    */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
    * Returns the OTP value for verification.
    *
    * @return The OTP value.
    */
    public int getOtp() {
        return otp;
    }
    
    /**
    * Sets the OTP value for verification.
    *
    * @param otp The OTP value.
    */
    public void setOtp(int otp) {
        this.otp = otp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnContinueWithGoogle;
    private online.syncio.component.MyButton btnSignup;
    private online.syncio.component.MyLabel lblContinue;
    private online.syncio.component.MyLabel lblLogin;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyPanel pnlContainer;
    private javax.swing.JPanel pnlForm;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlPassword;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyPasswordField txtPasswordConfirm;
    private online.syncio.component.MyTextField txtUsername;
    private online.syncio.component.WindowTitleBar windowTitleBar1;
    // End of variables declaration//GEN-END:variables
}
