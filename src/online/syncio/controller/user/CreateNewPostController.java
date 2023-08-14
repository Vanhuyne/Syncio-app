package online.syncio.controller.user;

import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.utils.ImageFilter;
import online.syncio.utils.ImageHelper;
import online.syncio.utils.TextHelper;
import online.syncio.view.user.CreateNewPost;
import org.bson.types.Binary;

/**
 * The `CreateNewPostController` class manages the creation and uploading of new posts. It handles the user interface interactions and communicates with the `PostDAO` and `UserDAO` to add a new post to the database.
 *
 * The controller obtains the necessary data from the user interface, including the user's ID, caption, and images for the post. It performs validation on the input data, such as checking for the number of selected images and the presence of a caption. Additionally, the controller applies image filters to the selected images if specified.
 *
 * The `uploadPost()` method is responsible for creating a new `Post` object with the provided data and using the `PostDAO` to add it to the database. It also triggers a notification in the user interface upon a successful post upload.
 */
public class CreateNewPostController {

    private CreateNewPost popup;
    private PostDAO postDAO = MongoDBConnect.getPostDAO();
    private UserDAO userDAO = MongoDBConnect.getUserDAO();

    /**
     * Constructs a `CreateNewPostController` with the associated popup user interface.
     *
     * @param popup The popup user interface for creating new posts.
     */
    public CreateNewPostController(CreateNewPost popup) {
        this.popup = popup;

    }

    
    /**
     * Uploads a new post to the database based on user input.
     */
    public void uploadPost() {
        String userID = LoggedInUser.getCurrentUser().getId().toString();
        String caption = "";

        try {
            caption = popup.getTxtCaption().getDocument().getText(0, popup.getTxtCaption().getDocument().getLength()).trim();
            if (caption.equalsIgnoreCase("Write a caption...")) {
                caption = "";
            }
            
            caption = TextHelper.censorBadWords(caption);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        ArrayList<Binary> lPhoto = new ArrayList<>();
        ArrayList<String> imagePaths = new ArrayList<>();
        imagePaths = popup.getImagePaths();

        if (imagePaths.size() > 5) {
            GlassPanePopup.showPopup(new MyDialog("Error", "You can only select between 0 and 5 images."), "dialog");
            return;
        }

        int expectWidth = 720;
        for (int i = 0; i < imagePaths.size(); i++) {
            String path = imagePaths.get(i);

            if (null == popup.getImageFilter().get(i)) {
                lPhoto.add(ImageHelper.resizingAndCompressingWidthToBinary(ImageHelper.stringToBufferedImage(path), expectWidth, 1f));
            } else {
                switch (popup.getImageFilter().get(i)) {
                    case 1 ->
                        lPhoto.add(ImageFilter.toGrayScale2(ImageHelper.resizingAndCompressingWidthToBinary(ImageHelper.stringToBufferedImage(path), expectWidth, 1f)));
                    case 2 ->
                        lPhoto.add(ImageFilter.brighten(ImageHelper.resizingAndCompressingWidthToBinary(ImageHelper.stringToBufferedImage(path), expectWidth, 1f), 10));
                    default ->
                        lPhoto.add(ImageHelper.resizingAndCompressingWidthToBinary(ImageHelper.stringToBufferedImage(path), expectWidth, 1f));
                }
            }
        }
        if ((caption.equals("") || caption.equalsIgnoreCase(popup.getTxtCaption().getPlaceholder())) && lPhoto.isEmpty()) {
            GlassPanePopup.showPopup(new MyDialog("Error", "Please select an image or add a caption before sharing the post"), "dialog");
            return;
        }

        if (userDAO.getByID(LoggedInUser.getCurrentUser().getId().toString()) == null) {
            GlassPanePopup.showPopup(new MyDialog("Error", "Your account is not available. Cannot add the post.\nPlease try again later."), "dialog");
            return;
        }

        Post post = new Post(userID, caption, lPhoto);

        boolean result = postDAO.add(post);

        if (result) {
            this.popup.uploadNotification();
        }
    }
}
