package online.syncio.model;

import java.util.Objects;

public class UserIDAndDateAndText extends UserIDAndDate {

    public String text;

    public UserIDAndDateAndText() {
    }

    public UserIDAndDateAndText(String userID, String text) {
        this.userID = userID;
        this.text = text;
    }

    public UserIDAndDateAndText(String userID) {
        this.userID = userID;
    }

    public UserIDAndDateAndText(String userID, String date, String text) {
        this.userID = userID;
        this.date = date;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserIDAndDate{");
        sb.append("userID=").append(userID);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(userID, date);
    }
}
