package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import java.util.Set;
import online.syncio.model.Message;

/**
 * The `MessageDAO` interface defines methods for interacting with the messaging data in the MongoDB database.
 */
public interface MessageDAO extends DAO<Message> {

    /**
     * Retrieves all messages between two specified users.
     *
     * @param user1 The ID of the first user.
     * @param user2 The ID of the second user.
     * @return A FindIterable containing messages between the two users.
     */
    public FindIterable<Message> findAllByTwoUsers(String user1, String user2);

    /**
     * Retrieves a set of users with whom the specified user has had messaging conversations.
     *
     * @param currentUser The ID of the user for whom to retrieve messaging partners.
     * @return A Set containing the IDs of messaging partners.
     */
    public Set<String> getMessagingUsers(String currentUser);

    /**
     * Returns a change stream for monitoring changes in the messaging data.
     *
     * @return A ChangeStreamIterable providing the ability to listen to changes in messages.
     */
    public ChangeStreamIterable<Message> getChangeStream();
}
