package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import online.syncio.model.User;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class UserDAOImpl implements UserDAO {

    private MongoDatabase database;
    List<User> listUser = new ArrayList<>();

    public UserDAOImpl(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public boolean add(User user) {
        MongoDatabase database = MongoDBConnect.getDatabase();
        MongoCollection<User> collection = database.getCollection("users", User.class);
        try {

            InsertOneResult result = collection.insertOne(user);

            MongoDBConnect.closeConnection();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateByID(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getByID(String userID) {
        MongoCollection<User> users = database.getCollection("users", User.class);
        return users.find(eq("_id", new ObjectId(userID))).first();
    }

    @Override
    public List<User> getAll() {
        // Lấy kết nối và collection từ MongoDBConnect
        MongoDatabase database = MongoDBConnect.getDatabase();
        MongoCollection<User> collection = database.getCollection("users", User.class);

        try {
            FindIterable<User> users = collection.find();

            // listUser
            for (User user : users) {
                listUser.add(user);
            }
            MongoDBConnect.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUser;
    }

    public User authentication(String username, String password) {
        MongoDatabase database = MongoDBConnect.getDatabase();
        MongoCollection<User> collection = database.getCollection("users", User.class);

        Bson filter = Filters.eq("username", username);
        User user = collection.find(filter).first();

        if (user == null) {
            MongoDBConnect.closeConnection();
            return null;
        }
        
        // check password
        if (user.getPassword().equals(password)) {
            MongoDBConnect.closeConnection();
            return user;
        } else {
            MongoDBConnect.closeConnection();
            return null;
        }
    }

}
