package online.syncio.model;

import java.util.ArrayList;
import java.util.Objects;
import online.syncio.utils.TimeHelper;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

public class User {

    private ObjectId id;
    private String username;
    private String password;
    private String email;
    public Binary avt;
    private String bio;
    private int role;
    private String dateCreated = TimeHelper.getCurrentDateTime();
    private int flag;
    private ArrayList<UserIDAndDate> following = new ArrayList<>();

    public User() {
    }

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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Binary getAvt() {
        return avt;
    }

    public void setAvt(Binary avt) {
        this.avt = avt;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ArrayList<UserIDAndDate> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<UserIDAndDate> following) {
        this.following = following;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, avt, bio, role, dateCreated, flag, following);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + ", bio=" + bio + ", role=" + role + ", dateCreated=" + dateCreated + ", flag=" + flag + ", following=" + following + '}';
    }

}
