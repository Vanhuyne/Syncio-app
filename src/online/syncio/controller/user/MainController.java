package online.syncio.controller.user;

import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.config.Account;
import online.syncio.config.Version;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.FileHelper;
import online.syncio.utils.OtherHelper;
import online.syncio.utils.WebsiteHelper;
import online.syncio.view.user.Main;
import online.syncio.view.user.Profile;

public class MainController {

    private Main main;
    public static String CURRENT_VERSION = Version.CURRENT_VERSION;
    public static String LATEST_VERSION = null;
    public static String LINK_TO_UPDATE = null;
    public static String CURRENT_DIRECTORY = null;
    private boolean isUpdating = false; 

    private UserDAO userDAO = MongoDBConnect.getUserDAO();

    public MainController(Main main) {
        this.main = main;
        LATEST_VERSION = getLatestVersion();
        //prevent beta version
        this.isUpdating = !CURRENT_VERSION.equals(LATEST_VERSION) && !LATEST_VERSION.contains("beta");
    }

    public boolean getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(boolean isUpdating) {
        this.isUpdating = isUpdating;
    }

    
    
    public void checkForUpdates() {
        if (isUpdating) {
            updateApplication();
//            JOptionPane.showMessageDialog(this, "Application is updating.\nIt will automatically restart once the update is complete.\nPlease do not close the app while the update is in progress.");
            GlassPanePopup.showPopup(new MyDialog("Updating", "Application is updating.<br>It will automatically restart once the update is complete.<br>Please do not close the app while the update is in progress."), "dialog");
        }
        else {
            // no
            System.out.println("khong update");
        }
    }
    
    
    
    public void updateApplication() {
        //download
        String fileName = LATEST_VERSION + ".zip";
        LINK_TO_UPDATE = Account.GITHUB_REPOSITORIE + "releases/download/" + LATEST_VERSION + "/" + fileName;
        CURRENT_DIRECTORY = System.getProperty("user.dir");
        System.out.println(LINK_TO_UPDATE);
        FileHelper.downloadFileFromWebsite(LINK_TO_UPDATE, CURRENT_DIRECTORY, fileName);
        
        //unzip
        FileHelper.unzip(CURRENT_DIRECTORY, fileName);
        
        //delete after unzip
        FileHelper.deleteFile(CURRENT_DIRECTORY, fileName);
        
        //restart application
        ActionHelper.restartApplication();
    }
    
    
    
    public String getLatestVersion() {
        String url = "https://github.com/56duong/Syncio/releases";
        String latestVersion = null;
        if(WebsiteHelper.isUrlExists(url)) {
            latestVersion = WebsiteHelper.getTextFromWeb(Account.GITHUB_REPOSITORIE + "releases", "h2.sr-only");
        }
        return latestVersion;
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
