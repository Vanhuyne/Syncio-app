package online.syncio.utils;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import online.syncio.view.Main;

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
        Main f = (Main) SwingUtilities.getWindowAncestor(component);
        return f;
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
