package online.syncio.controller;

import static org.mockito.Mockito.*;
import online.syncio.dao.UserDAO;
import online.syncio.model.User;
import online.syncio.utils.TextHelper;
import online.syncio.view.Signup;
import org.assertj.swing.edt.GuiActionRunner;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignupTest {

    private FrameFixture window;

    @Before
    public void setUp() {
      Signup frame = GuiActionRunner.execute(() -> new Signup());
      window = new FrameFixture(frame);
      window.show(); // shows the frame to test
    }

    
    
    @Test
    public void shouldShowErrorDialogOnEmptyEmail() {
        testErrorDialog("", "testuser", "mypassword", "mypassword", "Error");
    }
    
    @Test
    public void shouldShowErrorDialogOnInvalidEmail() {
        testErrorDialog("invalidEmail", "testuser", "mypassword", "mypassword", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnInvalidUsername() {
        testErrorDialog("test@example.com", "user123*", "mypassword", "mypassword", "Error");
    }
    
    @Test
    public void shouldShowErrorDialogOnEmptyUsername() {
        testErrorDialog("test@example.com", "", "mypassword", "mypassword", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnShortPassword() {
        testErrorDialog("test@example.com", "testuser", "pas", "pas", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnLongPassword() {
        testErrorDialog("test@example.com", "testuser", "averylongpasswordthatexceedsthirtycharacters", "averylongpasswordthatexceedsthirtycharacters", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnPasswordMismatch() {
        testErrorDialog("test@example.com", "testuser", "password1", "password2", "Error");
    }
    
    // Add more test cases for specific scenarios
    private void testErrorDialog(String email, String username, String password, String passwordConfirm, String expectedErrorMessage) {
        window.textBox("txtEmail").enterText(email);
        window.textBox("txtUsername").enterText(username);
        window.textBox("txtPassword").enterText(password);
        window.textBox("txtPasswordConfirm").enterText(passwordConfirm);
        window.button("btnSignup").click();

        // Find the label that displays the error message
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText(expectedErrorMessage);
    }
    
    
    
    @Test
    public void shouldShowErrorDialogOnExistingEmail() {
        String existingEmail = "accountToTest@gmail.com";
        String username = "testuser";

        // Set up the userDAO to return existing username and email
        Signup signup = window.targetCastedTo(Signup.class);
        SignupController signupController = new SignupController(signup) {
            @Override
            public void signupAuthentication(int type) {
                if (type == 0) {
                    // Simulate existing email
                    userDAO = mock(UserDAO.class);
                    when(userDAO.checkEmail(existingEmail)).thenReturn(true);
                }
                super.signupAuthentication(type);
            }
        };

        // Enter existing email
        window.textBox("txtEmail").enterText(existingEmail);
        window.textBox("txtUsername").enterText(username);
        window.textBox("txtPassword").enterText("mypassword");
        window.textBox("txtPasswordConfirm").enterText("mypassword");
        window.button("btnSignup").click();

        // Verify the error message is shown
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText("Email Address Already Taken");
    }
    
    
    
    @Test
    public void shouldShowErrorDialogOnIncorrectOTP() {
        String email = "fancyaccountemailtotest@example.com";
        String username = TextHelper.generateUniqueUsernameFromEmail(email);

        // Set up the signupController to use a specific OTP
        Signup signup = window.targetCastedTo(Signup.class);
        SignupController signupController = new SignupController(signup) {
            @Override
            public void signupAuthentication(int type) {
                if (type == 1) {
                    when((signup.getOtp() + "").equals(signup.getTxtUsername().getText())).thenReturn(true);
                }
                super.signupAuthentication(type);
            }
        };

        // Enter OTP and click Verify button
        window.textBox("txtEmail").enterText(email);
        window.textBox("txtUsername").enterText(username);
        window.textBox("txtPassword").enterText("mypassword");
        window.textBox("txtPasswordConfirm").enterText("mypassword");
        window.button("btnSignup").click();
        
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText("Email Sent with OTP");
        window.button("btnDismiss").click();
        
        // Enter incorrect OTP and click Verify button
        window.textBox("txtUsername").setText("000000");
        window.button("btnSignup").click();

        lblTitle = window.label("lblTitle");
        lblTitle.requireText("Wrong OTP Code");
    }

    
    @Test
    public void shouldCreateAccountOnCorrectOTP() {
        String email = "fancyaccountemailtotest@example.com"; //delete in mongodb before run
        String username = TextHelper.generateUniqueUsernameFromEmail(email);

        // Set up the signupController to use a specific OTP
        Signup signup = window.targetCastedTo(Signup.class);
        SignupController signupController = new SignupController(signup) {
            @Override
            public void signupAuthentication(int type) {
                if (type == 1) {
                    // Simulate correct OTP
                    if (signup.getTxtUsername().getText().equals(signup.getOtp() + "")) {
                        // Hash password and add user
                        String password = "mypassword";
                        String hashedPassword = TextHelper.HashPassword(password);
                        boolean result = userDAO.add(new User(username, hashedPassword, email, null, 0, 0, null));
                        when(result).thenReturn(true);
                    }
                }
                super.signupAuthentication(type);
            }
        };

        // Enter OTP and click Verify button
        window.textBox("txtEmail").enterText(email);
        window.textBox("txtUsername").enterText(username);
        window.textBox("txtPassword").enterText("mypassword");
        window.textBox("txtPasswordConfirm").enterText("mypassword");
        window.button("btnSignup").click();
        
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText("Email Sent with OTP");
        window.button("btnDismiss").click();
        
        // Enter correct OTP and click Verify button
        window.textBox("txtUsername").setText(signup.getOtp() + "");
        window.button("btnSignup").click();
        
        FrameFixture mainFrame = findFrame("Login").using(window.robot());
        lblTitle = mainFrame.label("lblTitle");
        lblTitle.requireText("Account Created");
    }
    
    

    @After
    public void tearDown() {
        window.cleanUp();
    }
    
}
