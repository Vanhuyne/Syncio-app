package online.syncio.view.admin;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import online.syncio.component.GlassPanePopup;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.view.user.ErrorDetail;

public class HiddenPost extends JPanel {

    private User currentUser;
    private String currentUserID;
    private PostDAO postDAO = MongoDBConnect.getPostDAO();
    FindIterable<Post> postsReport;

    public HiddenPost() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        currentUser = LoggedInUser.getCurrentUser();
        currentUserID = currentUser.getId().toString();
        postsReport = postDAO.getAllReportedPost();

        // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        loadReportedPosts();
    }

    public void addLoading() {
        feedPanel.add(lblLoading);
    }

    public void removeLoading() {
        lblLoading.setText("");
        feedPanel.remove(lblLoading);
    }

    private void loadReportedPosts() {
        // Create a thread for loading and displaying posts
        Thread thread = new Thread(() -> {
            int postsLoaded = 0;

            // Set up MongoDB change stream
            ChangeStreamIterable<Post> changeStream = postDAO.getChangeStream();

            // Listen for change stream events in a separate thread
            Thread changeStreamThread = new Thread(() -> {
                changeStream.forEach(changeDocument -> {
                    // Handle the change event here
                    // For example, extract the new post data from changeDocument and update your feed UI
                    Post newPost = changeDocument.getFullDocument();
                    if (newPost != null) {
                        if (newPost.getFlag() == 1) {
                            SwingUtilities.invokeLater(() -> {
                                PostUIReport postUIReport = new PostUIReport(newPost.getId().toString(), newPost.getUserID());
                                addPostUI(postUIReport);
                            });
                        }
                    }
                });
            });
            changeStreamThread.start();

            // Load initial posts from the regular database query
            for (Post post : postsReport) {
                if (post.getFlag() == 1) {
                    try {
                        Thread.sleep(40); // Wait for 100 milliseconds before checking again
                    } catch (InterruptedException e) {
                        String errorInfo = e.getMessage();
                        GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
                    }

                    PostUIReport postUIReport = new PostUIReport(post.getId().toString(), currentUserID);
                    SwingUtilities.invokeLater(() -> {
                        removeLoading();
                        feedPanel.add(postUIReport);
                        addLoading();
                        feedPanel.revalidate();
                        feedPanel.repaint();
                    });

                    postsLoaded++;

                    if (postsLoaded >= 5) {
                        // Introduce a 2-second delay after loading 5 posts
                        try {
                            Thread.sleep(3000); // 3000 milliseconds = 2 seconds
                        } catch (InterruptedException e) {
                            String errorInfo = e.getMessage();
                            GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
                        }

                        postsLoaded = 0; // Reset the counter
                    }
                }

            }
            // Wait for the change stream thread to finish (you can use other synchronization mechanisms if needed)
            try {
                changeStreamThread.join();
            } catch (InterruptedException e) {
                String errorInfo = e.getMessage();
                GlassPanePopup.showPopup(new ErrorDetail(errorInfo), "errordetail");
            }
        });

        // Start the thread
        thread.start();
    }

    private void addPostUI(PostUIReport postUIReport) {
        removeLoading();
        feedPanel.add(postUIReport);
        addLoading();
        feedPanel.revalidate();
        feedPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        feedPanel = new online.syncio.component.MyPanel();
        lblLoading = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
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
