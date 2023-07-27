package online.syncio.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import online.syncio.model.Post;
import online.syncio.utils.ImageHelper;
import org.bson.types.Binary;

public class ProfilePostPanel extends JPanel {

    private final int COLUMNS = 3;
    private int cellWidth = 0;

    private List<Post> userPosts;
    private JPanel contentPanel; // New panel to hold the components

    public ProfilePostPanel() {
        this.setOpaque(false);

        // Initialize the content panel
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(800, 600));
        contentPanel.setLayout(new GridLayout(0, COLUMNS, 10, 10));

        add(contentPanel, BorderLayout.CENTER);
    }

    private void loadUserPost() {
        contentPanel.removeAll(); // Use contentPanel instead of "this"

        // Create a thread for loading and displaying posts
        Thread thread = new Thread(() -> {
            for (Post p : userPosts) {
                SwingUtilities.invokeLater(() -> {
                    contentPanel.add(createPostPanel(p)); // Use contentPanel instead of "this"

                });
                contentPanel.revalidate(); // Use contentPanel instead of "this"
                contentPanel.repaint(); // Use contentPanel instead of "this"
            }
        });

        // Start the thread
        thread.start();
    }

    private MyPanel createPostPanel(Post post) {
        if (cellWidth == 0) {
            int gridWidth = this.getWidth();
            cellWidth = gridWidth / COLUMNS;
        }

        MyPanel postPanel = new MyPanel();
        postPanel.setSize(new Dimension(cellWidth, cellWidth));

        postPanel.setBackground(Color.WHITE);

        List<Binary> photoList = post.getLPhoto();

        if (!photoList.isEmpty()) {
            postPanel.setImg(ImageHelper.readBinaryAsBufferedImage(photoList.get(0)));
        } else {
            postPanel.setBackground(Color.BLACK);
        }

        return postPanel;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;

        loadUserPost();
    }
}
