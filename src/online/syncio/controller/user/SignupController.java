package online.syncio.controller.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.User;
import online.syncio.utils.SendEmail;
import online.syncio.utils.TextHelper;
import online.syncio.utils.Validator;
import online.syncio.view.login.Login;
import online.syncio.view.login.Signup;

/**
 * Controller class for handling user sign-up and email verification.
 */
public class SignupController {

    private Signup signup;
    private UserDAO userDAO;
    private String us;

    /**
     * Constructor for initializing the SignupController.
     *
     * @param signup The associated Signup view.
     */
    public SignupController(Signup signup) {
        this.signup = signup;

        MongoDBConnect.connect();
        this.userDAO = MongoDBConnect.getUserDAO();
    }

    /**
     * Performs sign-up authentication based on the provided type.
     *
     * @param type The type of authentication to perform.
     */
    public void signupAuthentication(int type) {
        if (type == 0) {
            signupAndSendVerificationEmail();
        } else if (type == 1) {
            verifyOTPAndCreateAccount();
        }
    }

    /**
     * Handles sign-up and sends a verification email.
     */
    private void signupAndSendVerificationEmail() {
        MyTextField txtEmail = signup.getTxtEmail();
        MyTextField txtUsername = signup.getTxtUsername();
        MyPasswordField txtPassword = signup.getTxtPassword();
        MyPasswordField txtPasswordConfirm = signup.getTxtPasswordConfirm();

        String email = txtEmail.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String passwordConfirm = new String(txtPasswordConfirm.getPassword()).trim();

        // validate inputs
        List<String> errors = validateSignupInputs(email, username, password, passwordConfirm);

        if (!errors.isEmpty()) {
            GlassPanePopup.showPopup(new MyDialog("Error", String.join("<br>", errors)), "dialog");
            return;
        }

        if (userDAO.checkEmail(email)) {
            GlassPanePopup.showPopup(new MyDialog("Email Address Already Taken", "The email address you entered is already taken.\nPlease use a different email address."), "dialog");
            txtEmail.requestFocus();
            return;
        } else if (userDAO.checkUsername(username)) {
            GlassPanePopup.showPopup(new MyDialog("Username Already Taken", "The username you've chosen is already taken.\nPlease select a different username."), "dialog");
            return;
        }

        // send verification email
        int otp = (int) (Math.random() * 900000) + 100000;
        signup.setOtp(otp);
        String subject = "WELCOME TO SYNCIO";
        String recipientName = email;
        String content = generateVerificationEmailContent(otp, subject, recipientName);

        boolean sendStatus = SendEmail.sendFormat(email, email, subject, content);

        if (!sendStatus) {
            GlassPanePopup.showPopup(new MyDialog("Error", "An error occurred while sending the email"), "dialog");
        } else {
            showVerificationPanel();
        }
    }

     /**
     * Validates the sign-up input fields.
     *
     * @param email           The email input.
     * @param username        The username input.
     * @param password        The password input.
     * @param passwordConfirm The password confirmation input.
     * @return A list of validation errors.
     */
    private List<String> validateSignupInputs(String email, String username, String password, String passwordConfirm) {
        List<String> errors = new ArrayList<>();
        errors.add(Validator.email(signup.getTxtEmail(), "Email", email, false, "Email"));
        errors.add(Validator.allowNumberTextUnderline(signup.getTxtUsername(), "Username", username, false, "Username"));
        errors.add(Validator.allowNumberText(signup.getTxtPassword(), "Password", password, false, "Password"));
        errors.add(Validator.allowNumberText(signup.getTxtPasswordConfirm(), "Password Confirm", passwordConfirm, false, "Password Confirm"));

        if (!password.equals(passwordConfirm)) {
            errors.add("Password and Password Confirm don't match");
        }

        if (password.length() < 4 || password.length() > 30) {
            errors.add("Your password must contain between 4 and 30 characters");
        }

        Iterator<String> iterator = errors.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.isBlank()) {
                iterator.remove();
            }
        }

        return errors;
    }

     /**
     * Generates the content of the verification email.
     *
     * @param otp           The OTP code.
     * @param subject       The subject of the email.
     * @param recipientName The name of the recipient.
     * @return The email content.
     */
    private String generateVerificationEmailContent(int otp, String subject, String recipientName) {
        return """
               <tr>
               <td class="text-center" style="padding: 80px 0 !important;">
               <h4>""" + subject + "</h4>\n"
                + "<br>\n"
                + "Dear " + recipientName + ",<br>\n"
                + "Thank you for creating your personal account on SYNCIO.<br>\n"
                + "<br><br>\n"
                + "Your OTP code is:\n"
                + "<br><br>\n"
                + "<h2>" + otp + "</h2>\n"
                + "</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<td>\n"
                + "<p class=\"text-center\">If you did not request to create an account, please ignore this email, no changes will be made to your account. Another user may have entered your username by mistake, but we encourage you to view our tips for Protecting Your Account if you have any concerns.</p>\n"
                + "</td>\n"
                + "</tr>\n";
    }

    /**
     * Shows the verification panel after sending the email.
     */
    private void showVerificationPanel() {
        TextHelper.addPlaceholderText(signup.getTxtUsername(), "OTP");
        signup.getLblTitle().setText("Email Verification");
        signup.getBtnSignup().setText("Verify");
        signup.getPnlPassword().setVisible(false);
        signup.getTxtEmail().setEditable(false);
        GlassPanePopup.showPopup(new MyDialog("Email Sent with OTP", "We have sent an email with OTP code.\nIf you haven't received the email, please check your spam folder.\nYour OTP will expire when you close the app."), "dialog");
        signup.getTxtUsername().requestFocus();
    }

    /**
     * Verifies the entered OTP and creates the user account.
     */
    private void verifyOTPAndCreateAccount() {
        String otp = signup.getTxtUsername().getText();

        if (otp.matches("[0-9]{6}")) {
            if (otp.equals(String.valueOf(signup.getOtp()))) {
                // hash password
                String hashedPassword = TextHelper.HashPassword(new String(signup.getTxtPassword().getPassword()).trim());
                String email = signup.getTxtEmail().getText().trim();

                boolean result = userDAO.add(new User(us, hashedPassword, email, null, 0, 0, null));

                if (result) {
                    signup.dispose();
                    new Login().setVisible(true);
                    GlassPanePopup.showPopup(new MyDialog("Account Created", "Your account has been successfully created."), "dialog");
                }
            } else {
                GlassPanePopup.showPopup(new MyDialog("Wrong OTP Code", "Please enter the correct OTP code."), "dialog");
            }
        } else {
            GlassPanePopup.showPopup(new MyDialog("Error", "Please enter OTP (6 digits long)"), "dialog");
            signup.getTxtUsername().requestFocus();
        }
    }
}
