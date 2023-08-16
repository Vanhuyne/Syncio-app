package online.syncio.model;

import java.util.ArrayList;
import java.util.Objects;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

/**
 * Represents a user in the system.
 */
public class User {

    private ObjectId id;
    private String username = "";
    private String password = "";
    private String email = "";
    public Binary avt;
    private String bio = "";
    private int role;
    private String dateCreated = TimeHelper.getCurrentDateTime();
    private int flag;
    private ArrayList<UserIDAndDate> following = new ArrayList<>();

    /**
     * Default constructor for the User class.
     */
    public User() {
    }

    /**
     * Constructs a User object with specified properties.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email address of the user
     * @param bio the biography or description of the user
     * @param role the role of the user
     * @param flag a flag associated with the user
     * @param following a list of users that this user is following
     */
    public User(String username, String password, String email, String bio, int role, int flag, ArrayList<UserIDAndDate> following) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.role = role;
        this.flag = flag;
        this.following = following;
    }

    public User(ObjectId id, String username, String password, String email, String bio, int role, int flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.role = role;
        this.flag = flag;
    }

    public User(String id, String username, String password, String email, String bio, int role, int flag) {
        this.id = new ObjectId(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.role = role;
        this.flag = flag;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return The unique identifier of the user.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Returns the unique identifier of the user as string.
     *
     * @return The unique identifier of the user as string.
     */
    public String getIdAsString() {
        return id.toString();
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The new unique identifier for the user.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the avatar (profile picture) of the user.
     *
     * @return The avatar of the user.
     */
    public Binary getAvt() {
        return avt;
    }

    /**
     * Sets the avatar (profile picture) of the user.
     *
     * @param avt The new avatar for the user.
     */
    public void setAvt(Binary avt) {
        this.avt = avt;
    }

    /**
     * Returns the biography of the user.
     *
     * @return The biography of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the biography of the user.
     *
     * @param bio The new biography for the user.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Returns the role of the user.
     *
     * @return The role of the user.
     */
    public int getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The new role for the user.
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Returns the date when the user's account was created.
     *
     * @return The date when the user's account was created.
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date when the user's account was created.
     *
     * @param dateCreated The date when the user's account was created.
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Returns the flag associated with the user.
     *
     * @return The flag associated with the user.
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Sets the flag associated with the user.
     *
     * @param flag The flag associated with the user.
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Returns the list of users followed by this user.
     *
     * @return The list of users followed by this user.
     */
    public ArrayList<UserIDAndDate> getFollowing() {
        return following;
    }

    /**
     * Sets the list of users followed by this user.
     *
     * @param following The list of users followed by this user.
     */
    public void setFollowing(ArrayList<UserIDAndDate> following) {
        this.following = following;
    }

    /**
     * Checks if the given object is equal to this User object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(avt, user.avt) && Objects.equals(bio, user.bio) && Objects.equals(role, user.role) && Objects.equals(flag, user.flag) && Objects.equals(following, user.following);
    }

    /**
     * Returns the hash code value for the User object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, avt, bio, role, dateCreated, flag, following);
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + ", bio=" + bio + ", role=" + role + ", dateCreated=" + dateCreated + ", flag=" + flag + ", following=" + following + '}';
    }

}
