package online.syncio.view;

import online.syncio.controller.ForgotController;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;

import javax.swing.BorderFactory;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyButton;
import online.syncio.component.MyLabel;
import online.syncio.component.MyPanel;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;

import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.TextHelper;

public class Forgot extends javax.swing.JFrame {

    private static Main main;
    private ForgotController controller;
    private MongoDatabase database;
    private UserDAO userDAO;
    private int otp = -1;

    public Forgot() {
        this.database = MongoDBConnect.getDatabase();

        userDAO = new UserDAOImpl(database);

        setUndecorated(true);
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        GlassPanePopup.install(this);
        setLocationRelativeTo(null);
        TextHelper.addPlaceholderText(txtEmail, "Email");
        TextHelper.addPlaceholderText(txtOTP, "OTP");
        TextHelper.addPlaceholderText(txtPassword, "Password");
        TextHelper.addPlaceholderText(txtPasswordConfirm, "Password");

        txtEmail.requestFocus();

        this.controller = new ForgotController(this);

        txtOTP.setVisible(false);
        txtPassword.setVisible(false);
        txtPasswordConfirm.setVisible(false);
        //press Enter => click btnLogin
        ActionHelper.assignEnterKeyListener(btnGetOTP, txtEmail, txtOTP, txtPassword, txtPasswordConfirm);
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
        btnGetOTP = new online.syncio.component.MyButton();
        lblLogin = new online.syncio.component.MyLabel();
        txtOTP = new online.syncio.component.MyTextField();
        txtPasswordConfirm = new online.syncio.component.MyPasswordField();
        lblNote = new online.syncio.component.MyLabel();
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

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Enter your Email");
        lblTitle.setFont(new java.awt.Font("SF Pro Display Bold", 0, 34)); // NOI18N
        lblTitle.setFontBold(2);

        btnGetOTP.setBackground(new java.awt.Color(0, 149, 246));
        btnGetOTP.setForeground(new java.awt.Color(255, 255, 255));
        btnGetOTP.setText("Get OTP");
        btnGetOTP.setBorderColor(new java.awt.Color(255, 255, 255));
        btnGetOTP.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnGetOTP.setPreferredSize(new java.awt.Dimension(92, 20));
        btnGetOTP.setRadius(10);
        btnGetOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetOTPActionPerformed(evt);
            }
        });

        lblLogin.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("BACK TO LOGIN");
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
        });

        txtPasswordConfirm.setText("myPasswordField1");

        lblNote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNote.setText("The one that you use to sign in with");
        lblNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNoteMouseClicked(evt);
            }
        });

        txtPassword.setText("myPasswordField1");

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGetOTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOTP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNote, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnGetOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
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
                .addGap(52, 52, 52)
                .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetOTPActionPerformed
        String type = evt.getActionCommand();
        controller.forgotAuthentication(type);
    }//GEN-LAST:event_btnGetOTPActionPerformed

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
//        dispose();
//
//        boolean check = false; // true if open form HOME
//        Window[] windows = Window.getWindows();
//        for (Window window : windows) {
//            if (window instanceof Main mainWindow && window.isVisible()) {
//                check = true;
//            }
//        }
//
//        if (!check) {
//            new Main().setVisible(true);
//        }        
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_lblLoginMouseClicked

    private void lblNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNoteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNoteMouseClicked

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
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Forgot().setVisible(true);
            }
        });
    }

    public MyLabel getLblTitle() {
        return lblTitle;
    }

    public MyLabel getLblNote() {
        return lblNote;
    }

    public MyTextField getTxtEmail() {
        return txtEmail;
    }

    public MyPasswordField getTxtPassword() {
        return txtPassword;
    }

    public MyPasswordField getTxtPasswordConfirm() {
        return txtPasswordConfirm;
    }

    public MyTextField getTxtOTP() {
        return txtOTP;
    }

    public MyButton getbtnSumbit() {
        return btnGetOTP;
    }

//    public MyPanel getPnlPassword() {
//        return pnlPassword;
//    }

//    public void setPnlPassword(MyPanel pnlPassword) {
//        this.pnlPassword = pnlPassword;
//    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnGetOTP;
    private online.syncio.component.MyLabel lblLogin;
    private online.syncio.component.MyLabel lblNote;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyPanel pnlContainer;
    private javax.swing.JPanel pnlForm;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyTextField txtEmail;
    private online.syncio.component.MyTextField txtOTP;
    private online.syncio.component.MyPasswordField txtPassword;
    private online.syncio.component.MyPasswordField txtPasswordConfirm;
    private online.syncio.component.WindowTitleBar windowTitleBar1;
    // End of variables declaration//GEN-END:variables
}
