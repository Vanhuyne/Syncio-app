package online.syncio.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import online.syncio.dao.MongoDBConnect;
import online.syncio.view.user.Main;

public class ActionHelper {

    public static void assignEnterKeyListener(JButton button, JTextField... textFields) {
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        };

        for (JTextField textField : textFields) {
            textField.addKeyListener(enterKeyListener);
        }
    }
    
    public static void assignEnterKeyListener(JButton button, JEditorPane... textPanes) {
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        };

        for (JEditorPane textPane : textPanes) {
            textPane.addKeyListener(enterKeyListener);
        }
    }
    
    
    
    public static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Perform cleanup tasks here, such as closing the MongoDB connection
            MongoDBConnect.close();
        }));
    }
    
    
    
    public static void restartApplication() {
        // Get the current Java executable path
        String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        // Get the main class name
        String mainClassName = Main.class.getName();

        // Construct the command to restart the application
        List<String> command = new ArrayList<>();
        command.add(javaPath);
        command.add("-cp");
        command.add(System.getProperty("java.class.path"));
        command.add(mainClassName);

        // Start a new process to restart the application
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            processBuilder.start();
            System.exit(0); // Exit the current instance of the application
        } catch (IOException e) {
            e.printStackTrace();
            // Handle restart errors
        }
    }
    
    
    
    public static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
}
