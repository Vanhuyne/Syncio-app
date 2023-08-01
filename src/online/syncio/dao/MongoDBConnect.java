package online.syncio.dao;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import online.syncio.config.Account;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBConnect {
    private static MongoDBConnect instance;
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static UserDAO userDAO;
    private static PostDAO postDAO;
    
    public static synchronized MongoDBConnect getInstance() {
        if (instance == null) {
            instance = new MongoDBConnect();
        }
        return instance;
    }

    public static void connect() {
        if (mongoClient == null) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            try {
                mongoClient = MongoClients.create(Account.CONNECTION_STRING);
                database = mongoClient.getDatabase(Account.DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
            } catch (MongoException me) {
                System.err.println(me);
                // Handle the exception here if needed
                return; // Return if the connection failed
            }

            // Create UserDAO and PostDAO instances
            userDAO = new UserDAOImpl(database);
            postDAO = new PostDAOImpl(database);
        }
    }

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            connect();
        }
        return database;
    }

    public static UserDAO getUserDAO() {
        if (mongoClient == null) {
            connect();
        }
        return userDAO;
    }

    public static PostDAO getPostDAO() {
        if (mongoClient == null) {
            connect();
        }
        return postDAO;
    }

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
