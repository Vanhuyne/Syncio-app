package online.syncio.controller.user;

import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.utils.OtherHelper;
import online.syncio.view.user.Main;
import online.syncio.view.user.Profile;

public class MainController {

    private Main main;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();

    public MainController(Main main) {
        this.main = main;
    }

    public void recheckLoggedInUser() {
        if (OtherHelper.getSessionValue("LOGGED_IN_USER") != null) {
            // da login = remember me
            LoggedInUser.setCurrentUser(userDAO.getByID(OtherHelper.getSessionValue("LOGGED_IN_USER")));
            this.main.setProfile(new Profile(LoggedInUser.getCurrentUser()));
        } else if (LoggedInUser.getCurrentUser() != null) {
            //da login = username password
            this.main.setProfile(new Profile(LoggedInUser.getCurrentUser()));
        } else if (LoggedInUser.getCurrentUser() == null) {
            //chua login
            this.main.getBtnProfile().setText("Log in");
            this.main.setProfile(new Profile(null));
        }
    }

}
