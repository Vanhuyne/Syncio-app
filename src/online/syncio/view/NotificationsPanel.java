package online.syncio.view;

import com.mongodb.client.FindIterable;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import online.syncio.component.MyLabel;
import online.syncio.component.SearchedUserCard;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.UserIDAndDateAndText;

public class NotificationsPanel extends JPanel {

    private static Main main = Main.getInstance();
    private static UserDAO userDAO = MongoDBConnect.getUserDAO();
    private static PostDAO postDAO = MongoDBConnect.getPostDAO();
    
    private String CONFIG_FILE_PATH = "/online/syncio/config/config.properties";
    private static final String DESIRED_DATETIME_KEY = "desiredDateTime";
    private Date desiredDateTime;
    
    private static FindIterable<Post> posts;
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // Format of your date-time string
    private static InputStream is;
    
    MyLabel lblLoading;
            
    public NotificationsPanel() {
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        setOpaque(true);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        is = getClass().getResourceAsStream(CONFIG_FILE_PATH);
    }
    
    
    
    public void readDesiredDateTime(String userID) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH)) {
            properties.load(inputStream);
            String desiredDateTimeStr = properties.getProperty(userID + "." + DESIRED_DATETIME_KEY);
            if (desiredDateTimeStr != null) {
                desiredDateTime = dateTimeFormat.parse(desiredDateTimeStr);
                System.out.println("Desired date and time for user " + userID + ": " + desiredDateTime);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void writeDesiredDateTime(String userID, Date date) {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.setProperty(userID + "." + DESIRED_DATETIME_KEY, dateTimeFormat.format(date));

        try (OutputStream outputStream = new FileOutputStream("src/" + CONFIG_FILE_PATH)) {
            properties.store(outputStream, "Desired Date and Time for Users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    // Method to display notifications based on the desiredDateTime
    public void displayNotifications() {
        pnlResult.removeAll();
        
        String currentUserID = LoggedInUser.getCurrentUser().getId().toString();
        posts = postDAO.getAllByUserIDFindIterable(currentUserID);
        
        readDesiredDateTime(currentUserID);
        
        boolean filterComments = desiredDateTime != null; // Check if desiredDateTime is set
        
        for (Post post : posts) {
            List<UserIDAndDateAndText> comments = post.getCommentList();
            Collections.reverse(comments);

            int nums = 0;
            for (UserIDAndDateAndText comment : comments) {
                String commentDateTimeStr = comment.getDate();
                if (commentDateTimeStr != null) {
                    // Check if the date is not null
                    try {
                        Date commentDateTime = dateTimeFormat.parse(commentDateTimeStr);

                        if (desiredDateTime != null && commentDateTime.after(desiredDateTime)) {
//                            System.out.println(comment.getDate() + "-" + comment.getText());
                            nums++;
                        }
                        else {
                            break;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(nums > 0) {
                pnlResult.add(new SearchedUserCard(post.getId().toString(), nums + " new comments on your post", comments.get(0).getDate()));
            }
        }
        
        pnlResult.revalidate();
        pnlResult.repaint();
    }
    
    
    
    public void addLastNotification() {
        
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        pnlResult = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        setMaximumSize(new java.awt.Dimension(314, 679));
        setMinimumSize(new java.awt.Dimension(314, 679));
        setPreferredSize(new java.awt.Dimension(314, 679));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(219, 219, 219)));
        pnlMain.setMaximumSize(new java.awt.Dimension(314, 679));
        pnlMain.setMinimumSize(new java.awt.Dimension(314, 679));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);

        pnlResult.setBackground(new java.awt.Color(255, 255, 255));
        pnlResult.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));
        pnlResult.setLayout(new javax.swing.BoxLayout(pnlResult, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnlResult);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlResult;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
