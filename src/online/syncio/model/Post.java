package online.syncio.model;

import java.util.ArrayList;
import java.util.HashMap;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;

public class Post {
    public String userID;
    private String caption;
    private String datePosted = TimeHelper.getCurrentDateTime();
    public ArrayList<Binary> lPhoto;
    private ArrayList<UserIDAndDate> lLike = new ArrayList<>();
    private HashMap<UserIDAndDate, String> mComment = new HashMap<>();
    private HashMap<UserIDAndDate, Integer> mReport = new HashMap<>();

    public Post() {
    }

    public Post(String userID, String caption, ArrayList<Binary> lPhoto) {
        this.userID = userID;
        this.caption = caption;
        this.lPhoto = lPhoto;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public ArrayList<Binary> getlPhoto() {
        return lPhoto;
    }

    public void setlPhoto(ArrayList<Binary> lPhoto) {
        this.lPhoto = lPhoto;
    }

    public ArrayList<UserIDAndDate> getlLike() {
        return lLike;
    }

    public void setlLike(ArrayList<UserIDAndDate> lLike) {
        this.lLike = lLike;
    }

    public HashMap<UserIDAndDate, String> getmComment() {
        return mComment;
    }

    public void setmComment(HashMap<UserIDAndDate, String> mComment) {
        this.mComment = mComment;
    }

    public HashMap<UserIDAndDate, Integer> getmReport() {
        return mReport;
    }

    public void setmReport(HashMap<UserIDAndDate, Integer> mReport) {
        this.mReport = mReport;
    }
    
}
