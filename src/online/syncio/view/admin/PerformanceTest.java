package online.syncio.view.admin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.MongoDBConnectOld;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import org.bson.types.ObjectId;


/**
 * PerformanceTest class for comparing the execution time of accessing and loading posts using different MongoDB connection approaches.
 */
public class PerformanceTest {

    public static void main(String[] args) {
        // Number of test iterations
        int iterations = 50;

        // Connect using MongoDBConnectOld (original implementation)
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            System.out.println("MongoDBConnect - " + i);
            try {
                MongoDBConnect.connect();
                PostDAO postDAO = MongoDBConnect.getPostDAO();
                // Replace the User object with a valid User object representing the logged-in user
                User user = new User();
                user.setId(new ObjectId("64ba297c10b6253598f2385b"));
                List<UserIDAndDate> followers = user.getFollowing();
                followers.add(new UserIDAndDate("64c1eb8c2c369f48226c9b8b"));
                followers.add(new UserIDAndDate("64acdbc31d8be01ea40a81ec"));
                FindIterable<Post> posts = postDAO.getAllPostOfFollowers(user); // Load all posts of the followers
                
                // Loop through the posts and display their details
                for (Post post : posts) {
                    System.out.println("Post ID: " + post.getId());
                    System.out.println("---------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTimeUsingMongoDBConnect = endTime - startTime;

        
        
        // Connect using MongoDBManager (updated implementation)
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            System.out.println("MongoDBManager - " + i);
            try {
                MongoDatabase database = MongoDBConnectOld.getDatabase();
                PostDAO postDAO = new PostDAOImpl(database);
                // Replace the User object with a valid User object representing the logged-in user
                User user = new User();
                user.setId(new ObjectId("64ba297c10b6253598f2385b"));
                List<UserIDAndDate> followers = user.getFollowing();
                followers.add(new UserIDAndDate("64c1eb8c2c369f48226c9b8b"));
                followers.add(new UserIDAndDate("64acdbc31d8be01ea40a81ec"));
                FindIterable<Post> posts = postDAO.getAllPostOfFollowers(user); // Load all posts of the followers
                
                // Loop through the posts and display their details
                for (Post post : posts) {
                    System.out.println("Post ID: " + post.getId());
                    System.out.println("---------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        endTime = System.currentTimeMillis();
        long totalTimeUsingMongoDBManager = endTime - startTime;

        System.out.println("Time taken using MongoDBConnect: " + totalTimeUsingMongoDBConnect + " ms");
        System.out.println("Time taken using MongoDBManager: " + totalTimeUsingMongoDBManager + " ms");
    }
}

