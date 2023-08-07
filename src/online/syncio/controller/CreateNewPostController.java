package online.syncio.controller;

import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.utils.ImageFilter;
import online.syncio.utils.ImageHelper;
import online.syncio.view.CreateNewPost;
import org.bson.types.Binary;

public class CreateNewPostController {

    private CreateNewPost popup;
    private PostDAO postDAO = MongoDBConnect.getPostDAO();
    private UserDAO userDAO = MongoDBConnect.getUserDAO();

    public CreateNewPostController(CreateNewPost popup) {
        this.popup = popup;

    }

    public void uploadPost() {
        String userID = LoggedInUser.getCurrentUser().getId().toString();
        String caption = "";

        try {
            caption = popup.getTxtCaption().getDocument().getText(0, popup.getTxtCaption().getDocument().getLength()).trim();
            if (caption.equalsIgnoreCase("Write a caption...")) {
                caption = "";
            }
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
