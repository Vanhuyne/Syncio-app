package online.syncio.dao;

import com.mongodb.client.MongoCollection;
import java.util.List;
import online.syncio.model.Post;

public interface PostDAO extends DAO<Post> {

    public MongoCollection<Post> getAllByCollection();

    public List<Post> getAllByUserID(String entityID);
}
