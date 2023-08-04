package online.syncio.model;

import online.syncio.utils.TimeHelper;

public class Message {

    private String sender;
    private String recipient;
    private String dateSent = TimeHelper.getCurrentDateTime();
    private String messageContent;

    public Message() {
    }

    public Message(String sender, String recipient, String message) {
        this.sender = sender.trim();
        this.recipient = recipient.trim();
        this.messageContent = message.trim();
    }

    public Message(String sender, String recipient, String dateSent, String message) {
        this.sender = sender.trim();
        this.recipient = recipient.trim();
        this.dateSent = dateSent;
        this.messageContent = message.trim();
    }

    public String getSender() {
        return sender.trim();
    }

    public void setSender(String sender) {
        this.sender = sender.trim();
    }

    public String getRecipient() {
        return recipient.trim();
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient.trim();
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getMessage() {
        return messageContent.trim();
    }

    public void setMessage(String message) {
        this.messageContent = message.trim();
    }

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
