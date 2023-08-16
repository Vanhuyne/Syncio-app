package online.syncio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class for working with date and time operations.
 */
public class TimeHelper {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private TimeHelper() {
    }

    /**
     * Gets the current date and time in the format "yyyy-MM-dd HH:mm:ss".
     *
     * @return The current date and time as a formatted string.
     */
    public static String getCurrentDateTime() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentTime);
    }

}

