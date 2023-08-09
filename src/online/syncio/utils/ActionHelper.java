package online.syncio.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import online.syncio.dao.MongoDBConnect;

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
}
