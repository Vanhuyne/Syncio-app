package online.syncio.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import online.syncio.model.Post;
import org.bson.types.ObjectId;

public class PostDAOImpl implements PostDAO {
    private MongoDatabase database;

    public PostDAOImpl(MongoDatabase database) {
        this.database = database;
    }
    
    @Override
    public boolean add(Post post) {
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);

        try {
            InsertOneResult result = posts.insertOne(post);
            System.out.println("Inserted a Post with the following id: " + result.getInsertedId().asObjectId().getValue());

            return true;
        }
        catch(Exception ex) {
            System.out.println("Failed to insert into MongoDB: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    
    
    @Override
    public boolean updateByID(Post t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    @Override
    public Post getByID(String postID) {
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);
        return posts.find(eq("_id", new ObjectId(postID))).first();
    }

    
    
    @Override
    public List<Post> getAll() {
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);
        List<Post> lPost = new ArrayList<>();
        
        try {    
            posts.find().sort(Sorts.descending("datePosted")).into(lPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lPost;
    }

    
    
    @Override
    public MongoCollection<Post> getAllByCollection() {
        return database.getCollection("posts", Post.class);
    }

}
