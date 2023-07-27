package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import online.syncio.model.User;
import online.syncio.utils.TextHelper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class UserDAOImpl implements UserDAO {

    private MongoDatabase database;
    private MongoCollection<User> userCollection;

    public UserDAOImpl(MongoDatabase database) {
        this.database = database;
        userCollection = database.getCollection("users", User.class);
    }

    @Override
    public boolean add(User user) {
        try {
            InsertOneResult result = userCollection.insertOne(user);
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
        return userCollection.find(eq("_id", new ObjectId(userID))).first();
    }

    @Override
    public List<User> getAll() {
        List<User> lUser = new ArrayList<>();
        try {
            FindIterable<User> users = userCollection.find();

            // listUser
            for (User user : users) {
                lUser.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lUser;
    }

    @Override
    public User authentication(String username, String password) {
        User user = userCollection.find(eq("username", username)).first();

        // check password
        if (user != null) {
            if (TextHelper.authenticationPasswordHash(password, user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean checkEmail(String email) {
        Bson filter = Filters.eq("email", email);
        User user = userCollection.find(filter).first();

        return user != null; //  user ton tai => true
    }
    
    @Override
    public boolean checkUsername(String username) {
        Bson filter = Filters.eq("username", username);
        User user = userCollection.find(filter).first();

        return user != null; //  username ton tai => true
    }

    @Override
    public int updateByEmail(String password, String email) {
        MongoCollection<User> users = database.getCollection("users", User.class);

        Bson filter = Filters.eq("email", email);
        Bson update = Updates.set("password", password);

        return (int) users.updateOne(filter, update).getModifiedCount();  // thanh cong -> (lon hon 0)    }
    }
}
