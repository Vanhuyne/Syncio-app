package online.syncio.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import online.syncio.model.MyFont;

public class ComponentInit {
    public static Font fontStyle(int bold, JComponent component) {
        float existingFont = component.getFont().getSize();

        if(bold == 0) return new MyFont().getSFProDisplayRegular().deriveFont(existingFont);
        else if(bold == 1) return new MyFont().getSFProDisplayMedium().deriveFont(existingFont);
        else if(bold == 2) return new MyFont().getSFProDisplayBold().deriveFont(existingFont);
        
        return null;
    }
    
    
    
    public static void applyProperties(JLabel label) {
        label.setBackground(new Color(0f, 0f, 0f, 0f));
    }

    public static void applyProperties(JTextField textField) {
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textField.setBorder(new EmptyBorder(1, 5, 1, 5));
    }
    
    public static void applyProperties(JPasswordField passwordField) {
        passwordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordField.setBorder(new EmptyBorder(1, 5, 1, 5));
    }
    
    public static void applyProperties(JTextArea textArea) {
        textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textArea.setBorder(new EmptyBorder(3, 5, 3, 5));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    public static void applyProperties(JComboBox<?> comboBox) {
        
    }
    
    public static void applyProperties(JRadioButton radioButton) {
        radioButton.setBorderPainted(false);
        radioButton.setForeground(Color.BLACK);
    }

    public static void applyProperties(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
    }
    
    public static void applyProperties(JTable table) {
        
    }
    
    public static void applyProperties(MyTable table) {
        table.setFontHeaderBold(2);
    }
    
    
    
    public static void applyCommonProperties(JComponent component) {
        component.setOpaque(false);
        component.setFont(new MyFont().getSFProDisplayMedium());
        component.setFont(component.getFont().deriveFont(14f));
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        
        if (component instanceof JLabel) {
            applyProperties((JLabel) component);
        } else if (component instanceof JTextField) {
            applyProperties((JTextField) component);
        } else if (component instanceof JPasswordField) {
            applyProperties((JPasswordField) component);
        } else if (component instanceof JTextArea) {
            applyProperties((JTextArea) component);
        } else if (component instanceof JComboBox) {
            applyProperties((JComboBox<?>) component);
        } else if (component instanceof JButton) {
            applyProperties((JButton) component);
        } else if (component instanceof JTable) {
            applyProperties((JTable) component);
        }
        
    }
}
