package online.syncio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;
import org.bson.types.ObjectId;


/**
 * Represents a post with various attributes including user ID, caption, date posted, photos, likes, comments, and reports.
 */
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

    /**
     * Default constructor for the Post class.
     */
    public Post() {
    }

    /**
     * Constructs a Post object with the provided user ID and like list.
     *
     * @param userID   the user ID of the post creator
     * @param likeList the list of users who liked the post and the date they liked it
     */
    public Post(String userID, List<UserIDAndDate> likeList) {
        this.userID = userID.trim();
        this.likeList = likeList;
    }

     /**
     * Constructs a Post object with the provided user ID, caption, and photo list.
     *
     * @param userID    the user ID of the post creator
     * @param caption   the caption or description of the post
     * @param lPhoto    the list of photos associated with the post
     */
    public Post(String userID, String caption, ArrayList<Binary> lPhoto) {
        this.userID = userID.trim();
        this.caption = caption.trim();
        this.photoList = lPhoto;
    }

    /**
     * Gets the ID of the post.
     *
     * @return the ID of the post
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets the ID of the post.
     *
     * @param id the ID to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

     /**
     * Gets the user ID of the post creator.
     *
     * @return the user ID of the post creator
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the user ID of the post creator.
     *
     * @param userID the user ID to set
     */
    public void setUserID(String userID) {
        this.userID = userID.trim();
    }
    
    /**
     * Returns the caption of the post.
     *
     * @return The caption of the post.
     */  
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the caption of the post.
     *
     * @param caption The new caption for the post.
     * @return The modified Post object.
     */
    public Post setCaption(String caption) {
        this.caption = caption.trim();
        return this;
    }

    /**
     * Returns the date when the post was posted.
     *
     * @return The date when the post was posted.
     */
    public String getDatePosted() {
        return datePosted;
    }

    /**
     * Sets the date when the post was posted.
     *
     * @param datePosted The date when the post was posted.
     */
    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    /**
     * Returns the list of photos associated with the post.
     *
     * @return The list of photos associated with the post.
     */
    public List<Binary> getPhotoList() {
        return photoList;
    }

    /**
     * Sets the list of photos associated with the post.
     *
     * @param photoList The list of photos associated with the post.
     */
    public void setPhotoList(List<Binary> photoList) {
        this.photoList = photoList;
    }
    
      /**
     * Returns the list of users who liked the post.
     *
     * @return The list of users who liked the post.
     */
    public List<UserIDAndDate> getLikeList() {
        return likeList;
    }

     /**
     * Sets the list of users who liked the post.
     *
     * @param likeList The list of users who liked the post.
     */
    public void setLikeList(List<UserIDAndDate> likeList) {
        this.likeList = likeList;
    }

    /**
     * Returns the list of comments on the post.
     *
     * @return The list of comments on the post.
     */
    public List<UserIDAndDateAndText> getCommentList() {
        return commentList;
    }

    /**
     * Sets the list of comments on the post.
     *
     * @param commentList The list of comments on the post.
     */
    public void setCommentList(List<UserIDAndDateAndText> commentList) {
        this.commentList = commentList;
    }

    /**
     * Returns the list of user reports on the post.
     *
     * @return The list of user reports on the post.
     */
    public List<UserIDAndDateAndText> getReportList() {
        return reportList;
    }

    /**
     * Sets the list of user reports on the post.
     *
     * @param reportList The list of user reports on the post.
     */
    public void setReportList(List<UserIDAndDateAndText> reportList) {
        this.reportList = reportList;
    }

    /**
     * Returns the flag associated with the post.
     *
     * @return The flag associated with the post.
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Sets the flag associated with the post.
     *
     * @param flag The flag associated with the post.
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Returns a string representation of the Post object.
     *
     * @return A string representation of the Post object.
     */
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
    
    
    /**
     * Checks if the given object is equal to this Post object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
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

    
     /**
     * Returns the hash code value for the Post object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, userID, caption, datePosted, photoList, likeList, commentList, reportList, flag);
    }
}
