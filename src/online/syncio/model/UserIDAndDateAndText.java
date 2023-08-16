package online.syncio.model;

import java.util.Objects;

public class UserIDAndDateAndText extends UserIDAndDate {

    public String text;

    public UserIDAndDateAndText() {
    }

    /**
     * Creates a new instance of UserIDAndDateAndText with the given user ID and text.
     *
     * @param userID The user ID associated with this entry.
     * @param text The text associated with this entry.
     */
    public UserIDAndDateAndText(String userID, String text) {
        this.userID = userID;
        this.text = text;
    }

    /**
     * Creates a new instance of UserIDAndDateAndText with the given user ID.
     *
     * @param userID The user ID associated with this entry.
     */
    public UserIDAndDateAndText(String userID) {
        this.userID = userID;
    }

    /**
     * Creates a new instance of UserIDAndDateAndText with the given user ID, date, and text.
     *
     * @param userID The user ID associated with this entry.
     * @param date The date associated with this entry.
     * @param text The text associated with this entry.
     */
    public UserIDAndDateAndText(String userID, String date, String text) {
        this.userID = userID;
        this.date = date;
        this.text = text;
    }

    /**
     * Returns the text associated with this entry.
     *
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text associated with this entry.
     *
     * @param text The new text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns a string representation of the UserIDAndDateAndText object.
     *
     * @return A string representation of the UserIDAndDateAndText object.
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
     * Checks if the given object is equal to this UserIDAndDateAndText object.
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
        UserIDAndDateAndText userIDAndDate = (UserIDAndDateAndText) o;
        return Objects.equals(userID, userIDAndDate.userID) && Objects.equals(date, userIDAndDate.date);
    }

    /**
     * Returns the hash code value for the UserIDAndDateAndText object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID, date);
    }
}
