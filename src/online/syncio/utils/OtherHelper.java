package online.syncio.utils;

import java.awt.Component;
import java.awt.Window;
import java.util.prefs.Preferences;
import javax.swing.SwingUtilities;
import online.syncio.view.admin.MainAdmin;
import online.syncio.view.user.Main;

/**
 * Utility class providing helper methods for various operations.
 */
public class OtherHelper {

    /**
     * Retrieves the main frame from a given component.
     *
     * @param component the component from which to retrieve the main frame
     * @return the main frame of the application
     */
    public static Main getMainFrame(Component component) {
        Window ancestorWindow = SwingUtilities.getWindowAncestor(component);

        if (ancestorWindow instanceof Main) {
            return (Main) ancestorWindow;
        } else {
            return null;
        }
    }

    /**
     * Retrieves the main admin frame from a given component.
     *
     * @param component the component from which to retrieve the main admin frame
     * @return the main admin frame of the application
     */
    public static MainAdmin getMainAdmin(Component component) {
        Window ancestorWindow = SwingUtilities.getWindowAncestor(component);

        if (ancestorWindow instanceof MainAdmin) {
            return (MainAdmin) ancestorWindow;
        } else {
            return null;
        }
    }

    /**
     * Saves a session value using Java Preferences.
     *
     * @param KEY   the key associated with the value to be saved
     * @param value the value to be saved in the session
     */
    public static void saveSessionValue(String KEY, String value) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        preferences.put(KEY, value);
    }

    /**
     * Retrieves a session value using Java Preferences.
     *
     * @param KEY the key associated with the value to be retrieved
     * @return the value retrieved from the session, or null if not found
     */
    public static String getSessionValue(String KEY) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        return preferences.get(KEY, null);
    }

    /**
     * Deletes a session value using Java Preferences.
     *
     * @param KEY the key associated with the value to be deleted
     */
    public static void deleteSessionValue(String KEY) {
        Preferences preferences = Preferences.userNodeForPackage(OtherHelper.class);
        preferences.remove(KEY);
    }
}

