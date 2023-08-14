package online.syncio.model;

import online.syncio.utils.TimeHelper;

/**
 * Represents a message with sender, recipient, date sent, and message content.
 */
public class Message {

    private String sender;
    private String recipient;
    private String dateSent = TimeHelper.getCurrentDateTime();
    private String messageContent;

    /**
     * Default constructor for the Message class.
     */
    public Message() {
    }

    /**
     * Constructs a Message object with the provided sender, recipient, and message content.
     *
     * @param sender      the sender of the message
     * @param recipient   the recipient of the message
     * @param message     the content of the message
     */
    public Message(String sender, String recipient, String message) {
        this.sender = sender.trim();
        this.recipient = recipient.trim();
        this.messageContent = message.trim();
    }

    /**
     * Constructs a Message object with the provided sender, recipient, date sent, and message content.
     *
     * @param sender      the sender of the message
     * @param recipient   the recipient of the message
     * @param dateSent    the date and time when the message was sent
     * @param message     the content of the message
     */
    public Message(String sender, String recipient, String dateSent, String message) {
        this.sender = sender.trim();
        this.recipient = recipient.trim();
        this.dateSent = dateSent;
        this.messageContent = message.trim();
    }

    /**
     * Gets the sender of the message.
     *
     * @return the sender of the message
     */
    public String getSender() {
        return sender.trim();
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender.trim();
    }

    /**
     * Gets the recipient of the message.
     *
     * @return the recipient of the message
     */
    public String getRecipient() {
        return recipient.trim();
    }

    /**
     * Sets the recipient of the message.
     *
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient.trim();
    }

    /**
     * Gets the date and time when the message was sent.
     *
     * @return the date and time when the message was sent
     */
    public String getDateSent() {
        return dateSent;
    }

     /**
     * Sets the date and time when the message was sent.
     *
     * @param dateSent the date and time to set
     */
    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    /**
     * Gets the content of the message.
     *
     * @return the content of the message
     */
    public String getMessage() {
        return messageContent.trim();
    }

    /**
     * Sets the content of the message.
     *
     * @param message the content of the message to set
     */
    public void setMessage(String message) {
        this.messageContent = message.trim();
    }

    /**
     * Generates a string representation of the Message object.
     *
     * @return a string representation of the Message object
     */
    @Override
    public String toString() {
        return "Message{"
                + ", sender=" + sender
                + ", recipient=" + recipient
                + ", dateSent=" + dateSent
                + ", message=" + messageContent
                + '}';
    }
}
