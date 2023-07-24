package online.syncio.model;

import java.util.Objects;
import online.syncio.utils.TimeHelper;

public class UserIDAndDate {
    public String userID;
    public String date = TimeHelper.getCurrentDateTime();

    public UserIDAndDate() {
    }

    public UserIDAndDate(String userID) {
        this.userID = userID;
    }
    
    public UserIDAndDate(String userID, String date) {
        this.userID = userID;
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserIDAndDate userIDAndDate = (UserIDAndDate) o;
        return Objects.equals(userID, userIDAndDate.userID) && Objects.equals(date, userIDAndDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, date);
    }
}
