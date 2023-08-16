package online.syncio.dao;

import com.mongodb.MongoException;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import online.syncio.model.Conversation;
import online.syncio.model.Message;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class ConversationDAOImpl implements ConversationDAO {

    private MongoCollection<Conversation> conversationCollection;

    public ConversationDAOImpl(MongoDatabase database) {
        conversationCollection = database.getCollection("conversations", Conversation.class);
    }

    @Override
    public Conversation getByParticipants(List<String> participants) {
        Bson filter1 = Filters.eq("participants", participants);
        Collections.reverse(participants);
        Bson filter2 = Filters.eq("participants", participants);

        Bson filter = Filters.or(filter1, filter2);
        return conversationCollection.find(filter).first();
    }

    @Override
    public List<String> getAllMessageHistory(String currentUser) {
        Bson filter = Filters.in("participants", currentUser);

        List<String> history = new ArrayList<>();

        try (MongoCursor<Conversation> cursor = conversationCollection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                Conversation con = cursor.next();
                history.add(con.getIdAsString());
            }
        }

        return history;
    }

    @Override
    public List<String> getMessagedUser(String currentUserID) {
        Bson filter = Filters.in("participants", currentUserID);

        List<String> history = new ArrayList<>();

        try (MongoCursor<Conversation> cursor = conversationCollection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                Conversation con = cursor.next();
                List<String> username = con.getParticipants();
                username.remove(currentUserID);

                if (username.size() == 1) {
                    history.add(username.get(0));
                }
            }
        }

        return history;
    }

    @Override
    public ChangeStreamIterable<Conversation> getChangeStream() {
        ChangeStreamIterable<Conversation> changeStreamConversations = conversationCollection.watch();
        changeStreamConversations.fullDocument(FullDocument.UPDATE_LOOKUP);
        return changeStreamConversations;
    }

    @Override
    public boolean add(Conversation con) {
        try {
            InsertOneResult result = conversationCollection.insertOne(con);
            System.out.println("Inserted a Conversation with the following id: " + result.getInsertedId().asObjectId().getValue());

            return true;
        } catch (Exception ex) {
            System.out.println("Failed to insert into MongoDB: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateByID(Conversation conversation) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Conversation getByID(String conversationID) {
        return conversationCollection.find(eq("_id", new ObjectId(conversationID))).first();
    }

    @Override
    public boolean addMessage(ObjectId id, Message message) {
        Bson filterID = Filters.eq("_id", id);
        Bson update = push("messagesHistory", message);

        try {
            UpdateResult result = conversationCollection.updateOne(filterID, update);
            System.out.println("Modified document count: " + result.getModifiedCount());
            System.out.println("Upserted id: " + result.getUpsertedId());
            return result.getModifiedCount() > 0;
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

        return false;
    }

    public List<Conversation> getAll() {
        FindIterable<Conversation> conversationList = conversationCollection.find();
        return conversationList.into(new ArrayList<>());
    }

}
