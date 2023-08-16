package online.syncio.controller.user;

import com.mongodb.client.FindIterable;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyLabel;
import online.syncio.component.SearchedCard;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.UserIDAndDateAndText;
import online.syncio.view.user.ErrorDetail;
import online.syncio.view.user.NotificationsPanel;

/**
 * Controller class for managing user notifications.
 */
public class NotificationController {

    private NotificationsPanel pnlNoti;

    private static PostDAO postDAO = MongoDBConnect.getPostDAO();

    private final String CONFIG_FILE_PATH = "/online/syncio/config/config.properties";
    private static final String DESIRED_DATETIME_KEY = "desiredDateTime";
    private Date desiredDateTime;

    private FindIterable<Post> postList;
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Format of your date-time string

    /**
     * Constructs a NotificationController with the provided NotificationsPanel.
     *
     * @param pnlNoti The NotificationsPanel instance.
     */
    public NotificationController(NotificationsPanel pnlNoti) {
        this.pnlNoti = pnlNoti;
    }

    /**
     * Reads the desiredDateTime value from the configuration.
     *
     * @param userID The user ID.
     */
    public void readDesiredDateTime(String userID) {


        Calendar calendar = Calendar.getInstance();
        // Subtract 3 days from the current date
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        Date threeDaysAgo = calendar.getTime();
        desiredDateTime = threeDaysAgo;
    }

    /**
     * Writes the desiredDateTime value to the configuration.
     *
     * @param userID The user ID.
     * @param date   The date to write.
     */
    public void writeDesiredDateTime(String userID, Date date) {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            String errorInfo = e.getMessage();
            GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
        }

        properties.setProperty(userID + "." + DESIRED_DATETIME_KEY, dateTimeFormat.format(date));

        try (OutputStream outputStream = new FileOutputStream("src/" + CONFIG_FILE_PATH)) {
            properties.store(outputStream, "Desired Date and Time for Users");
        } catch (IOException e) {
            String errorInfo = e.getMessage();
            GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
        }
    }

    // Method to display notifications based on the desiredDateTime
    /**
     * Displays notifications based on the desiredDateTime.
     */
    public void displayNotifications() {
        SwingUtilities.invokeLater(() -> {
            pnlNoti.getPnlResult().removeAll();
            pnlNoti.getPnlResult().revalidate();
            pnlNoti.getPnlResult().repaint();

            String currentUserID = LoggedInUser.getCurrentUser().getId().toString();
            postList = postDAO.getAllByUserIDFindIterable(currentUserID);

            readDesiredDateTime(currentUserID);

            MyLabel lblLast3Days = new MyLabel("Last 3 days");
            lblLast3Days.setPreferredSize(new Dimension(300, 30));
            lblLast3Days.setBorder(new EmptyBorder(5, 0, 5, 0));
            lblLast3Days.setAlignmentX(Component.CENTER_ALIGNMENT);

            pnlNoti.getPnlResult().add(lblLast3Days);
            for (Post post : postList) {
                List<UserIDAndDateAndText> comments = post.getCommentList();
                Collections.reverse(comments);

                int nums = countNewComments(comments);

                if (nums > 0) {
                    pnlNoti.getPnlResult().add(createNotificationCard(post, nums));
                }
            }

            // Revalidate and repaint after adding new notifications
            pnlNoti.getPnlResult().revalidate();
            pnlNoti.getPnlResult().repaint();
        });
    }

    /**
     * Counts the number of new comments after the desiredDateTime.
     *
     * @param comments The list of comments.
     * @return The count of new comments.
     */
    private int countNewComments(List<UserIDAndDateAndText> comments) {
        int nums = 0;

        for (UserIDAndDateAndText comment : comments) {
            String commentDateTimeStr = comment.getDate();

            if (commentDateTimeStr != null) {
                try {
                    Date commentDateTime = dateTimeFormat.parse(commentDateTimeStr);

                    if (desiredDateTime != null && commentDateTime.after(desiredDateTime)) {
                        nums++;
                    } else {
                        break;
                    }
                } catch (ParseException e) {
                    String errorInfo = e.getMessage();
                    GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
                }
            }
        }

        return nums;
    }

    /**
     * Creates a notification card.
     *
     * @param post              The post to create a notification for.
     * @param newCommentsCount The count of new comments.
     * @return The created SearchedUserCard instance.
     */
    private SearchedCard createNotificationCard(Post post, int newCommentsCount) {
        String notificationText = newCommentsCount + " new comments on your post";
        String notificationDate = post.getCommentList().get(0).getDate();

        return new SearchedCard(post.getId().toString(), notificationText, notificationDate);
    }

}
