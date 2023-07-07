package online.syncio.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.List;
import online.syncio.model.Post;

public class PostDAOImpl implements PostDAO {
    
    @Override
    public boolean add(Post post) {
        MongoDatabase database = MongoDBConnect.getDatabase();
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);

        try {
            InsertOneResult result = posts.insertOne(post);
            System.out.println("Inserted a Post with the following id: " + result.getInsertedId().asObjectId().getValue());
            MongoDBConnect.closeConnection();
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
    public Post getByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Post> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
