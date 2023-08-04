package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import java.util.Set;
import online.syncio.model.Message;

public interface MessageDAO extends DAO<Message> {

    public FindIterable<Message> findAllByTwoUsernames(String user1, String user2);

    public Message findNewMessageWithCurrentUser(String currentUser, String messagedUsername);

    public Set<String> findMessagedUsers(String currentUser);

    public ChangeStreamIterable<Message> getChangeStream();
}
