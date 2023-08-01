package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.List;
import online.syncio.model.Post;
import online.syncio.model.User;

public interface PostDAO extends DAO<Post> {
    public MongoCollection<Post> getAllByCollection();
    public FindIterable<Post> getAllPostOfFollowers(User user);
    public List<Post> getAllByUserID(String userID);
    public boolean addLike(String postID, String userID);
    public boolean removeLike(String postID, String userID);
    public boolean addComment(String text, String userID, String postID);
}
