package online.syncio.model;

import online.syncio.utils.TimeHelper;

public class UserIDAndDate {
    public String followerID;
    public String dateFollowed = TimeHelper.getCurrentDateTime();

    public UserIDAndDate() {
    }

    public UserIDAndDate(String followerID, String dateFollowed) {
        this.followerID = followerID;
        this.dateFollowed = dateFollowed;
    }

    public String getFollowerID() {
        return followerID;
    }

    public void setFollowerID(String followerID) {
        this.followerID = followerID;
    }

    public String getDateFollowed() {
        return dateFollowed;
    }

    public void setDateFollowed(String dateFollowed) {
        this.dateFollowed = dateFollowed;
    }
    
}
