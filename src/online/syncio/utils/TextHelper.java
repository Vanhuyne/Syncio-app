package online.syncio.utils;

import com.lambdaworks.crypto.SCryptUtil;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import online.syncio.component.MyTextPane;
import online.syncio.dao.MongoDBConnectOld;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

/**
 * The TextHelper class provides utility methods for working with passwords.
 */
public class TextHelper {
    
     // A map containing emojis and their corresponding colors
    private static final Map<String, Color> emojiColorMap = new HashMap<>();

    static {
        // Add emojis and their corresponding colors to the map
        emojiColorMap.put("👌", new Color(255, 204, 0));
        emojiColorMap.put("✨", new Color(255, 204, 0));
        emojiColorMap.put("👍", new Color(255, 204, 0));
        emojiColorMap.put("❤", new Color(255, 0, 0));
        emojiColorMap.put("📸", new Color(102, 102, 102));
        emojiColorMap.put("😂", Color.BLACK);
        emojiColorMap.put("😁", Color.BLACK);
        // Add more emojis and colors as needed
    }

    /**
     * Adds colored text, including emojis, to a JTextPane.
     *
     * @param textPane The JTextPane to which colored text will be added.
     * @param text     The text to be displayed, including emojis.
     */
    public static void addColoredText(JTextPane textPane, String text) {
        Document doc = textPane.getDocument();
        try {
            int pos = 0;
            int length = text.length();

            for (int i = 0; i < length; ) {
                int codePoint = text.codePointAt(i);
                int charCount = Character.charCount(codePoint);
                String emoji = text.substring(i, i + charCount);

                Color emojiColor = emojiColorMap.getOrDefault(emoji, Color.BLACK);

                String plainText = text.substring(pos, i);
                SimpleAttributeSet style = new SimpleAttributeSet();
                StyleConstants.setForeground(style, Color.BLACK);
                doc.insertString(doc.getLength(), plainText, style);

                StyleConstants.setForeground(style, emojiColor);
                StyleConstants.setFontSize(style, 16); // You can adjust the font size as needed
                doc.insertString(doc.getLength(), emoji, style);

                pos = i + charCount;
                i += charCount;
            }

            if (pos < length) {
                String remainingText = text.substring(pos);
                SimpleAttributeSet style = new SimpleAttributeSet();
                StyleConstants.setForeground(style, Color.BLACK);
                doc.insertString(doc.getLength(), remainingText, style);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        textPane.requestFocus();
    }
    
    
    
    
    
    
    
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
    
    
    
    
    private static Map<JTextField, String> textFieldPlaceholders = new HashMap<>();

    /**
     * Adds placeholder text to the specified text field.
     *
     * @param textField       the text field to add placeholder text to
     * @param placeholderText the placeholder text to be displayed
     */
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
    /**
     * Adds placeholder text to a JTextArea.
     *
     * @param textArea        The JTextArea to add placeholder text to.
     * @param placeholderText The placeholder text to be displayed.
     */
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
    
    
    /**
     * Generates a unique username from an email address.
     *
     * @param userEmail The user's email address.
     * @return A unique username generated from the email.
     */
    public static String generateUniqueUsernameFromEmail(String userEmail) {
        UserDAO userDAO = new UserDAOImpl(MongoDBConnectOld.getDatabase());
        String baseUsername = userEmail.split("@")[0].toLowerCase().replaceAll("[^a-zA-Z0-9_]", "_");
        String username = baseUsername;
        int count = 1;
        while (userDAO.checkUsername(username)) {
            username = baseUsername + count;
            count++;
        }
        return username;
    }
    
    
    /**
     * Censors bad words in a given text.
     *
     * @param text The text to be censored.
     * @return The censored text.
     */
    public static String censorBadWords(String text) {
        try {
            InputStream inputStream = TextHelper.class.getResourceAsStream("/online/syncio/resources/json/bad_words.json");
            if (inputStream != null) {
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                JSONArray badWordsArray = new JSONArray(new JSONTokener(reader));

                String[] badWords = new String[badWordsArray.length()];
                for (int i = 0; i < badWordsArray.length(); i++) {
                    badWords[i] = badWordsArray.getString(i);
                }

                for (String badWord : badWords) {
                    String replacement = "*".repeat(badWord.length());
                    text = text.replaceAll("\\b" + Pattern.quote(badWord) + "\\b", replacement);
                }

                System.out.println(text);
                reader.close();
            } else {
                System.out.println("Resource not found.");
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        
        return text;
    }
}
