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
    private int panelWidth = 800;
    private int gap = 4;
    private int cellWidth = (panelWidth - (gap * (COLUMNS - 1))) / COLUMNS;

    private List<Post> userPosts;
    private JPanel contentPanel; // New panel to hold the components
    private int numRows = 0;

    public ProfilePostPanel() {
        this.setOpaque(false);

        // Initialize the content panel
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(panelWidth, cellWidth));
        contentPanel.setLayout(new GridLayout(0, COLUMNS, gap, gap));

        add(contentPanel, BorderLayout.CENTER);
    }

    private void loadUserPost() {
        contentPanel.removeAll(); // Use contentPanel instead of "this"
        numRows = 0; // Reset the number of rows

        // Create a thread for loading and displaying posts
        Thread thread = new Thread(() -> {
            for (Post p : userPosts) {
                SwingUtilities.invokeLater(() -> {
                    contentPanel.add(createPostPanel(p)); // Use contentPanel instead of "this"
                    numRows++;
                    
                    if(numRows % COLUMNS > 0) {
                        contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), (Math.ceilDiv(numRows, COLUMNS)) * cellWidth));
                    }
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
