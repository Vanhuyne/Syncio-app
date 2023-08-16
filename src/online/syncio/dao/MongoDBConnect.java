package online.syncio.dao;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.config.Account;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Singleton class for managing the connection to MongoDB and providing access
 * to DAO instances.
 */
public class MongoDBConnect {

    private static MongoDBConnect instance;
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static UserDAO userDAO;
    private static PostDAO postDAO;
    private static ConversationDAO conversationDAO;

    /**
     * Get the instance of MongoDBConnect using a singleton pattern.
     *
     * @return The MongoDBConnect instance.
     */
    public static synchronized MongoDBConnect getInstance() {
        if (instance == null) {
            instance = new MongoDBConnect();
        }
        return instance;
    }

    /**
     * Establishes a connection to MongoDB and initializes DAO instances.
     */
    public static void connect() {
        if (mongoClient == null) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            try {
                mongoClient = MongoClients.create(Account.CONNECTION_STRING);
                database = mongoClient.getDatabase(Account.DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
            } catch (MongoTimeoutException ex) {
                //System.err.println(ex);
                GlassPanePopup.showPopup(new MyDialog("Connection Timeout", "Connection to the database timed out. Please try again later.<br>(Database access is blocked on the school's Wi-Fi network at FPT Polytechnic.)"), "dialog");
                return;
            } catch (MongoException ex) {
                //System.err.println(ex);
                GlassPanePopup.showPopup(new MyDialog("Database Error", "An error occurred while communicating with the database.<br>Please try again later."), "dialog");
                return;
            } catch (Exception ex) {
                //System.err.println(ex);
                GlassPanePopup.showPopup(new MyDialog("Error", "An unexpected error occurred.<br>Please contact support for assistance."), "dialog");
                return;
            }

            // Create UserDAO and PostDAO instances
            userDAO = new UserDAOImpl(database);
            postDAO = new PostDAOImpl(database);
            conversationDAO = new ConversationDAOImpl(database);
        }
    }

    /**
     * Get the MongoDB database instance.
     *
     * @return The MongoDB database instance.
     */
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            connect();
        }
        return database;
    }

    /**
     * Get the UserDAO instance.
     *
     * @return The UserDAO instance.
     */
    public static UserDAO getUserDAO() {
        if (mongoClient == null) {
            connect();
        }
        return userDAO;
    }

    /**
     * Get the PostDAO instance.
     *
     * @return The PostDAO instance.
     */
    public static PostDAO getPostDAO() {
        if (mongoClient == null) {
            connect();
        }
        return postDAO;
    }

    /**
     * Get the ConversationDAO instance.
     *
     * @return The ConversationDAO instance.
     */
    public static ConversationDAO getConversationDAO() {
        if (mongoClient == null) {
            connect();
        }
        return conversationDAO;
    }

    /**
     * Closes the MongoDB client and releases resources.
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            database = null;
            userDAO = null;
            postDAO = null;
        }
    }
}
