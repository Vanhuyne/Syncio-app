package online.syncio.model;

import online.syncio.utils.TimeHelper;

public class UserIDAndDate {
    public String userID;
    public String date = TimeHelper.getCurrentDateTime();

    public UserIDAndDate() {
    }

    public UserIDAndDate(String userID, String date) {
        this.userID = userID;
        this.date = date;
    }
    
    public UserIDAndDate(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
