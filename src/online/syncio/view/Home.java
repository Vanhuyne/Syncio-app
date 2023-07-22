package online.syncio.view;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.GlassPanePopup;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.PostDAOImpl;
import online.syncio.dao.UserDAO;
import online.syncio.dao.UserDAOImpl;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import org.bson.Document;

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

    public Home() {
        userDAO = new UserDAOImpl(database);
        postDAO = new PostDAOImpl(database);
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        if (LoggedInUser.isUser()) {
            currentUser = LoggedInUser.getCurrentUser();
            currentUserID = currentUser.getIdAsString();

            for(UserIDAndDate u : currentUser.getFollowers()) {
                getPostOfFollowerID(u.getFollowerID());
//                System.out.println(u.getDateFollowed());
//                System.out.println(u.getFollowerID());
            }
            




//            // lấy ra danh dách các ID đang được follow
//            getListFollowerID(curUserID);
//
//            //  với mỗi follower sẽ lấy ra list postID của follower đó
//            for (String followerID : lFollowerID) {
//                getPostOfFollowerID(followerID);
//            }
//
            // set box layout để các post nằm chồng lên nhau theo trục Y
            feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
            // tỉ lệ khoảng cách dịch chuyển khi lăn chuột
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            // sự kiện lăn chuột (load thêm post)
            scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    // lấy thanh cuộn dọc từ đối tượng AdjustmentEvent
                    JScrollBar scrollBar = (JScrollBar) e.getSource();
                    // lấy kích thước hiển thị
                    int extent = scrollBar.getModel().getExtent();
                    // lấy giá trị tối đa
                    int maximum = scrollBar.getModel().getMaximum();
                    // lấy giá trị hiện tại
                    int value = scrollBar.getValue();
                    // kiểm tra nếu đã cuộn đến cuối thì load thêm post
                    if (value + extent >= maximum) {
                        loadMorePosts();
                    }
                }
            });
            
        } else {
            System.out.println("chưa đăng nhập");
        }

    }

    private void loadMorePosts() {
        //GlassPanePopup.showPopup(new LoadingMore(), "loadmore");
        int startIndex = curIndex; // Lưu chỉ số bắt đầu của lPostID
        int endIndex = curIndex + 4; // Lưu chỉ số kết thúc của lPostID

        for (int i = startIndex; i <= endIndex && i < lPostID.size(); i++) {
            String postID = lPostID.get(i);
            JPanel postPanel = createPostPanel(postID);
            feedPanel.add(postPanel);
        }
        feedPanel.revalidate();
        feedPanel.repaint();

        curIndex += 5; // Tăng chỉ số hiện tại lên 5
        //GlassPanePopup.closePopup("loadmore");
    }

    private JPanel createPostPanel(String postID) {
        PostUI postPanel = new PostUI(postID);
        return postPanel;
    }

    private List getListFollowerID(String curUserID) {
        ArrayList<UserIDAndDate> list = currentUser.getFollowers();

        if (list != null) {
            for (UserIDAndDate l : list) {
                lFollowerID.add(l.getFollowerID());
            }
            return lFollowerID;
        }

        return null;
    }

    private List getPostOfFollowerID(String followerID) {
        List<Post> list = new ArrayList<>();
        list = postDAO.getAll();
        System.out.println("co");
        MongoDatabase database = MongoDBConnect.getDatabase();
        MongoCollection<Document> collection = database.getCollection("posts");
        FindIterable<Document> findIterable = collection.find(eq("userID", followerID));
        for (Document document : findIterable) {
            String postID = document.getObjectId("_id").toString();
            lPostID.add(postID);
        }
        return lPostID;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        feedPanel = new online.syncio.component.MyPanel();

        setBackground(null);
        setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomRight(20);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout feedPanelLayout = new javax.swing.GroupLayout(feedPanel);
        feedPanel.setLayout(feedPanelLayout);
        feedPanelLayout.setHorizontalGroup(
            feedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );
        feedPanelLayout.setVerticalGroup(
            feedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(feedPanel);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyPanel feedPanel;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
