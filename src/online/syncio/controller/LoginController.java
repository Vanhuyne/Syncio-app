package online.syncio.controller;

import com.mongodb.client.MongoDatabase;
import java.util.HashSet;
import java.util.Set;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPasswordField;
import online.syncio.component.MyTextField;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import online.syncio.view.Login;
import online.syncio.view.Main;
import online.syncio.view.MainAdmin;

public class LoginController {

    private Login login;
    private MongoDatabase database;
    private UserDAO userDAO;

    public LoginController(Login login) {
        this.login = login;
        this.database = MongoDBConnect.getDatabase();
        userDAO = new UserDAOImpl(database);
    }

    public void loginAuthentication() {
        MyTextField txtUser = login.getTxtUser();
        MyPasswordField txtPassword = login.getTxtPassword();

        String username = txtUser.getText();
        String password = new String(txtPassword.getPassword());

        Set<String> setError = new HashSet<>();

        //txtUser
        if (username.isEmpty() || username.equalsIgnoreCase("username")) {
            setError.add("Please enter username");
            txtUser.requestFocus();
        } else if (!username.matches("[a-zA-Z0-9_]+")) {
            setError.add("Username should only allow letters, digits and underscores (a-zA-Z0-9_).");
            txtUser.requestFocus();
        }

        //txtPassword
        if (password.isEmpty() || password.equalsIgnoreCase("password")) {
            setError.add("Please enter password");
            txtPassword.requestFocus();
        } else if (!password.matches("[a-zA-Z0-9]+")) {
            setError.add(" should only allow letters and digits (a-zA-Z0-9).");
            txtPassword.requestFocus();
        }

        if (setError.isEmpty()) {
            User user = userDAO.authentication(username, password);
            if (user == null) {
                GlassPanePopup.showPopup(new MyDialog("Incorrect Username or Password", "The username or password you entered is incorrect.\nPlease double-check and try again."), "dialog");
                return;
            }

            if (user.getFlag() == 1) {
                GlassPanePopup.showPopup(new MyDialog("Account Unavailable", "We're sorry, but your account is currently unavailable.\nPlease try again later or contact support for assistance."), "dialog");
            } else {
                LoggedInUser.setCurrentUser(user); //set loggedin user

                // check role
                if (LoggedInUser.isAdmin()) {
                    MainAdmin mainAdmin = new MainAdmin();
                    mainAdmin.setConnection(this.database, user);
                    mainAdmin.setVisible(true);

                    login.dispose();
                } else {
                    Main main = new Main();
                    main.setConnection(this.database);
                    main.setVisible(true);

                    login.dispose();
                }
            }
        } else {
            //neu co loi => hien thi loi
            String errors = "";
            for (String error : setError) {
                errors += error + "<br>";
            }
            GlassPanePopup.showPopup(new MyDialog("Error", errors), "dialog");
        }
    }
}
