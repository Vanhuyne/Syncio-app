package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import online.syncio.component.GlassPanePopup;
import online.syncio.model.Message;
import online.syncio.view.user.ErrorDetail;
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
            String errorInfo = ex.getMessage();
            GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
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
    public FindIterable<Message> findAllByTwoUsers(String user1, String user2) {
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
    public Set<String> getMessagingUsers(String currentUser) {
        Bson filter = Filters.or(
                Filters.eq("sender", currentUser),
                Filters.eq("recipient", currentUser)
        );

        Set<String> usernames = new HashSet<>();

        try (MongoCursor<Message> cursor = messageCollection.find(filter).sort(Sorts.descending("dateSent")).iterator()) {
            while (cursor.hasNext()) {
                Message message = cursor.next();
                String sender = message.getSender();
                String recipient = message.getRecipient();

                // Add sender to usernames if it is not the currentUser
                if (!sender.equalsIgnoreCase(currentUser)) {
                    usernames.add(sender);
                }

                // Add recipient to usernames if it is not the currentUser
                if (!recipient.equalsIgnoreCase(currentUser)) {
                    usernames.add(recipient);
                }
            }
        }

        usernames.remove(currentUser);

        return usernames;
    }

    @Override
    public ChangeStreamIterable<Message> getChangeStream() {
        ChangeStreamIterable<Message> changeStreamMessage = messageCollection.watch();
        changeStreamMessage.fullDocument(FullDocument.UPDATE_LOOKUP);
        return changeStreamMessage;
    }
}
