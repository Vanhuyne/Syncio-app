package online.syncio.view;

import com.mongodb.client.FindIterable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.message.ChatBox;
import online.syncio.component.message.ChatEvent;
import online.syncio.dao.MessageDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Message;
import online.syncio.model.User;
import online.syncio.utils.OtherHelper;

public class MessagePanel extends ConnectionPanel {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private UserDAO userDAO;
    private PostDAO postDAO;
    private MessageDAO messageDAO;
    private FindIterable<MessagePanel> messageList;

    private User currentUser = LoggedInUser.getCurrentUser();
    private User messagingUser;

    private String lastSentDate = "";

    public MessagePanel() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
    }

    @Override
    public void setConnection(Main main) {
        this.main = OtherHelper.getMainFrame(this);
        this.database = MongoDBConnect.getDatabase();

        userDAO = MongoDBConnect.getUserDAO();
        postDAO = MongoDBConnect.getPostDAO();
        messageDAO = MongoDBConnect.getMessageDAO();

        loadExistingMessages();
    }

    public void loadExistingMessages() {

        if (currentUser.getUsername().equalsIgnoreCase("thuanID")) {
            messagingUser = userDAO.getByUsername("huygaID");
        } else {
            messagingUser = userDAO.getByUsername("thuanID");
        }

        chatArea.setTitle(messagingUser.getUsername());

        chatArea.addChatEvent(new ChatEvent() {
            @Override
            public void mousePressedSendButton(ActionEvent evt) {
                if (!chatArea.getText().isBlank()) {
                    Message m = new Message(currentUser.getUsername(),
                            messagingUser.getUsername(),
                            chatArea.getText());

                    messageDAO.add(m);
                    chatArea.addChatBox(m, ChatBox.BoxType.RIGHT);
                    chatArea.clearTextAndGrabFocus();
                }
            }

            @Override
            public void mousePressedFileButton(ActionEvent evt) {
            }

            @Override
            public void keyTyped(KeyEvent evt) {
            }
        });

        chatArea.getTxtMessage().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (!chatArea.getText().isBlank()) {
                        Message m = new Message(currentUser.getUsername(),
                                messagingUser.getUsername(),
                                chatArea.getText());

                        messageDAO.add(m);
                        chatArea.addChatBox(m, ChatBox.BoxType.RIGHT);
                        chatArea.clearTextAndGrabFocus();
                    }
                }
            }
        });

        FindIterable<Message> messageList = messageDAO.findAllByTwoUsernames(currentUser.getUsername(),
                messagingUser.getUsername());

        Thread thread = new Thread(() -> {
            for (Message m : messageList) {
                SwingUtilities.invokeLater(() -> {
                    if (m.getRecipient().equalsIgnoreCase(messagingUser.getUsername())) {
                        chatArea.addChatBox(m, ChatBox.BoxType.RIGHT);
                    } else {
                        chatArea.addChatBox(m, ChatBox.BoxType.LEFT);
                    }
                });

                if (!m.getSender().equalsIgnoreCase(currentUser.getUsername())) {
                    lastSentDate = m.getDateSent();
                }

                chatArea.revalidate();
                chatArea.repaint();

            }
        });

        thread.start();

        Runnable updateRecievingMessageTask = () -> {
            Message newMessage = messageDAO.findNewMessageWithCurrentUser(
                    currentUser.getUsername(), messagingUser.getUsername());

            if (newMessage != null) {
                if (!newMessage.getSender().equalsIgnoreCase(currentUser.getUsername())
                        && !newMessage.getDateSent().equalsIgnoreCase(lastSentDate)) {
                    SwingUtilities.invokeLater(() -> chatArea.addChatBox(newMessage, ChatBox.BoxType.LEFT));

                    lastSentDate = newMessage.getDateSent();
                }
            }
        };

        scheduler.scheduleAtFixedRate(updateRecievingMessageTask, 0, 3, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatArea = new online.syncio.component.message.ChatArea();

        setLayout(new java.awt.BorderLayout());

        chatArea.setPreferredSize(new java.awt.Dimension(800, 92));
        add(chatArea, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.message.ChatArea chatArea;
    // End of variables declaration//GEN-END:variables
}
