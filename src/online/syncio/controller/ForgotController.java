package online.syncio.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JTextField;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.dao.UserDAO;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;
import online.syncio.utils.Validator;
import online.syncio.view.Forgot;
import online.syncio.view.Login;

public class ForgotController {
    private long otpRequestedTimeMillis;
    private final long OTP_VALID = 3 * 60 * 1000;
    private Date otpRequestedTime = new Date();
    private Forgot forgot;
    private UserDAO userDAO;
//    private String us;
    String emailGLOBAL = null;
    int otp = -1;
    
    long currentTimeInMillis = 0;
    public ForgotController(Forgot forgot) {
        this.forgot = forgot;
        userDAO = this.forgot.getUserDAO();
    }
     public void forgotAuthentication(String btntype) {
        // btntype -> Acction(String)
        forgot.getbtnSumbit().setText("Get OTP");
        MyTextField txtEmail = forgot.getTxtEmail();
        MyTextField txtOTP = forgot.getTxtOTP();
        MyPasswordField txtPassword = forgot.getTxtPassword();
        MyPasswordField txtPasswordConfirm = forgot.getTxtPasswordConfirm();
        
        ArrayList<String> errors = new ArrayList<>();
        if (btntype.equalsIgnoreCase("Get otp")) {
            otpRequestedTimeMillis = this.otpRequestedTime.getTime();
//            System.out.println("get otp " + otpRequestedTimeMillis);
            
            //get OTP
            String email = txtEmail.getText();
            emailGLOBAL = email;
            errors.add(Validator.email((JTextField)txtEmail, "Email", email, false, "Email"));
            Collections.reverse(errors);
            String e = "";
            for(String s : errors) e += s;
            
            //co loi
            if(!e.isEmpty()) {
                GlassPanePopup.showPopup(new MyDialog("Error", e), "dialog");
                return;
            }
            else {
               
                //gui email
                //neu co thi gui
                if (userDAO.checkEmail(email)) {
                    int o = (int) (Math.random() * 900000) + 100000;
                    otp = o;
                    String subject = "RESET PASSWORD";
                    String recipientName = email;
                    String content = "<tr>\n"
                              + "<td class=\"text-center\" style=\"padding: 80px 0 !important;\">\n"
                              + "<h4>" + subject + "</h4>\n"
                              + "<br>\n"
                              + "Dear " + recipientName + ",<br>\n"
                              + "We've received your request to reset your password for your<br>SYNCIO account.<br>\n"
                              + "<br><br>\n"
                              + "Your OTP code is:\n"
                              + "<br><br>\n"
                              + "<h2>" + o + "</h2>\n"
                              + "</td>\n"
                              + "</tr>\n"
                              + "<tr>\n"
                              + "<td>\n"
                              + "<p class=\"text-center\">If you did not request to reset your password, please ignore this email, no changes will be made to your account. Another user may have entered your username by mistake, but we encourage you to view our tips for Protecting Your Account if you have any concerns.</p>\n"
                              + "</td>\n"
                              + "</tr>\n";
                    
                    boolean sendStatus = SendEmail.sendFormat(email, email, subject, content);
                    
                    //insert OTP into user
                    if (!sendStatus) {
                        GlassPanePopup.showPopup(new MyDialog("Error system", "An error occurred while sending the email"), "dialog");
                        return;
                    }
                }
            
                //sau khi gui => chuyen sang xac nhan otp
                forgot.getLblNote().setText("<html><p style='text-align: center'>We sent an OTP code to reset your password. If you have not received an email, please check your spam folder. Your OTP will expire in 3 minutes.</p></html>");
                forgot.getTxtEmail().setEnabled(false);
                forgot.getTxtOTP().setVisible(true);
                forgot.getTxtOTP().requestFocus();
                forgot.getbtnSumbit().setText("Verify Code");
            }
        } else if (btntype.equalsIgnoreCase("Verify Code")) {
            
            currentTimeInMillis = System.currentTimeMillis();
//            System.out.println("current "+ currentTimeInMillis);
            //submit otp
            if (txtOTP.getText().equals(otp + "") && txtOTP.getText().matches("[0-9]{6}")) {
                    
                if(otpRequestedTimeMillis + OTP_VALID < currentTimeInMillis) {

                    // otp expires
                    otpRequestedTimeMillis = 0;                  
                    forgot.dispose();
                    new Forgot().setVisible(true);
                    GlassPanePopup.showPopup(new MyDialog("Error code", "Expired code. Please get OTP again"), "dialog");
   
                }else {
                    forgot.getTxtPassword().setVisible(true);
                    txtPassword.requestFocus();
                    forgot.getTxtPasswordConfirm().setVisible(true);
                    forgot.getTxtEmail().setVisible(false);
                    forgot.getTxtOTP().setVisible(false);
                    forgot.getLblTitle().setText("Enter your new password");
                    forgot.getLblNote().setText("<html><p style='text-align: center'>Now you need to change password.</p></html>");
                    forgot.getbtnSumbit().setText("Reset Password");
                }
            } else {
                GlassPanePopup.showPopup(new MyDialog("Error code", "Wrong code. Try again."), "dialog");

                forgot.getTxtOTP().requestFocus();
            }
        } 
        else if (btntype.equalsIgnoreCase("Reset Password")) {
            String password = new String(txtPassword.getPassword());
            String confirmpassword = new String(txtPasswordConfirm.getPassword());

            //txtPassword vs confirmpassword
            errors.add(Validator.allowNumberText((JTextField)txtPassword, "Password", password, false, "Password"));
            errors.add(Validator.allowNumberText((JTextField)txtPasswordConfirm, "Password Confirm", confirmpassword, false, "Password Confirm"));
            if(!password.equals(confirmpassword)) errors.add("Password and Password Confirm don't match.");
            txtPassword.requestFocus();
            
            Collections.reverse(errors);
            String e = "";
            for(String s : errors) e += s;
            
            if (!e.isEmpty()) {
                GlassPanePopup.showPopup(new MyDialog("Error", e), "dialog");
                return;
            } else {
                
                password = TextHelper.HashPassword(password);
                int rowAffected = userDAO.updateByEmail(password, emailGLOBAL);

                if (rowAffected > 0) {
                    System.out.println("2");
                    //update thanh cong

                    forgot.dispose();
                    GlassPanePopup.showPopup(new MyDialog("Success", "Your password has been reset successfully"), "dialog");
                    new Login().setVisible(true);
                } else {
                    
                    //update that bai

                   GlassPanePopup.showPopup(new MyDialog("Error", "There is an error"), "dialog");
                }
            }
        }
        
    }   
}
