package online.syncio.controller;

import online.syncio.controller.user.ForgotController;
import javax.swing.JButton;
import static org.mockito.Mockito.*;
import online.syncio.view.login.Forgot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ForgotTest {

    private FrameFixture window;
    private Forgot forgotView;

    @Before
    public void setUp() {
        forgotView = GuiActionRunner.execute(() -> new Forgot());
        window = new FrameFixture(forgotView);
        window.show(); // shows the frame to test

    }

    @Test
    public void shouldShowErrorDialogOnEmptyEmail() {
        testErrorDialog("", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnInvalidEmail() {
        testErrorDialog("invalidEmail", "Error");
    }

    @Test
    public void shouldShowErrorDialogOnIncorrectOTP() {
        String email = "accountToTest@gmail.com";

        // Set up the signupController to use a specific OTP
        Forgot forgot = window.targetCastedTo(Forgot.class);
        ForgotController forgotController = new ForgotController(forgot) {
            @Override
            public void forgotAuthentication(String type) {
                if (type.equals("1")) {
                    when((forgot.getOtp() + "").equals(forgot.getTxtOTP().getText())).thenReturn(true);
                }
                super.forgotAuthentication(type);
            }
        };

        // Enter OTP and click Verify button
        window.textBox("txtEmail").enterText(email);
        window.button("btnGetOTP").click();

        // custom search criteria (the button's text)
        JButtonFixture button = window.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return "Verify Code".equals(button.getText());
            }
        });

        window.textBox("txtOTP").enterText("000000");
        window.button("btnGetOTP").click();

        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText("Error code");
    }

    // Add more test cases for specific scenarios
    private void testErrorDialog(String email, String expectedErrorMessage) {
        window.textBox("txtEmail").enterText(email);
        window.button("btnGetOTP").click();

        // Find the label that displays the error message
        JLabelFixture lblTitle = window.label("lblTitle");
        lblTitle.requireText(expectedErrorMessage);
    }

    @Test
    public void shouldChangePasswordOnCorrectOTP() {
        String email = "accountToTest@gmail.com";

        // Set up the signupController to use a specific OTP
        Forgot forgot = window.targetCastedTo(Forgot.class);
        ForgotController forgotController = new ForgotController(forgot) {
            @Override
            public void forgotAuthentication(String type) {
                if (type.equals("1")) {
                    when((forgot.getOtp() + "").equals(forgot.getTxtOTP().getText())).thenReturn(true);
                }
                super.forgotAuthentication(type);
            }
        };

        // Enter OTP and click Verify button
        window.textBox("txtEmail").enterText(email);
        window.button("btnGetOTP").click();

        // custom search criteria (the button's text)
        JButtonFixture button = window.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return "Verify Code".equals(button.getText());
            }
        });

        window.textBox("txtOTP").enterText(forgot.getOtp() + "");
        window.button("btnGetOTP").click();

        // custom search criteria (the button's text)
        JButtonFixture buttonReset = window.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return "Reset Password".equals(button.getText());
            }
        });

        window.textBox("txtPassword").enterText("1");
        window.textBox("txtPasswordConfirm").enterText("1");
        window.button("btnGetOTP").click();

        FrameFixture mainFrame = findFrame("Login").using(window.robot());
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

}
