package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import online.syncio.model.User;

public interface UserDAO extends DAO<User> {

    public User authentication(String username, String password);

    public boolean checkEmail(String email);

    public boolean checkUsername(String username);

    public int updateByEmail(String password, String email);

    public MongoCollection<User> getAllByCollection();

    public FindIterable<User> getAllByUsernameOrEmailRoleFlag(String usernameOrEmail, Integer role, Integer flag);

    public long countPost(String userID);

    public User getByEmail(String email);

    public int updateUsernameByEmail(String username, String email);

    public int updateBioByEmail(String bio, String email);

    public int toggleFollow(String currentUserID, String followedUserID);

    public int getFollowerCount(String userId);

    public User getByUsername(String username);
}
