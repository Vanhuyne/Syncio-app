package online.syncio.controller;

import java.util.ArrayList;
import javax.swing.JTextField;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.dao.UserDAO;
import online.syncio.model.User;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;
import online.syncio.utils.Validator;
import online.syncio.view.Login;
import online.syncio.view.Signup;

public class SignupController {

    private Signup signup;
    UserDAO userDAO;
    private String us; 

    
    
    public SignupController(Signup signup) {
        this.signup = signup;

        userDAO = this.signup.getUserDAO();
    }

    
    
    public void signupAuthentication(int type) {
        // 0: sigup and 1: otp verification
        
        MyTextField txtEmail = signup.getTxtEmail();
        MyTextField txtUsername = signup.getTxtUsername();
        MyPasswordField txtPassword = signup.getTxtPassword();
        MyPasswordField txtPasswordConfirm = signup.getTxtPasswordConfirm();
        
        String email = txtEmail.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String passwordConfirm = new String(txtPasswordConfirm.getPassword()).trim();

        //signup or verification email
        if (type == 0) {
            //sign up
            us = username; //save current username
            
            //validate
            ArrayList<String> errors = new ArrayList<>();
            errors.add(Validator.email((JTextField)txtEmail, "Email", email, false, "Email"));
            errors.add(Validator.allowNumberTextUnderline((JTextField)txtUsername, "Username", username, false, "Username"));
            errors.add(Validator.allowNumberText((JTextField)txtPassword, "Password", password, false, "Password"));
            errors.add(Validator.allowNumberText((JTextField)txtPasswordConfirm, "Password Confirm", passwordConfirm, false, "Password Confirm"));
            if(!password.equals(passwordConfirm)) errors.add("Password and Password Confirm don't match");
            if(password.length() < 4 || password.length() > 30) errors.add("Your password must contain between 4 and 30 characters");
            
            String e = "";
            for(String s : errors) e += (s != "") ? s + "<br>" : "";

            //co loi
            if(!e.isEmpty()) {
                GlassPanePopup.showPopup(new MyDialog("Error", e), "dialog");
                return;
            }

            //check exist
            if (userDAO.checkEmail(email)) {
                GlassPanePopup.showPopup(new MyDialog("Email Address Already Taken", "The email address you entered is already taken.\nPlease use a different email address."), "dialog");
                txtEmail.requestFocus();
                return;
            }
            else if (userDAO.checkUsername(username)) {
                GlassPanePopup.showPopup(new MyDialog("Username Already Taken", "The username you've chosen is already taken.\nPlease select a different username."), "dialog");
                return;
            }
        
            //gui email
            int o = (int) (Math.random() * 900000) + 100000;
            signup.setOtp(o);
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

            if (!sendStatus) {
                GlassPanePopup.showPopup(new MyDialog("Error", "An error occurred while sending the email"), "dialog");
                return;
            }
            else {
                //chuyen sang verification
                
                TextHelper.addPlaceholderText(signup.getTxtUsername(), "OTP");
                signup.getLblTitle().setText("Email Verification");
                signup.getBtnSignup().setText("Verify");
                signup.getPnlPassword().setVisible(false);
                signup.getTxtEmail().setEditable(false);
                GlassPanePopup.showPopup(new MyDialog("Email Sent with OTP", "We have sent an email with OTP code.\nIf you haven't received the email, please check your spam folder.\nYour OTP will expire when you close the app."), "dialog");
                txtUsername.requestFocus();
            }
        }
        else if (type == 1) {
            //verification
            
            String otp = txtUsername.getText();

            //validate
            if(otp.matches("[0-9]{6}")) { // match
                if(otp.equals(signup.getOtp() + "")) {
                    // hash password
                    password = TextHelper.HashPassword(password);

                    boolean result = userDAO.add(new User(us, password, email, null, 0, 0, null));

                    if (result) {
                        signup.dispose();
                        new Login().setVisible(true);
                        GlassPanePopup.showPopup(new MyDialog("Account Created", "Your account has been successfully created."), "dialog");
                    }
                }
                else {
                    GlassPanePopup.showPopup(new MyDialog("Wrong OTP Code", "Please enter the correct OTP code."), "dialog");
                }
            }
            else { //empty, wrong format
                GlassPanePopup.showPopup(new MyDialog("Error", "Please enter OTP (6 digits long)"), "dialog");
                txtUsername.requestFocus();
            }
        }
        
    }
}
