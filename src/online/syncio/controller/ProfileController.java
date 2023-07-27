package online.syncio.controller;

import java.util.List;
import online.syncio.component.ProfilePostPanel;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.view.OtherUserProfile;
import online.syncio.view.Profile;

public class ProfileController {

    private Profile profile;
    private OtherUserProfile otherProfile;

    private ProfilePostPanel postPanel;

    private User user;
    private UserDAO userDAO;

    private PostDAO postDAO;
    private List<Post> postList;

    public ProfileController(Profile profile) {
        this.profile = profile;
        this.postPanel = profile.getPnlProfilePost();

        userDAO = new UserDAOImpl(profile.database);
        postDAO = new PostDAOImpl(profile.database);
    }

    public ProfileController(OtherUserProfile otherProfile) {
        this.otherProfile = otherProfile;
        this.postPanel = otherProfile.getPnlProfilePost();

        userDAO = new UserDAOImpl(otherProfile.database);
        postDAO = new PostDAOImpl(otherProfile.database);
    }

    public void setCurrentUser(User user) {
        this.user = user;
        this.postList = postDAO.getAllByUserID(user.getIdAsString());

        loadUserData();
    }

    public void loadUserData() {
        String username = user.getUsername();

        String bio = user.getBio();

        int postNum = postList.size();

        int followers = user.getFollowers().size();

        if (profile != null) {
            profile.getLblUsername().setText(username);
            profile.getLblBio().setText(bio);
            profile.getLblPostNum().setText(postNum + " posts");
            profile.getLblFollowerNum().setText(followers + " followers");
        } else {
            otherProfile.getLblUsername().setText(username);
            otherProfile.getLblBio().setText(bio);
            otherProfile.getLblPostNum().setText(postNum + " posts");
            otherProfile.getLblFollowerNum().setText(followers + " followers");
        }

        postPanel.setUserPosts(postList);
    }
}
