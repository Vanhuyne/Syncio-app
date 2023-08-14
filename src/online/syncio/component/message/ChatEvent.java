package online.syncio.component.message;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Interface for defining chat-related events.
 */
public interface ChatEvent {

    /**
     * Triggered when the send button is pressed.
     * @param evt The ActionEvent associated with the send button press.
     */
    public void mousePressedSendButton(ActionEvent evt);

    /**
     * Triggered when the file button is pressed.
     * @param evt The ActionEvent associated with the file button press.
     */
    public void mousePressedFileButton(ActionEvent evt);

    /**
     * Triggered when a key is typed.
     * @param evt The KeyEvent associated with the key typing.
     */
    public void keyTyped(KeyEvent evt);
}
