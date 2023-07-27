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
import online.syncio.view.ManagementUser;

public class ManagementUserController {

    private ManagementUser managementUser;
    private MongoDatabase database;
    private UserDAO userDAO;

    public ManagementUserController(ManagementUser managementUser) {
        this.managementUser = managementUser;
        this.database = MongoDBConnect.getDatabase();
        userDAO = new UserDAOImpl(database);
    }
}
