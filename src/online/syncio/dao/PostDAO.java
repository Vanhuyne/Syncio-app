package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.List;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDateAndText;

/**
 * The `PostDAO` interface defines methods for managing interactions with post-related data in the MongoDB database.
 */
public interface PostDAO extends DAO<Post> {

    /**
     * Retrieves all posts from the collection.
     *
     * @return the MongoCollection containing all posts
     */
    public MongoCollection<Post> getAllByCollection();

    /**
     * Retrieves all posts from the followers of a given user.
     *
     * @param user the user for whom to retrieve posts from followers
     * @return a FindIterable containing the posts from followers
     */
    public FindIterable<Post> getAllPostOfFollowers(User user);

    /**
     * Retrieves all posts created by users other than the given user.
     *
     * @param user the user for whom to retrieve posts from others
     * @return a FindIterable containing the posts from other users
     */
    public FindIterable<Post> getAllPostOther(User user);

     /**
     * Retrieves all posts created by a specific user.
     *
     * @param userID the ID of the user
     * @return a list of posts created by the user
     */
    public List<Post> getAllByUserID(String userID);

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userID the ID of the user
     * @return a FindIterable containing the posts created by the user
     */
    public FindIterable<Post> getAllByUserIDFindIterable(String userID);

    /**
     * Adds a like to a post.
     *
     * @param postID the ID of the post
     * @param userID the ID of the user liking the post
     * @return true if the like is added successfully, false otherwise
     */
    public boolean addLike(String postID, String userID);

     /**
     * Removes a like from a post.
     *
     * @param postID the ID of the post
     * @param userID the ID of the user unliking the post
     * @return true if the like is removed successfully, false otherwise
     */
    public boolean removeLike(String postID, String userID);

    /**
     * Adds a comment to a post.
     *
     * @param text   the text of the comment
     * @param userID the ID of the user adding the comment
     * @param postID the ID of the post
     * @return true if the comment is added successfully, false otherwise
     */
    public boolean addComment(String text, String userID, String postID);

    /**
     * Retrieves a change stream for monitoring post changes.
     *
     * @return a ChangeStreamIterable for posts
     */
    public ChangeStreamIterable<Post> getChangeStream();

    /**
     * Retrieves the report list for a specific post.
     *
     * @param postID the ID of the post
     * @return a list of reported comments along with their users and dates
     */
    public List<UserIDAndDateAndText> getReportList(String postID);

    /**
     * Retrieves all posts that have been reported.
     *
     * @return a FindIterable containing the reported posts
     */
    public FindIterable<Post> getAllReportedPost();

    /**
     * Adds a report to a post.
     *
     * @param text   the text of the report
     * @param userID the ID of the user reporting the post
     * @param postID the ID of the post
     * @return true if the report is added successfully, false otherwise
     */
    public boolean addReport(int text, String userID, String postID);

    /**
     * Checks if a specific user's ID is in the list of reports for a post.
     *
     * @param userID the ID of the user
     * @param postID the ID of the post
     * @return true if the user's ID is in the list of reports, false otherwise
     */
    public boolean isUserIDInListReport(String userID, String postID);

    /**
     * Updates the flag of a post to 1.
     *
     * @param postID the ID of the post
     * @return true if the flag is updated successfully, false otherwise
     */
    public boolean updateFlagTo1(String postID);

    /**
     * Updates the flag of a post to 0.
     *
     * @param postID the ID of the post
     * @return true if the flag is updated successfully, false otherwise
     */
    public boolean updateFlagTo0(String postID);
}
