package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import java.util.List;
import online.syncio.model.Conversation;
import online.syncio.model.Message;
import org.bson.types.ObjectId;

public interface ConversationDAO extends DAO<Conversation> {

    public Conversation getByParticipants(List<String> participants);

    public List<String> getAllMessageHistory(String currentUser);

    public List<String> getMessagedUser(String currentUser);

    public ChangeStreamIterable<Conversation> getChangeStream();

    public boolean addMessage(ObjectId id, Message message);
}
