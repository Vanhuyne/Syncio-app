package online.syncio.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import online.syncio.model.User;

/**
 * Data Access Object (DAO) interface for managing User entities.
 */
public interface UserDAO extends DAO<User> {

    /**
     * Authenticates a user by checking their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated User instance if successful, or null if authentication fails.
     */
    public User authentication(String username, String password);

    /**
     * Checks if an email exists in the database.
     *
     * @param email The email to check.
     * @return True if the email exists, false otherwise.
     */
    public boolean checkEmail(String email);

    /**
     * Checks if a username exists in the database.
     *
     * @param username The username to check.
     * @return True if the username exists, false otherwise.
     */
    public boolean checkUsername(String username);

    /**
     * Updates a user's password using their email.
     *
     * @param password The new password.
     * @param email The email of the user.
     * @return The number of affected rows in the database.
     */
    public int updateByEmail(String password, String email);

    /**
     * Retrieves all users from the database.
     *
     * @return A MongoCollection containing all User instances.
     */
    public MongoCollection<User> getAllByCollection();

    /**
     * Retrieves users based on username, email, role, and flag criteria.
     *
     * @param isReload True if the data should be reloaded, false otherwise.
     * @param usernameOrEmail The username or email to filter by.
     * @param role The role to filter by.
     * @param flag The flag to filter by.
     * @return A FindIterable containing User instances based on the criteria.
     */
    public FindIterable<User> getAllByUsernameOrEmailRoleFlag(boolean isReload, String usernameOrEmail, Integer role, Integer flag);

    /**
     * Counts the number of posts associated with a user.
     *
     * @param userID The ID of the user.
     * @return The count of posts associated with the user.
     */
    public long countPost(String userID);

    /**
     * Retrieves a user based on their email.
     *
     * @param email The email of the user.
     * @return The User instance with the specified email.
     */
    public User getByEmail(String email);

    /**
     * Updates a user's username using their email.
     *
     * @param username The new username.
     * @param email The email of the user.
     * @return The number of affected rows in the database.
     */
    public int updateUsernameByEmail(String username, String email);

    /**
     * Updates a user's biography using their email.
     *
     * @param bio The new biography.
     * @param email The email of the user.
     * @return The number of affected rows in the database.
     */
    public int updateBioByEmail(String bio, String email);

    /**
     * Toggles the follow relationship between two users.
     *
     * @param currentUserID The ID of the current user.
     * @param followedUserID The ID of the user to follow/unfollow.
     * @return The number of affected rows in the database.
     */
    public int toggleFollow(String currentUserID, String followedUserID);

    /**
     * Retrieves the count of followers for a user.
     *
     * @param userId The ID of the user.
     * @return The count of followers for the user.
     */
    public int getFollowerCount(String userId);

    /**
     * Retrieves a user based on their username.
     *
     * @param username The username of the user.
     * @return The User instance with the specified username.
     */
    public User getByUsername(String username);
}
