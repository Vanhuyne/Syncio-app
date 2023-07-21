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
import online.syncio.utils.TextHelper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class UserDAOImpl implements UserDAO {

    private MongoDatabase database;

    public UserDAOImpl(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public boolean add(User user) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        try {
            InsertOneResult result = collection.insertOne(user);
            System.out.println("Inserted a User with the following id: " + result.getInsertedId().asObjectId().getValue());

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
        MongoCollection<User> collection = database.getCollection("users", User.class);

        List<User> lUser = new ArrayList<>();
        try {
            FindIterable<User> users = collection.find();

            // listUser
            for (User user : users) {
                lUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lUser;
    }

    public User authentication(String username, String password) {
        MongoCollection<User> collection = database.getCollection("users", User.class);

        User user = collection.find(eq("username", username)).first();

        // check password
        if (user != null) {
            if (TextHelper.authenticationPasswordHash(password, user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    public boolean checkEmail(String email) {
        MongoCollection<User> collection = database.getCollection("users", User.class);

        Bson filter = Filters.eq("email", email);
        User user = collection.find(filter).first();

        return user != null; //  user ton tai => true
    }

    @Override
    public boolean checkUsername(String username) {
        MongoCollection<User> users = database.getCollection("users", User.class);

        Bson filter = Filters.eq("username", username);
        User user = users.find(filter).first();

        return user != null; //  username ton tai => true
    }

}
