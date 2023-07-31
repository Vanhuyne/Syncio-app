package online.syncio.utils;

import com.lambdaworks.crypto.SCryptUtil;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;

/**
 * The TextHelper class provides utility methods for working with passwords.
 */
public class TextHelper {
    
    /**
     * Hashes a password using the SCrypt hashing algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password
     */
    public static String HashPassword(String password) {
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
        return generatedSecuredPasswordHash;
    }
    
    
    
    /**
     * Authenticates a password by checking it against a hashed password.
     *
     * @param password the password to be authenticated
     * @param hashPassword the hashed password to be compared against
     * @return true if the password is authenticated, false otherwise
     */
    public static boolean authenticationPasswordHash(String password, String hashPassword) {
        return SCryptUtil.check(password, hashPassword);
    }
    
    
    
    /**
     * Adds placeholder text to the specified text field.
     *
     * @param textField       the text field to add placeholder text to
     * @param placeholderText the placeholder text to be displayed
     */
    private static Map<JTextField, String> textFieldPlaceholders = new HashMap<>();

    public static void addPlaceholderText(JTextField textField, String placeholderText) {
        // Save the default foreground color of the text field
        Color defaultColor = textField.getForeground();

        // Set the current placeholder text for the specific text field
        if (placeholderText != null) {
            textFieldPlaceholders.put(textField, placeholderText);
            textField.setText(placeholderText);
            textField.setForeground(Color.GRAY);

            // Add a focus listener to handle the placeholder text
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    String currentText = textField.getText();
                    String placeholderText = textFieldPlaceholders.get(textField);
                    if (currentText.equals(placeholderText)) {
                        textField.setText("");
                        textField.setForeground(defaultColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    String currentText = textField.getText();
                    if (currentText.isEmpty()) {
                        String placeholderText = textFieldPlaceholders.get(textField);
                        textField.setForeground(Color.GRAY);
                        textField.setText(placeholderText);
                    }
                }
            });
        }
    }
    
    
    
    private static Map<JTextArea, String> textAreaPlaceholders = new HashMap<>();
    public static void addPlaceholderText(JTextArea textArea, String placeholderText) {
        // Save the default foreground color of the text field
        Color defaultColor = textArea.getForeground();

        // Set the current placeholder text for the specific text field
        if (placeholderText != null) {
            textAreaPlaceholders.put(textArea, placeholderText);
            textArea.setText(placeholderText);
            textArea.setForeground(Color.GRAY);

            // Add a focus listener to handle the placeholder text
            textArea.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    String currentText = textArea.getText();
                    String placeholderText = textAreaPlaceholders.get(textArea);
                    if (currentText.equals(placeholderText)) {
                        textArea.setText("");
                        textArea.setForeground(defaultColor);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    String currentText = textArea.getText();
                    if (currentText.isEmpty()) {
                        String placeholderText = textAreaPlaceholders.get(textArea);
                        textArea.setForeground(Color.GRAY);
                        textArea.setText(placeholderText);
                    }
                }
            });
        }
    }
    
    
    
    /**
     * Adds placeholder text to the specified text field.
     *
     * @param textPane       the text pane to add placeholder text to
     * @param placeholderText the placeholder text to be displayed
     */
    public static void addPlaceholderText(JTextPane textPane, String placeholderText) {
        // Save the default foreground color of the text field
        Color defaultColor = textPane.getForeground();
        
        // Set the placeholder text
        textPane.setText(placeholderText);
        textPane.setForeground(Color.GRAY);

        // Add a focus listener to handle the placeholder text
        textPane.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    if (textPane.getDocument().getText(0, textPane.getDocument().getLength()).trim().equals(placeholderText)) {
                        textPane.setText("");
                        textPane.setForeground(defaultColor);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (textPane.getDocument().getText(0, textPane.getDocument().getLength()).isEmpty()) {
                        textPane.setForeground(Color.GRAY);
                        textPane.setText(placeholderText);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    
    
    public static String generateUniqueUsernameFromEmail(String userEmail) {
        UserDAO userDAO = new UserDAOImpl(MongoDBConnect.getDatabase());
        String baseUsername = userEmail.split("@")[0].toLowerCase().replaceAll("[^a-zA-Z0-9_]", "_");
        String username = baseUsername;
        int count = 1;
        while (userDAO.checkUsername(username)) {
            username = baseUsername + count;
            count++;
        }
        return username;
    }
}
