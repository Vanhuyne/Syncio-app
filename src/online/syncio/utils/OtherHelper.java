package online.syncio.utils;

import java.util.prefs.Preferences;

/**
 * Utility class providing helper methods for various operations.
 */
public class OtherHelper {

    public static void saveSessionValue(String KEY, String value) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        preferences.put(KEY, value);
    }

    public static String getSessionValue(String KEY) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        return preferences.get(KEY, null);
    }

    public static void deleteSessionValue(String KEY) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        preferences.remove(KEY);
    }
}
