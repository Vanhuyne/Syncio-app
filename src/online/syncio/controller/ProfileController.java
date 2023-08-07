package online.syncio.controller;

import com.mongodb.client.ChangeStreamIterable;
import java.util.List;
import javax.swing.SwingUtilities;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.view.Profile;

public class ProfileController {

    private Profile pnlProfile;

    private PostDAO postDAO = MongoDBConnect.getPostDAO();

    private List<Post> postList;
    private User user;

    public ProfileController(Profile pnlProfile) {
        this.pnlProfile = pnlProfile;
    }

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
                e.printStackTrace();
            }
        });

        thread.start();
    }

    public User getUser() {
        return user;
    }

    public int getPostListSize() {
        return postList.size();
    }

}
