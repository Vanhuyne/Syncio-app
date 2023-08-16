package online.syncio.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import online.syncio.resources.fonts.MyFont;

/**
 * Utility class for initializing and applying common properties to Swing components.
 */
public class ComponentInit {

    /**
     * Initializes and returns a Font with specified boldness based on the existing component's font size.
     * @param bold The boldness level: 0 for regular, 1 for medium, 2 for bold.
     * @param component The component for which to set the font.
     * @return The Font with the specified boldness.
     */
    public static Font fontStyle(int bold, JComponent component) {
        float existingFont = component.getFont().getSize();

        switch (bold) {
            case 0:
                return new MyFont().getSFProDisplayRegular().deriveFont(existingFont);
            case 1:
                return new MyFont().getSFProDisplayMedium().deriveFont(existingFont);
            case 2:
                return new MyFont().getSFProDisplayBold().deriveFont(existingFont);
            default:
                break;
        }

        return null;
    }

    /**
     * Applies common properties to a JLabel component.
     * @param label The JLabel to apply properties to.
     */
    public static void applyProperties(JLabel label) {
        label.setBackground(new Color(0f, 0f, 0f, 0f));
    }

    /**
     * Applies common properties to a JTextField component.
     * @param textField The JLabel to apply properties to.
     */
    public static void applyProperties(JTextField textField) {
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textField.setBorder(new EmptyBorder(1, 5, 1, 5));
    }

    /**
     * Applies common properties to a JPasswordField component.
     * @param passwordField The JLabel to apply properties to.
     */
    public static void applyProperties(JPasswordField passwordField) {
        passwordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordField.setBorder(new EmptyBorder(1, 5, 1, 5));
    }

    /**
     * Applies common properties to a JTextArea component.
     * @param textArea The JTextArea to apply properties to.
     */
    public static void applyProperties(JTextArea textArea) {
        textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textArea.setBorder(new EmptyBorder(3, 5, 3, 5));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    /**
    * Applies custom properties to a JComboBox component.
    *
    * @param comboBox The JComboBox component to which properties will be applied.
    */
    public static void applyProperties(JComboBox<?> comboBox) {

    }

    /**
     * Applies common properties to a JRadioButton component.
     *
     * @param radioButton The JRadioButton to apply properties to.
     */
    public static void applyProperties(JRadioButton radioButton) {
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        radioButton.setForeground(Color.BLACK);
    }
    
    /**
     * Applies common properties to a JCheckBox component.
     *
     * @param checkBox The JCheckBox to apply properties to.
     */
    public static void applyProperties(JCheckBox checkBox) {
        checkBox.setFocusPainted(false);
        checkBox.setBorderPainted(false);
        checkBox.setForeground(Color.BLACK);
    }

    /**
     * Applies common properties to a JButton component.
     * @param button The JPasswordField to apply properties to.
     */
    public static void applyProperties(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
    }

    /**
     * Applies common properties to a JTable component.
     * @param table The JPasswordField to apply properties to.
     */
    public static void applyProperties(JTable table) {

    }

    /**
     * Applies common properties to a MyTable component.
     *
     * @param table The MyTable to apply properties to.
     */
    public static void applyProperties(MyTable table) {
        table.setFontHeaderBold(2);
    }

    /**
     * Applies common properties to a JComponent, including specialized properties based on the component type.
     * @param component The JComponent to apply properties to.
     */
    public static void applyCommonProperties(JComponent component) {
        component.setOpaque(false);
        component.setFont(new MyFont().getSFProDisplayMedium());
        component.setFont(component.getFont().deriveFont(14f));
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (component instanceof JTable) {
            applyProperties((JTable) component);
        }

        if (component instanceof JButton) {
            applyProperties((JButton) component);
        }

        if (component instanceof JComboBox) {
            applyProperties((JComboBox<?>) component);
        }
        
        if (component instanceof JCheckBox) {
            applyProperties((JCheckBox) component);
        }

        if (component instanceof JTextArea) {
            applyProperties((JTextArea) component);
        }

        if (component instanceof JPasswordField) {
            applyProperties((JPasswordField) component);
        }

        if (component instanceof JTextField) {
            applyProperties((JTextField) component);
        }

        if (component instanceof JLabel) {
            applyProperties((JLabel) component);
        }

    }
}
