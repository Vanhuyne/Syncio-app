package online.syncio.controller;

import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.timing.Pause.pause;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Test;
import online.syncio.view.login.Login;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class UpdateUsernameTest {
    String username = "accountToTest";
    String password = "1";
    String newUsername = "accountToTestNew";

    private FrameFixture window;

    @Before
    public void setUp() {
        Login frame = GuiActionRunner.execute(() -> new Login());
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }



    @Test
    public void shouldUpdateUsername() {
        shouldLoadCorrectUserData();

        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());

        // Enter the new username
        mainFrame.textBox("txtUsername").deleteText().enterText(newUsername);
        mainFrame.label("lblChangeUsername").click();

        // Find the label that displays the error message
        mainFrame.label("lblTitle").requireText("Update Successful");
        
        MongoDBConnect.connect();
        UserDAO userDAO = MongoDBConnect.getUserDAO();
        User u = userDAO.getByID(LoggedInUser.getCurrentUser().getId().toString());
        assertEquals(u.getUsername(), mainFrame.textBox("txtUsername").text());
        
        username = newUsername;
    }
    
    
//    @Test
    public void shouldNavigationToEditProfile() {
        window.textBox("txtUser").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.button("btnLogin").click();
        
        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());

        pause(3000);
        mainFrame.button("profile").click();
        
        pause(3000);
        mainFrame.button("btnEditProfileMessage").click();
    }
    
    
    @Test
    public void shouldLoadCorrectUserData() {
        shouldNavigationToEditProfile();
        
        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());
        User u = LoggedInUser.getCurrentUser();
        
        // Perform assertions to test if the user data is correctly loaded
        assertEquals(u.getUsername(), mainFrame.textBox("txtUsername").text());
        assertEquals(u.getPassword(), mainFrame.textBox("txtPassword").text());
        assertEquals(u.getEmail(), mainFrame.textBox("txtEmail").text());
        assertEquals(u.getBio(), mainFrame.textBox("txtBio").text());
    }
    
    
    
    @Test
    public void shouldShowErrorDialogOnEmptyUsername() {
        shouldLoadCorrectUserData();

        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());
        mainFrame.textBox("txtUsername").deleteText();
        mainFrame.label("lblChangeUsername").click();
        
        // Find the label that displays the error message
        mainFrame.label("lblTitle").requireText("Error");
    }

    @Test
    public void shouldShowErrorDialogOnInvalidUsername() {
        shouldLoadCorrectUserData();

        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());
        
        mainFrame.textBox("txtUsername").deleteText().enterText("!@#");
        mainFrame.label("lblChangeUsername").click();

        // Find the label that displays the error message
        mainFrame.label("lblTitle").requireText("Error");
    }
    
    @Test
    public void shouldShowErrorDialogOnExistingUsername() {
        shouldLoadCorrectUserData();

        // Wait until the main frame becomes visible
        FrameFixture mainFrame = findFrame("Main").withTimeout(10000).using(window.robot());
        
        mainFrame.textBox("txtUsername").deleteText().enterText("56duong");
        mainFrame.label("lblChangeUsername").click();

        // Find the label that displays the error message
        mainFrame.label("lblTitle").requireText("Username Already Taken");
    }
    
    
    
    @After
    public void tearDown() {
        window.cleanUp();
    }
}
