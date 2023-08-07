package online.syncio.controller;

import online.syncio.view.Login;
import org.assertj.swing.edt.GuiActionRunner;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    private FrameFixture window;

    @Before
    public void setUp() {
      Login frame = GuiActionRunner.execute(() -> new Login());
      window = new FrameFixture(frame);
      window.show(); // shows the frame to test
    }

    
    
    @Test
    public void shouldOpenMainWhenClickingButton() {
        String username = "accountToTest";
        String password = "1";
        
        window.textBox("txtUser").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.button("btnLogin").click();
      
        // now the interesting part, we need to wait till the main window is shown.
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());
    }
    
    
    
    @Test
    public void shouldShowErrorDialogOnIncorrectUsernameAndPassword() {
        testErrorDialog("accountToTest1", "123", "Incorrect Username or Password");
    }

    @Test
    public void shouldShowErrorDialogOnIncorrectPassword() {
        testErrorDialog("accountToTest", "123", "Incorrect Username or Password");
    }

    @Test
    public void shouldShowErrorDialogOnEmptyPassword() {
        testErrorDialog("accountToTest", "", "Error");
    }
    
    @Test
    public void shouldShowErrorDialogOnIncorrectUsername() {
        testErrorDialog("accountToTest123", "1", "Incorrect Username or Password");
    }

    @Test
    public void shouldShowErrorDialogOnEmptyUsername() {
        testErrorDialog("", "1", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnEmptyUsernameAndPassword() {
        testErrorDialog("", "", "Error");
    }

    
    
    private void testErrorDialog(String username, String password, String errorTitle) {
        window.textBox("txtUser").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.button("btnLogin").click();

        // Find the label that displays the error message
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText(errorTitle);
    }
    
    
    
    @After
    public void tearDown() {
      window.cleanUp();
    }
    
}
