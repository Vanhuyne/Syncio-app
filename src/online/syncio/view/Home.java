package online.syncio.view;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.resources.images.other.MayULike;

public class Home extends JPanel {

    private Main main = Main.getInstance();
    private User currentUser;
    private String currentUserID;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private PostDAO postDAO = MongoDBConnect.getPostDAO();
    private List<String> postIDList = new ArrayList<>();
    private List<String> followerIDList = new ArrayList<>();

    private int curIndex = 0;
    FindIterable<Post> posts;
    FindIterable<Post> postsOther;
    private MongoCursor<ChangeStreamDocument<Post>> changeStreamCursor;

    public Home() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        if (LoggedInUser.isUser()) {
            currentUser = LoggedInUser.getCurrentUser();
            currentUserID = currentUser.getId().toString();
            posts = postDAO.getAllPostOfFollowers(currentUser);

            // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            loadMorePosts();
        } else {
            System.out.println("chưa đăng nhập");
        }

        // Add an AdjustmentListener to the vertical scrollbar of the scroll pane
        scrollPane.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            // Update the position of pnlSearch relative to the pnlHome container
            main.getPnlSearch().setBounds(main.getPnlSearch().getX(), getY(), main.getPnlSearch().getWidth(), main.getPnlSearch().getHeight());
            main.getPnlSearch().revalidate();
            main.getPnlSearch().repaint();
        });

    }

    public void addLoading() {
        feedPanel.add(lblLoading);
    }

    public void removeLoading() {
        lblLoading.setText("");
        feedPanel.remove(lblLoading);
    }

    private void loadMorePosts() {
        // Create a thread for loading and displaying posts
        Thread thread = new Thread(() -> {
            // Set up MongoDB change stream
            ChangeStreamIterable<Post> changeStream = postDAO.getChangeStream();

            // Listen for change stream events in a separate thread
            Thread changeStreamThread = new Thread(() -> {
                changeStream.forEach(changeDocument -> {
                    // Handle the change event here
                    // For example, extract the new post data from changeDocument and update your feed UI
                    Post newPost = changeDocument.getFullDocument();
                    if (newPost != null) {
                        SwingUtilities.invokeLater(() -> {
                            PostUI postUI = new PostUI(newPost.getId().toString(), currentUserID);
                            removeLoading();
                            feedPanel.add(postUI);
                            addLoading();
                            feedPanel.revalidate();
                            feedPanel.repaint();
                        });
                    }
                });
            });
            changeStreamThread.start();

            // Load initial posts from the regular database query
            for (Post post : posts) {
                // Check if pnlSearch is visible before adding PostUI components
                while (isSearchPanelVisible()) {
                    try {
                        Thread.sleep(50); // Wait for 100 milliseconds before checking again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                PostUI postUI = new PostUI(post.getId().toString(), currentUserID);
                SwingUtilities.invokeLater(() -> {
                    removeLoading();
                    feedPanel.add(postUI);
                    addLoading();
                    feedPanel.revalidate();
                    feedPanel.repaint();
                });
            }
            
            removeLoading();
            feedPanel.add(new MayULike());
            addLoading();
            postsOther = postDAO.getAllPostOther(currentUser);

            // Load initial posts from the regular database query
            for (Post post : postsOther) {
                // Check if pnlSearch is visible before adding PostUI components
                while (isSearchPanelVisible()) {
                    try {
                        Thread.sleep(50); // Wait for 100 milliseconds before checking again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                PostUI postUI = new PostUI(post.getId().toString(), currentUserID);
                SwingUtilities.invokeLater(() -> {
                    removeLoading();
                    feedPanel.add(postUI);
                    addLoading();
                    feedPanel.revalidate();
                    feedPanel.repaint();
                });
            }

            // Wait for the change stream thread to finish (you can use other synchronization mechanisms if needed)
            try {
                changeStreamThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start the thread
        thread.start();
    }

    public boolean isSearchPanelVisible() {
        return main.getPnlSearch().isVisible();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        feedPanel = new online.syncio.component.MyPanel();
        lblLoading = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlMain.setRoundBottomRight(20);
        pnlMain.setLayout(new java.awt.BorderLayout());

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        feedPanel.setBackground(new java.awt.Color(255, 255, 255));
        feedPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 340, 0, 340));
        feedPanel.setMaximumSize(new java.awt.Dimension(1080, 679));
        feedPanel.setMinimumSize(new java.awt.Dimension(1080, 679));
        feedPanel.setRoundBottomRight(20);
        feedPanel.setLayout(new javax.swing.BoxLayout(feedPanel, javax.swing.BoxLayout.Y_AXIS));

        lblLoading.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/loading.gif"))); // NOI18N
        lblLoading.setText("It may take some time for loading the first post...");
        lblLoading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLoading.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        feedPanel.add(lblLoading);

        scrollPane.setViewportView(feedPanel);

        pnlMain.add(scrollPane, java.awt.BorderLayout.CENTER);

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyPanel feedPanel;
    private online.syncio.component.MyLabel lblLoading;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
