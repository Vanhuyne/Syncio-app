package online.syncio.controller;

import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.utils.ImageHelper;
import online.syncio.view.PopupCreateNewPost;
import org.bson.types.Binary;

public class CreateNewPostController {

    private PopupCreateNewPost popup;
    private PostDAO postDAO;
    private UserDAO userDAO;

    
    
    public CreateNewPostController(PopupCreateNewPost popup) {
        this.popup = popup;

        postDAO = this.popup.getPostDAO();
        userDAO = this.popup.getUserDAO();
    }

    
    
    public void uploadPost() {
        String userID = LoggedInUser.getCurrentUser().getUsername();
        String caption = "";

        try {
            caption = popup.getTxtCaption().getDocument().getText(0, popup.getTxtCaption().getDocument().getLength()).trim();
        } catch (BadLocationException ex) {
        }

        ArrayList<Binary> lPhoto = new ArrayList<>();
        for (String photo : popup.getImagePaths()) {
            lPhoto.add(new Binary(ImageHelper.readAsByte(photo)));
        }

        if ((caption.equals("") || caption.equalsIgnoreCase(popup.getTxtCaption().getPlaceholder())) && lPhoto.isEmpty()) {
            GlassPanePopup.showPopup(new MyDialog("Error", "Please select an image or add a caption before sharing the post"), "dialog");
            return;
        }
        
        if (userDAO.getByID(LoggedInUser.getCurrentUser().getIdAsString()) == null) {
            GlassPanePopup.showPopup(new MyDialog("Error", "Your account is not available. Cannot add the post.\nPlease try again later."), "dialog");
            return;
        }

        Post post = new Post(userID, caption, lPhoto);

        boolean result = postDAO.add(post);
        this.popup.uploadNotification(result);
    }
}
