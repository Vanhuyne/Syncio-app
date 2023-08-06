package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.List;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDateAndText;

public interface PostDAO extends DAO<Post> {
    public MongoCollection<Post> getAllByCollection();
    public FindIterable<Post> getAllPostOfFollowers(User user);
    public FindIterable<Post> getAllPostOther(User user);
    public List<Post> getAllByUserID(String userID);
    public FindIterable<Post> getAllByUserIDFindIterable(String userID);
    public boolean addLike(String postID, String userID);
    public boolean removeLike(String postID, String userID);
    public boolean addComment(String text, String userID, String postID);
    public ChangeStreamIterable<Post> getChangeStream();
    public List<UserIDAndDateAndText> getReportList(String postID);
    public boolean addReport(String text, String userID, String postID);
    public boolean isUserIDInListReport(String userID, String postID);
}
