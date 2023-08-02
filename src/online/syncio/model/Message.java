package online.syncio.model;

import online.syncio.utils.TimeHelper;

public class Message {

    private String sender;
    private String recipient;
    private String dateSent = TimeHelper.getCurrentDateTime();
    private String message;

    public Message() {
    }

    public Message(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public Message(String sender, String recipient, String dateSent, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.dateSent = dateSent;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{"
                + ", sender=" + sender
                + ", recipient=" + recipient
                + ", dateSent=" + dateSent
                + ", message=" + message
                + '}';
    }
}
