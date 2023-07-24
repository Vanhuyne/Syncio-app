package online.syncio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

public class Post {
    private ObjectId id;
    private String userID;
    private String caption;
    private String datePosted = TimeHelper.getCurrentDateTime();
    public List<Binary> lPhoto;
    private List<UserIDAndDate> lLike = new ArrayList<>();
    private Map<String, UserIDAndDate> mComment = new HashMap<>();
    private Map<String, UserIDAndDate> mReport = new HashMap<>();

    public Post() {
    }

    public Post(String userID, List<UserIDAndDate> lLike) {
        this.userID = userID;
        this.lLike = lLike;
    }

    public Post(String userID, String caption, ArrayList<Binary> lPhoto) {
        this.userID = userID;
        this.caption = caption;
        this.lPhoto = lPhoto;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Post setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }
    
    public List<Binary> getLPhoto() {
        return lPhoto;
    }

    public void setLPhoto(ArrayList<Binary> lPhoto) {
        this.lPhoto = lPhoto;
    }

    public List<UserIDAndDate> getLLike() {
        return lLike;
    }

    public void setLLike(List<UserIDAndDate> lLike) {
        this.lLike = lLike;
    }

    public Map<String, UserIDAndDate> getMComment() {
        return mComment;
    }

    public void setMComment(Map<String, UserIDAndDate> mComment) {
        this.mComment = mComment;
    }

    public Map<String, UserIDAndDate> getMReport() {
        return mReport;
    }

    public void setMReport(Map<String, UserIDAndDate> mReport) {
        this.mReport = mReport;
    }
    
    
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Grade{");
        sb.append("id=").append(id);
        sb.append(", userID=").append(userID);
        sb.append(", caption=").append(caption);
        sb.append(", datePosted=").append(datePosted);
        sb.append(", lPhoto=").append(lPhoto);
        sb.append(", lLike=").append(lLike);
        sb.append(", mComment=").append(mComment);
        sb.append(", mReport=").append(mReport);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(userID, post.userID) && Objects.equals(caption, post.caption) && Objects.equals(datePosted, post.datePosted) && Objects.equals(lPhoto, post.lPhoto) && Objects.equals(lLike, post.lLike) && Objects.equals(mComment, post.mComment) && Objects.equals(mReport, post.mReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, caption, datePosted, lPhoto, lLike, mComment, mReport);
    }
}

