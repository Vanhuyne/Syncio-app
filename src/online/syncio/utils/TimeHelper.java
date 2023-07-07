package online.syncio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The TimeHelper class provides utility methods for handling time-related operations.
 */
public class TimeHelper {
    
    /**
     * Retrieves the current date and time in the format "yyyy-MM-dd HH:mm:ss".
     * 
     * @return the current date and time as a string
     */
    public static String getCurrentDateTime() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(currentTime);
        
        return currentDateTime;
    }
}
