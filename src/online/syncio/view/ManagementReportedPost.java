package online.syncio.view;

import com.mongodb.client.FindIterable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;

public class ManagementReportedPost extends JPanel {

    private User currentUser;
    private String currentUserID;
    private PostDAO postDAO = MongoDBConnect.getPostDAO();
    FindIterable<Post> postsReport;

    public ManagementReportedPost() {
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
            // Load initial posts from the regular database query
            for (Post post : postsReport) {

                try {
                    Thread.sleep(40); // Wait for 100 milliseconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                PostUI postUI = new PostUI(post.getId().toString(), currentUserID);
                SwingUtilities.invokeLater(() -> {
                    removeLoading();
                    feedPanel.add(postUI);
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
                        e.printStackTrace();
                    }

                    postsLoaded = 0; // Reset the counter
                }
            }
        });

        // Start the thread
        thread.start();
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
