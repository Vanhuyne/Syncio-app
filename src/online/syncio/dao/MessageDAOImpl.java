package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import java.util.List;
import online.syncio.model.Message;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MessageDAOImpl implements MessageDAO {

    private MongoDatabase database;
    private MongoCollection<Message> messageCollection;

    public MessageDAOImpl(MongoDatabase database) {
        this.database = database;
        messageCollection = database.getCollection("messages", Message.class);
    }

    @Override
    public boolean add(Message m) {
        try {
            InsertOneResult result = messageCollection.insertOne(m);
            System.out.println("Inserted a Message with the following id: " + result.getInsertedId().asObjectId().getValue());

            return true;
        } catch (Exception ex) {
            System.out.println("Failed to insert into MongoDB: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateByID(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Message getByID(String messageID) {
        return messageCollection.find(eq("_id", new ObjectId(messageID))).first();
    }

    @Override
    public List<Message> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FindIterable<Message> findAllByTwoUsernames(String user1, String user2) {
        String sender1 = user1;
        String recipient1 = user2;

        String sender2 = user2;
        String recipient2 = user1;

        Bson filter = Filters.or(
                Filters.and(Filters.eq("sender", sender1), Filters.eq("recipient", recipient1)),
                Filters.and(Filters.eq("sender", sender2), Filters.eq("recipient", recipient2))
        );

        FindIterable<Message> messageList = messageCollection.find(filter).sort(Sorts.ascending("dateSent"));
        return messageList;
    }

    @Override
    public Message findNewMessageWithCurrentUser(String currentUser, String messagedUsername) {
        String sender = messagedUsername;
        String recipient = currentUser;

        Bson filter = Filters.and(Filters.eq("sender", sender), Filters.eq("recipient", recipient));

        Message message = messageCollection.find(filter).sort(Sorts.descending("dateSent")).first();
        return message;
    }
}
