package online.syncio.controller.user;

import com.mongodb.client.ChangeStreamIterable;
import java.util.List;
import javax.swing.SwingUtilities;
import online.syncio.component.GlassPanePopup;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.view.user.ErrorDetail;
import online.syncio.view.user.Profile;

/**
 * Controller class for managing user profiles and associated posts.
 */
public class ProfileController {

    private Profile pnlProfile;

    private PostDAO postDAO = MongoDBConnect.getPostDAO();

    private List<Post> postList;
    private User user;

    /**
     * Constructor for initializing the ProfileController.
     *
     * @param pnlProfile The associated Profile panel.
     */
    public ProfileController(Profile pnlProfile) {
        this.pnlProfile = pnlProfile;
    }

    /**
     * Loads the user profile with the specified user data.
     *
     * @param user The user whose profile is being loaded.
     */
    public void loadProfile(User user) {
        if (user == null) {
            return;
        }

        this.user = user; //user is showing

        this.postList = postDAO.getAllByUserID(user.getId().toString());

        if (user.getId().toString().equals(LoggedInUser.getCurrentUser().getId().toString())) {
            // own profile
            pnlProfile.loadOwnProfile(user);
        } else {
            // another user profile
            pnlProfile.loadOtherUserProfile(user);
        }

        pnlProfile.setProfileInfo(user);
        setupPostChangeStream();
    }

    /**
     * Sets up a change stream to monitor post updates in real-time.
     */
    private void setupPostChangeStream() {
        Thread thread = new Thread(() -> {
            ChangeStreamIterable<Post> changeStream = postDAO.getChangeStream();

            Thread changeStreamThread = new Thread(() -> {
                changeStream.forEach(changeDocument -> {
                    Post updatedPost = changeDocument.getFullDocument();
                    if (postList.size() != postDAO.getAllByUserID(user.getId().toString()).size()
                            && updatedPost != null && updatedPost.getUserID().equals(this.user.getId().toString())) {
                        SwingUtilities.invokeLater(() -> {
                            pnlProfile.getPnlProfilePost().addUserPost(updatedPost);
                        });
                    }
                });
            });

            changeStreamThread.start();

            SwingUtilities.invokeLater(() -> {
                pnlProfile.getPnlProfilePost().setUserPosts(postList);
            });

            try {
                changeStreamThread.join();
            } catch (InterruptedException e) {
                String errorInfo = e.getMessage();
                GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
            }
        });

        thread.start();
    }

    /**
     * Retrieves the current user associated with the profile.
     *
     * @return The current user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Retrieves the size of the user's post list.
     *
     * @return The size of the post list.
     */ 
    public int getPostListSize() {
        return postList.size();
    }

}
