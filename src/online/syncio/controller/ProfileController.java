package online.syncio.controller;

import java.util.List;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.view.Profile;

public class ProfileController {

    private Profile profile;
    private User currentUser;
    private UserDAO userDAO;

    private PostDAO postDAO;
    private List<Post> postList;

    public ProfileController(Profile profile) {
        this.profile = profile;
        userDAO = new UserDAOImpl(profile.database);
        postDAO = new PostDAOImpl(profile.database);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        this.postList = postDAO.getAllByUserID(currentUser.getUsername());
        loadUserData();
    }

    public void loadUserData() {
//        if (currentUser.getAvatar() != null) {
//            byte[] avt = currentUser.getAvatar();
//            Image avatar = ImageHelper.byteToImage(avt);
//
//            profile.getLblAvatar().setIcon(ImageHelper.resizing(avatar,
//                    profile.getLblAvatar().getWidth(),
//                    profile.getLblAvatar().getHeight()));
//        }

        String username = currentUser.getUsername();
        profile.getLblUsername().setText(username);

        int postNum = postList.size();
        System.out.println(postNum);
        profile.getLblPostNum().setText(postNum + " posts");

        int followers = currentUser.getFollowers().size();
        profile.getLblFollowerNum().setText(followers + " followers");
    }
}
