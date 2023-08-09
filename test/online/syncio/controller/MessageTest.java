package online.syncio.controller;

import java.util.Set;
import online.syncio.dao.MessageDAO;
import online.syncio.dao.MongoDBConnect;
import online.syncio.view.login.Login;
import org.assertj.swing.edt.GuiActionRunner;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import static org.assertj.swing.timing.Pause.pause;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    JPanelFixture messageView;
    MessageDAO msgDAO = MongoDBConnect.getMessageDAO();

    final String username = "thuanID";
    final String password = "1";

    String messagingUser;

    @Before
    public void setUp() {
        Login frame = GuiActionRunner.execute(() -> new Login());
        FrameFixture window = new FrameFixture(frame);
        window.show();

        window.textBox("txtUser").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.button("btnLogin").click();

        FrameFixture mainWindow = findFrame("Main").withTimeout(10000).using(window.robot());
        mainWindow.button("message").click();

        messageView = mainWindow.panel("message");
    }

    @Test
    public void showMessagedUsersOnPanelUserList() {
        JPanelFixture pnlHistoryUserList = messageView.panel("userList");

        Set<String> messagedUserSet = msgDAO.getMessagingUsers(username);

        // If the history is shown on GUI
        for (String name : messagedUserSet) {
            if (!pnlHistoryUserList.panel(name.toLowerCase().trim()).isEnabled()) {
                break;
            }
            messagingUser = name.toLowerCase().trim();
        }
    }

    @Test
    public void openChatAreaWhenUserCardClicked() {
        JPanelFixture pnlHistoryUserList = messageView.panel("userList");

        // Simulate interaction: Click on a user's card
        pnlHistoryUserList.panel(messagingUser).click();

        // Pause to allow the panel to update
        pause(500);

        // Verify that the correct chat area is displayed
        messageView.panel("chatArea").requireVisible();
        messageView.panel("chatArea").panel(messagingUser).requireVisible();
    }
}
