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

/**
 *
 * @author DELL
 */
public class Login extends javax.swing.JFrame {

    List<User> listUsers = new ArrayList<>();

    public Login() {
        initComponents();
        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        addPlaceholderText(txtUser, "Username");
        addPlaceholderText(txtPassword, "Password");

        txtUser.requestFocus();
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
        myLabel1 = new online.syncio.component.MyLabel();
        txtUser = new online.syncio.component.MyTextField();
        btnLogin = new online.syncio.component.MyButton();
        lblForgetPassword = new online.syncio.component.MyLabel();
        lblCreateAccount = new online.syncio.component.MyLabel();
        lblContinue = new online.syncio.component.MyLabel();
        txtPassword = new online.syncio.component.MyPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setPreferredSize(new java.awt.Dimension(412, 454));

        myLabel1.setText("Login");
        myLabel1.setFont(new java.awt.Font("SF Pro Display Medium", 0, 36)); // NOI18N

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(0, 149, 246));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorderColor(new java.awt.Color(255, 255, 255));
        btnLogin.setPreferredSize(new java.awt.Dimension(92, 20));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblForgetPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblForgetPassword.setText("FORGOT PASSWORD");

        lblCreateAccount.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblCreateAccount.setText("CREATE ACCOUNT");

        lblContinue.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblContinue.setText("CONTINUE AS A GUEST");

        txtPassword.setText("myPasswordField1");

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblForgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                        .addGap(0, 132, Short.MAX_VALUE)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addComponent(lblCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125))))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblForgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(lblCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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
                .addGap(130, 130, 130)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
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

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed

    }//GEN-LAST:event_txtUserActionPerformed

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        String username = txtUser.getText();
        String password = new String(txtPassword.getPassword());

        Set<String> setError = new HashSet<String>();
        //txtUser
        if (username.isEmpty() || username.equalsIgnoreCase("username")) {
            setError.add("Please enter a username");
            txtUser.requestFocus();
        } else if (!username.matches("[a-zA-Z0-9]+")) {
            setError.add("Username can only contain the characters [a-zA-Z0-9]");
            txtUser.requestFocus();
        }
        //txtPassword
        if (password.isEmpty() || password.equalsIgnoreCase("password")) {
            setError.add("Please enter a password");
            txtPassword.requestFocus();
        } else if (!password.matches("[a-zA-Z0-9]+")) {
            setError.add("Password does not contain special characters");
            txtPassword.requestFocus();
        }
        if (!setError.isEmpty()) {
            //neu co loi => hien thi loi
            String errors = "";
            for (String error : setError) {
                errors += error + "<br>";
            }
            GlassPanePopup.showPopup(new MyDialog("Error", errors), "dialog");

        } // dang nhap thanh cong
        else {
            UserDAO userDAO = new UserDAOImpl(MongoDBConnect.getDatabase());
            User user = userDAO.authentication(username, password);
                if (user != null ) {
                    LoggedInUser.setCurrentUser(user); //set loggedin user
                    GlassPanePopup.showPopup(new MyDialog("Success", "Logged in successfully"), "dialog");
                    // set role
                    if (LoggedInUser.isAdmin()) {
//                       new AdminHome();
                    } else {
                        new Main().setVisible(true);
                        dispose();
                    }
                } else {
                    GlassPanePopup.showPopup(new MyDialog("Error", "Wrong account or password"), "dialog");
                }
            
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnLogin;
    private javax.swing.JPanel jPanel1;
    private online.syncio.component.MyLabel lblContinue;
    private online.syncio.component.MyLabel lblCreateAccount;
    private online.syncio.component.MyLabel lblForgetPassword;
    private online.syncio.component.MyLabel myLabel1;
    private javax.swing.JPanel pnlForm;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
