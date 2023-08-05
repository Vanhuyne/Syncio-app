package online.syncio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

public class Post {

    private ObjectId id;
    private String userID = "";
    private String caption = "";
    private String datePosted = TimeHelper.getCurrentDateTime();
    public List<Binary> photoList;
    private List<UserIDAndDate> likeList = new ArrayList<>();
    private List<UserIDAndDateAndText> commentList = new ArrayList<>();
    private List<UserIDAndDateAndText> reportList = new ArrayList<>();
    private int flag;

    public Post() {
    }

    public Post(String userID, List<UserIDAndDate> likeList) {
        this.userID = userID.trim();
        this.likeList = likeList;
    }

    public Post(String userID, String caption, ArrayList<Binary> lPhoto) {
        this.userID = userID.trim();
        this.caption = caption.trim();
        this.photoList = lPhoto;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserID() {
        return userID.trim();
    }

    public void setUserID(String userID) {
        this.userID = userID.trim();
    }

    public String getCaption() {
        return caption.trim();
    }

    public Post setCaption(String caption) {
        this.caption = caption.trim();
        return this;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public List<Binary> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Binary> photoList) {
        this.photoList = photoList;
    }

    public List<UserIDAndDate> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<UserIDAndDate> likeList) {
        this.likeList = likeList;
    }

    public List<UserIDAndDateAndText> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<UserIDAndDateAndText> commentList) {
        this.commentList = commentList;
    }

    public List<UserIDAndDateAndText> getReportList() {
        return reportList;
    }

    public void setReportList(List<UserIDAndDateAndText> reportList) {
        this.reportList = reportList;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Grade{");
        sb.append("id=").append(id);
        sb.append(", userID=").append(userID);
        sb.append(", caption=").append(caption);
        sb.append(", datePosted=").append(datePosted);
        sb.append(", photoList=").append(photoList);
        sb.append(", likeList=").append(likeList);
        sb.append(", commentList=").append(commentList);
        sb.append(", reportList=").append(reportList);
        sb.append(", flag=").append(flag);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(userID, post.userID) && Objects.equals(caption, post.caption) && Objects.equals(datePosted, post.datePosted) && Objects.equals(photoList, post.photoList) && Objects.equals(likeList, post.likeList) && Objects.equals(commentList, post.commentList) && Objects.equals(reportList, post.reportList) && Objects.equals(flag, post.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, caption, datePosted, photoList, likeList, commentList, reportList, flag);
    }
}
