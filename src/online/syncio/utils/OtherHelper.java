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
    
    public static MainAdmin getMainAdmin(Component component) {
        Window ancestorWindow = SwingUtilities.getWindowAncestor(component);
    
        if (ancestorWindow instanceof MainAdmin) {
            return (MainAdmin) ancestorWindow;
        } else {
            return null;
        }
    }

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
