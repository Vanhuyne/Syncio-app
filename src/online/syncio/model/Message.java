package online.syncio.model;

import online.syncio.utils.TimeHelper;

/**
 * Represents a message with sender, date sent, and message content.
 */
public class Message {

    private String senderID;
    private String text;
    private String dateSent = TimeHelper.getCurrentDateTime();

    /**
     * Default constructor for the Message class.
     */
    public Message() {
    }

    /**
     * Constructs a Message object with the provided sender, recipient, date
     * sent, and message content.
     *
     * @param senderID the sender of the message
     * @param dateSent the date and time when the message was sent
     * @param text the content of the message
     */
    public Message(String dateSent, String text, String senderID) {
        this.dateSent = dateSent;
        this.text = text;
        this.senderID = senderID;
    }

    /**
     * Constructs a Message object with the provided sender, recipient, and
     * message content.
     *
     * @param senderID the sender of the message
     * @param text the content of the message
     */
    public Message(String senderID, String text) {
        this.senderID = senderID;
        this.text = text;
    }

    /**
     * Gets the senderID of the message.
     *
     * @return the senderID of the message
     */
    public String getSenderID() {
        return senderID;
    }

    /**
     * Sets the senderID of the message.
     *
     * @param senderID the senderID to set
     */
    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    /**
     * Gets the content of the message.
     *
     * @return the content of the message
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the content of the message.
     *
     * @param text the content of the message to set
     */
    public void setText(String text) {
        this.text = text;
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
     * Generates a string representation of the Message object.
     *
     * @return a string representation of the Message object
     */
    @Override
    public String toString() {
        return """
               \tMessage{
               \t\tsenderID=""" + senderID + "\n\t\t, text=" + text + "\n\t\t, dateSent=" + dateSent + "}\n\n";
    }

}
