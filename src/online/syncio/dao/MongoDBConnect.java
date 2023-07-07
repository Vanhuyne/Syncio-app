package online.syncio.dao;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import online.syncio.config.Account;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

public class MongoDBConnect {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase(String connectionString, String databaseName) {
        // The MongoDB Java Driver provides a way to automatically map objects to Document using a Codec and CodecRegistry.
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        
        // Create a new client and connect to the server
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
        } catch (MongoException me) {
            System.err.println(me);
        }
        
        return database;
    }
    
    
    
    public static MongoDatabase getDatabase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Create a new client and connect to the server
        try {
            mongoClient = MongoClients.create(Account.CONNECTION_STRING);
            database = mongoClient.getDatabase(Account.DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
//                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                return database;
            } catch (MongoException me) {
                System.err.println(me);
            }
        } catch (MongoException me) {
            System.err.println(me);
        }
        
        return null;
    }

    
    
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
    
}
