package online.syncio.view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;

import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;

/**
 *
 * @author DELL
 */
public class Signup extends javax.swing.JFrame {

    List<User> listUsers = new ArrayList<>();
    int otp = -1;

    public Signup() {
        initComponents();
        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        addPlaceholderText(txtUser, "Username");
        addPlaceholderText(txtEmail, "Huy@gmail.com");
        addPlaceholderText(txtPassword, "Password");
        addPlaceholderText(txtPassword2, "Password");
        addPlaceholderText(txtOTP, "OTP");
        
        txtEmail.requestFocus();
        txtOTP.setVisible(false);
    }

    private void addPlaceholderText(JTextField textField, String placeholderText) {
        // Save the default foreground color of the text field
        Color defaultColor = textField.getForeground();

        // Set the placeholder text
        textField.setText(placeholderText);

        // Add a focus listener to handle the placeholder text
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(defaultColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        txtEmail = new online.syncio.component.MyTextField();
        txtUser = new online.syncio.component.MyTextField();
        btnSignup = new online.syncio.component.MyButton();
        txtPassword = new online.syncio.component.MyPasswordField();
        txtPassword2 = new online.syncio.component.MyPasswordField();
        myLabel1 = new online.syncio.component.MyLabel();
        lblContinue = new online.syncio.component.MyLabel();
        lblAlreadyAcount = new online.syncio.component.MyLabel();
        txtOTP = new online.syncio.component.MyTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setPreferredSize(new java.awt.Dimension(412, 454));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        btnSignup.setBackground(new java.awt.Color(0, 149, 246));
        btnSignup.setForeground(new java.awt.Color(255, 255, 255));
        btnSignup.setText("Sign up");
        btnSignup.setBorderColor(new java.awt.Color(255, 255, 255));
        btnSignup.setPreferredSize(new java.awt.Dimension(92, 20));
        btnSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSignupMouseClicked(evt);
            }
        });
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        txtPassword.setText("myPasswordField1");

        txtPassword2.setText("myPasswordField1");
        txtPassword2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassword2ActionPerformed(evt);
            }
        });

        myLabel1.setText("Sign up");
        myLabel1.setFont(new java.awt.Font("SF Pro Display Medium", 0, 36)); // NOI18N

        lblContinue.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblContinue.setText("CONTINUE AS A GUEST");

        lblAlreadyAcount.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblAlreadyAcount.setText("ALREADY HAVE AN ACCOUNT? LOG IN");
        lblAlreadyAcount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlreadyAcountMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addComponent(lblAlreadyAcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))))
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOTP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSignup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAlreadyAcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        String username = txtUser.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String confirmpassword = new String(txtPassword2.getPassword());

        String btnType = evt.getActionCommand();
        Set<String> setError = new HashSet<String>();

        UserDAO userDAO = new UserDAOImpl(MongoDBConnect.getDatabase());
        User user = userDAO.authentication(username, password);
        //txtEmail
        if (email.isEmpty() || email.equalsIgnoreCase("email")) {
            setError.add("Please enter Email");
            txtEmail.requestFocus();
        } else if (!email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")) {
            setError.add("Email is wrong format");
            txtEmail.requestFocus();
        } 
        
        // txtUsername
        if (username.isEmpty() || username.equalsIgnoreCase("username")) {
            setError.add("Please enter Username");
            txtUser.requestFocus();
        } else if (!username.matches("[a-zA-Z0-9]+")) {
            setError.add("Username is wrong format");
            txtUser.requestFocus();
        }
        
        //txtPassword
        if (password.isEmpty() || password.equalsIgnoreCase("password")) {
            setError.add("Please enter Password");
            txtPassword.requestFocus();

        } else if (!password.matches("[a-zA-Z0-9]+")) {
            setError.add("Password can only contain the characters [a-zA-Z0-9]");
            txtPassword.requestFocus();
        }
        // txt confirm password
        if (confirmpassword.isEmpty() || confirmpassword.equalsIgnoreCase("Confirm Password")) {
            setError.add("Please enter Confirm Password");
            txtPassword2.requestFocus();
        }   
        if (confirmpassword.length() != password.length()) { 
            setError.add("Confirm Password is not the same as Password");
            txtPassword2.requestFocus();
        }    
        if (!setError.isEmpty()) {
            //neu co loi => hien thi loi
            String errors = "";
            for (String error : setError) {
                errors += error + "<br>";
            }

            GlassPanePopup.showPopup(new MyDialog("Error", errors), "dialog");
        } else {
            if (userDAO.checkEmail(email)) {
                GlassPanePopup.showPopup(new MyDialog("Error", "Email already exists"), "dialog");
                txtEmail.requestFocus();
                return;
            }
            
            if (btnType.equalsIgnoreCase("Sign up") && user == null ) {
                //gui email
                int o = (int) (Math.random() * 900000) + 100000;
                otp = o;
                String subject = "WELCOME TO SYNCIO";
                String recipientName = email;
                String content = "<tr>\n"
                          + "<td class=\"text-center\" style=\"padding: 80px 0 !important;\">\n"
                          + "<h4>" + subject + "</h4>\n"
                          + "<br>\n"
                          + "Dear " + recipientName + ",<br>\n"
                          + "Thank you for creating your personal account on SYNCIO.<br>\n"
                          + "<br><br>\n"
                          + "Your OTP code is:\n"
                          + "<br><br>\n"
                          + "<h2>" + o + "</h2>\n"
                          + "</td>\n"
                          + "</tr>\n"
                          + "<tr>\n"
                          + "<td>\n"
                          + "<p class=\"text-center\">If you did not request to create an account, please ignore this email, no changes will be made to your account. Another user may have entered your username by mistake, but we encourage you to view our tips for Protecting Your Account if you have any concerns.</p>\n"
                          + "</td>\n"
                          + "</tr>\n";
                
                boolean sendStatus = SendEmail.sendFormat(email, email, subject, content);
                //insert OTP into user
                
                if (!sendStatus) {
                    GlassPanePopup.showPopup(new MyDialog("Error", "An error occurred while sending the email"), "dialog");
                    return;
                }
                //sau khi gui => chuyen sang xac nhan otp
                txtEmail.setEnabled(true);
                txtUser.setVisible(false);
                txtPassword.setVisible(false);
                txtPassword2.setVisible(false);
                txtOTP.setVisible(true);
                txtOTP.requestFocus();
                btnSignup.setText("Verify Code");
                System.out.println("1");
            } else if (btnType.equalsIgnoreCase("Verify Code")) {
                if (txtOTP.getText().equals(otp + "") && txtOTP.getText().matches("[0-9]{6}")) {

                    // hash password
                    password = TextHelper.HashPassword(password);

                    boolean result = userDAO.add(new User(username, password, email, null , 0,  0, null ));
                    
                    if (result) {
                        dispose();
                        new Login().setVisible(true);
                        GlassPanePopup.showPopup(new MyDialog("Success", "SignUp Success."), "dialog");
                    }
                } else {
                    GlassPanePopup.showPopup(new MyDialog("Error", "Wrong code. Try again."), "dialog");
                    txtOTP.requestFocus();
                }
                
            }else {
                GlassPanePopup.showPopup(new MyDialog("Error", "Username already exists."), "dialog");
            }
        }    
    }//GEN-LAST:event_btnSignupActionPerformed
    
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseClicked

    }//GEN-LAST:event_btnSignupMouseClicked

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtPassword2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassword2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassword2ActionPerformed

    private void lblAlreadyAcountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlreadyAcountMouseClicked
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_lblAlreadyAcountMouseClicked

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnSignup;
    private javax.swing.JPanel jPanel1;
    private online.syncio.component.MyLabel lblAlreadyAcount;
    private online.syncio.component.MyLabel lblContinue;
    private online.syncio.component.MyLabel myLabel1;
    private javax.swing.JPanel pnlForm;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyTextField txtOTP;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyPasswordField txtPassword2;
    private online.syncio.component.MyTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
