package online.syncio.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

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
}
