package online.syncio.dao;
    
import com.mongodb.client.MongoCollection;
import online.syncio.model.Post;

public interface PostDAO extends DAO<Post> {
    public MongoCollection<Post> getAllByCollection();
}

