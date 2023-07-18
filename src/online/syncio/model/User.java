package online.syncio.model;

import java.util.ArrayList;
import online.syncio.utils.TimeHelper;

public class User {
    private String userID;
    private String username;
    private String password;
    private String email;
    private String bio;
    private int role;
    private String dateCreated = TimeHelper.getCurrentDateTime();
    private int flag;
    private ArrayList<UserIDAndDate> followers = new ArrayList<>();

    public User() {
    }

    public User( String username, String password, String email, String bio, int role, int flag, ArrayList<UserIDAndDate> followers) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.role = role;
        this.flag = flag;
        this.followers = followers;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public ArrayList<UserIDAndDate> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserIDAndDate> followers) {
        this.followers = followers;
    }
    
    
}
