package online.syncio.controller;

import java.util.HashSet;
import java.util.Set;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.view.Login;
import online.syncio.view.Main;

public class LoginController {

    private Login login;
    private UserDAO userDAO;

    
    
    public LoginController(Login login) {
        this.login = login;

        userDAO = this.login.getUserDAO();
    }

    
    
    public void loginAuthentication() {
        MyTextField txtUser = login.getTxtUser();
        MyPasswordField txtPassword = login.getTxtPassword();
        
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
            User user = userDAO.authentication(username, password);
            if (user != null ) {
                LoggedInUser.setCurrentUser(user); //set loggedin user
//                GlassPanePopup.showPopup(new MyDialog("Success", "Logged in successfully"), "dialog");
                // check role
                if (LoggedInUser.isAdmin()) {
//                    new AdminHome();
                } else {
                    new Main().setVisible(true);
                    login.dispose();
                }
            } else {
                GlassPanePopup.showPopup(new MyDialog("Error", "Wrong account or password"), "dialog");
            }

        }
    }
}
