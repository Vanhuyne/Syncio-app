package online.syncio.view;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import online.syncio.component.ConnectionPanel;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;

public class Home extends ConnectionPanel {

    //    private LoadingMore loading = new LoadingMore();
    private Main main;
    private MongoDatabase database = MongoDBConnect.getDatabase();
    private User currentUser;
    private String currentUserID;
    private UserDAO userDAO;
    private PostDAO postDAO;
    private List<String> lPostID = new ArrayList<>();
    private List<String> lFollowerID = new ArrayList<>();
    private int curIndex = 0;
    FindIterable<Post> posts;

    public Home() {
        this.userDAO = new UserDAOImpl(database);
        this.postDAO = new PostDAOImpl(database);
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        if (LoggedInUser.isUser()) {
            currentUser = LoggedInUser.getCurrentUser();
            currentUserID = currentUser.getIdAsString();

            posts = postDAO.getAllPostOfFollowers(currentUser);

            // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            loadMorePosts();
        } else {
            System.out.println("chưa đăng nhập");
        }

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
            for (Post post : posts) {
                PostUI postUI = new PostUI(post.getId().toString(), currentUserID);
                SwingUtilities.invokeLater(() -> {
                    removeLoading();
                    feedPanel.add(postUI);
                    addLoading();
                });
                feedPanel.revalidate();
                feedPanel.repaint();
            }
            
            removeLoading();
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
