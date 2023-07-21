package online.syncio.view;

import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.controller.LoginController;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.TextHelper;

public class Login extends javax.swing.JFrame {

    private static Main main;
    private LoginController controller;
    private MongoDatabase database;
    private UserDAO userDAO;
    
    

    public Login() {
        this.database = MongoDBConnect.getDatabase();
        
        userDAO = new UserDAOImpl(database);

        setUndecorated(true);
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        TextHelper.addPlaceholderText(txtUser, "Username");
        TextHelper.addPlaceholderText(txtPassword, "Password");

        txtUser.requestFocus();
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnLogin.setBackground(new java.awt.Color(0, 149, 246));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorderColor(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnLogin.setPreferredSize(new java.awt.Dimension(92, 20));
        btnLogin.setRadius(10);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblForgetPassword.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblForgetPassword.setText("FORGOT PASSWORD");

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
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
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
                                .addGap(125, 125, 125))))))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(myLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblForgetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
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
                .addGap(100, 100, 100)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    
    
    public MyPasswordField getTxtPassword() {
        return txtPassword;
    }

    public MyTextField getTxtUser() {
        return txtUser;
    }
    
    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnLogin;
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
