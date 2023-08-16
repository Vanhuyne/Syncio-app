package online.syncio.model;

import java.util.Objects;
import online.syncio.utils.TimeHelper;

/**
 * Represents a combination of user ID and date, often used in various contexts.
 */
public class UserIDAndDate {
    public String userID;
    public String date = TimeHelper.getCurrentDateTime();

    public UserIDAndDate() {
    }

    /**
     * Creates a new instance of UserIDAndDate with the given user ID and current date.
     *
     * @param userID The user ID associated with this entry.
     */
    public UserIDAndDate(String userID) {
        this.userID = userID;
    }
    
    /**
     * Creates a new instance of UserIDAndDate with the given user ID and date.
     *
     * @param userID The user ID associated with this entry.
     * @param date The date associated with this entry.
     */
    public UserIDAndDate(String userID, String date) {
        this.userID = userID;
        this.date = date;
    }

    /**
     * Returns the user ID associated with this entry.
     *
     * @return The user ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the user ID associated with this entry.
     *
     * @param userID The new user ID.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Returns the date associated with this entry.
     *
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date associated with this entry.
     *
     * @param date The new date.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Returns a string representation of the UserIDAndDate object.
     *
     * @return A string representation of the UserIDAndDate object.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserIDAndDate{");
        sb.append("userID=").append(userID);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
    
    /**
     * Checks if the given object is equal to this UserIDAndDate object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserIDAndDate userIDAndDate = (UserIDAndDate) o;
        return Objects.equals(userID, userIDAndDate.userID) && Objects.equals(date, userIDAndDate.date);
    }

    /**
     * Returns the hash code value for the UserIDAndDate object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID, date);
    }
}
