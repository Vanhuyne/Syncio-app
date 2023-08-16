package online.syncio.model;

import java.util.List;
import org.bson.types.ObjectId;

/**
 * Represents a conversation with participants and the send messages between
 * them
 */
public class Conversation {

    private ObjectId id;
    private List<String> participants;
    private List<Message> messagesHistory;

    /**
     * Default constructor for the Conversation class.
     */
    public Conversation() {
    }

    /**
     * Constructs a Message object with the provided sender, recipient, date
     * sent, and message content.
     *
     * @param participants the participants of this conversation
     * @param messagesHistory the history of the sent messages from these
     * participants
     */
    public Conversation(List<String> participants, List<Message> messagesHistory) {
        this.participants = participants;
        this.messagesHistory = messagesHistory;
    }

    public Conversation(ObjectId id, List<String> participants, List<Message> messagesHistory) {
        this.id = id;
        this.participants = participants;
        this.messagesHistory = messagesHistory;
    }

    public Conversation(String id, List<String> participants, List<Message> messagesHistory) {
        this.id = new ObjectId(id);
        this.participants = participants;
        this.messagesHistory = messagesHistory;
    }

    /**
     * Gets the ID of the conversation.
     *
     * @return the ID of the conversation
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Gets the ID of the conversation as string.
     *
     * @return the ID of the conversation as string
     */
    public String getIdAsString() {
        return id.toString();
    }

    /**
     * Sets the ID of the conversation.
     *
     * @param id the ID to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Gets the list of participants in this conversation.
     *
     * @return the list of participants in this conversation
     */
    public List<String> getParticipants() {
        return participants;
    }

    /**
     * Sets the list of participants in this conversation.
     *
     * @param participants the list of participants to set
     */
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    /**
     * Gets the list of sent messages in this conversation.
     *
     * @return the list of sent messages in this conversation
     */
    public List<Message> getMessagesHistory() {
        return messagesHistory;
    }

    /**
     * Sets the list of sent messages in this conversation.
     *
     * @param messagesHistory the list of sent messages to set
     */
    public void setMessagesHistory(List<Message> messagesHistory) {
        this.messagesHistory = messagesHistory;
    }

    /**
     * Add new message to this conversation.
     *
     * @param msg the new message to add
     */
    public void addNewMessage(Message msg) {
        this.messagesHistory.add(msg);
    }

    /**
     * Gets the newest messages in this conversation.
     *
     * @return the newest messages in this conversation
     */
    public Message getNewestMessage() {
        if (messagesHistory.isEmpty()) {
            return null;
        }
        return messagesHistory.get(messagesHistory.size() - 1);
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + id + "\n\nparticipants=" + participants + "\n\nmessagesHistory=" + messagesHistory + '}';
    }
}
