package online.syncio.controller;


import java.util.ArrayList;
import java.util.Collections;

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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class ForgotController {
    private Forgot forgot;
    private UserDAO userDAO;
    private String us;
    String emailGLOBAL = null;
    int otp = -1;
    public ForgotController(Forgot forgot) {
        this.forgot = forgot;
        userDAO = this.forgot.getUserDAO();
    }
     public void forgotAuthentication(String btntype) {
        // btntype -> Acction(String)
        
        MyTextField txtEmail = forgot.getTxtEmail();
        MyTextField txtOTP = forgot.getTxtOTP();
        MyPasswordField txtPassword = forgot.getTxtPassword();
        MyPasswordField txtPasswordConfirm = forgot.getTxtPasswordConfirm();
        
        
        ArrayList<String> errors = new ArrayList<>();
        if (btntype.equalsIgnoreCase("get otp")) {
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
                if (!userDAO.checkEmail(email)) {
                    GlassPanePopup.showPopup(new MyDialog("Email Address Already Taken", "The email address you entered is already taken.\nPlease use a different email address."), "dialog");
                    txtEmail.requestFocus();
                    return;
                }

                else {
                    //co

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
                              + "<p class=\"text-center\">If you did not request to reset your password, please ignore this email, no changes will be made to your account. Another player may have entered your username by mistake, but we encourage you to view our tips for Protecting Your Account if you have any concerns.</p>\n"
                              + "</td>\n"
                              + "</tr>\n";
                    System.out.println("1");
                    boolean sendStatus = SendEmail.sendFormat(email, email, subject, content);
                    
                    //insert OTP into user
                    if (!sendStatus) {
                        
                        GlassPanePopup.showPopup(new MyDialog("Error system", "An error occurred while sending the email"), "dialog");
                        return;
                    }
                }
            
                //sau khi gui => chuyen sang xac nhan otp
                forgot.getLblNote().setText("<html><p style='text-align: center'>If your username matches an existing account we will send a password reset email within a few minutes.If you have not received an email check your spam folder.</p></html>");
                forgot.getTxtEmail().setEnabled(false);
                forgot.getTxtOTP().setVisible(true);
                forgot.getTxtOTP().requestFocus();
                forgot.getbtnSumbit().setText("Verify Code");
            }
        } else if (btntype.equalsIgnoreCase("Verify Code")) {
            //submit otp
            if (txtOTP.getText().equals(otp + "") && txtOTP.getText().matches("[0-9]{6}")) {
                forgot.getTxtPassword().setVisible(true);
                txtPassword.requestFocus();
                forgot.getTxtPasswordConfirm().setVisible(true);
                forgot.getTxtEmail().setVisible(false);
                forgot.getTxtOTP().setVisible(false);
                forgot.getLblTitle().setText("Enter your new password");
                forgot.getbtnSumbit().setText("Reset Password");
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
