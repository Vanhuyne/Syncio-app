package online.syncio.view;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import online.syncio.component.ConnectionPanel;
import online.syncio.component.MyChart;
import online.syncio.component.SearchedUserCard;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.model.UserIDAndDateAndText;
import online.syncio.utils.Export;
import org.bson.BsonDocument;

public class ManagementDashboard extends ConnectionPanel {
    
    private UserDAO userDAO;
    private PostDAO postDAO;

    public ManagementDashboard() {
        MongoDBConnect.connect();
        this.userDAO = MongoDBConnect.getUserDAO();
        this.postDAO = MongoDBConnect.getPostDAO();
        
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        // get starting date
        cal.add(Calendar.DAY_OF_YEAR, -7);

        // loop adding one day in each iteration
        for(int i = 0; i < 7; i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateList.add(sdf.format(cal.getTime()));
        }
        
        
        MongoCollection<User> collectionUser = userDAO.getAllByCollection();
        FindIterable<User> userCollection = collectionUser.find(
            Filters.and(
                Filters.gte("dateCreated", dateList.get(0) + " 00:00:00"), // Start of the day
                Filters.lt("dateCreated", dateList.get(dateList.size() - 1) + " 23:59:59") // End of the day
            )
        );

        // Iterate through the dateList and populate the valueList with new user counts
        for (String date : dateList) {
            int newUserCount = 0;
            for (User user : userCollection) {
                String registrationDate = user.getDateCreated().substring(0, 10);
                if (registrationDate.equals(date)) newUserCount++;
            }
            valueList.add(newUserCount);
        }
        
        
        //chart
        for(int i = 0; i < dateList.size(); i++) dateList.set(i, dateList.get(i).substring(5, 10)); //remove year
        MyChart newUserChart = new MyChart(dateList, valueList);
        newUserChart.setChartHEIGHT(469);
        newUserChart.setChartName("New Users Last 7 Days");
        pnlNewUsersChart.add(newUserChart);
        pnlNewUsersChart.revalidate();
        pnlNewUsersChart.repaint();
        
        CountOptions opts = new CountOptions().hintString("_id_");
        long numUsers = collectionUser.countDocuments(new BsonDocument(), opts);
        long numPosts = postDAO.getAllByCollection().countDocuments(new BsonDocument(), opts);
        lblUsersTotal.setText(numUsers + "");
        lblPostsTotal.setText(numPosts + "");
        
        
        long likeCount = 0;
        long commentCount = 0;
        Map<String, Integer> engagementCountMap = new HashMap<>();
        
        MongoCollection<Post> collectionPost = postDAO.getAllByCollection();
        FindIterable<Post> postCollection = collectionPost.find();
        
        for(Post post : postCollection) {
            likeCount += post.getLikeList().size();
            commentCount += post.getCommentList().size();
            
            // Loop through the likeList and increment the engagement count for each user
            List<UserIDAndDate> likeList = post.getLikeList();
            for (UserIDAndDate like : likeList) {
                String userID = like.getUserID();
                engagementCountMap.put(userID, engagementCountMap.getOrDefault(userID, 0) + 1);
            }

            // Loop through the commentList and increment the engagement count for each user
            List<UserIDAndDateAndText> commentList = post.getCommentList();
            for (UserIDAndDateAndText comment : commentList) {
                String userID = comment.getUserID();
                engagementCountMap.put(userID, engagementCountMap.getOrDefault(userID, 0) + 1);
            }
        }
        
        lblAverageEngagementRate.setText(String.format("%.2f", (double)(likeCount + commentCount) / numUsers));
        
        // Sort the users based on their engagement counts in descending order
        List<Map.Entry<String, Integer>> sortedEngagementList = new ArrayList<>(engagementCountMap.entrySet());
        sortedEngagementList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Get the top 5 users with the most engagement
        int topUsersCount = Math.min(4, sortedEngagementList.size());
        List<Map.Entry<String, Integer>> topUsers = sortedEngagementList.subList(0, topUsersCount);

        // Display the top users with the most engagement
        pnlResult.removeAll();
        pnlResult.setLayout(new BoxLayout(pnlResult, BoxLayout.Y_AXIS));
        Box.createVerticalStrut(30);
        for (int i = 0; i < topUsers.size(); i++) {
            Map.Entry<String, Integer> entry = topUsers.get(i);
            String userID = entry.getKey();
            int engagementCount = entry.getValue();
            
            pnlResult.add(new SearchedUserCard(userDAO.getByID(userID), new Color(245, 245, 245)));
            Box.createVerticalStrut(20);

            pnlResult.revalidate();
            pnlResult.repaint();
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        pnlTitle = new online.syncio.component.MyPanel();
        lblTitle = new online.syncio.component.MyLabel();
        btnPrint = new online.syncio.component.MyButton();
        pnlMain = new online.syncio.component.MyPanel();
        pnlPosts = new online.syncio.component.MyPanel();
        lblPosts = new online.syncio.component.MyLabel();
        lblPostsTotal = new online.syncio.component.MyLabel();
        lblPostsIcon = new online.syncio.component.MyLabel();
        lblPostsQuestion = new online.syncio.component.MyLabel();
        pnlUsers = new online.syncio.component.MyPanel();
        lblUsers = new online.syncio.component.MyLabel();
        lblUsersTotal = new online.syncio.component.MyLabel();
        lblUsersIcon = new online.syncio.component.MyLabel();
        lblUsersQuestion = new online.syncio.component.MyLabel();
        pnlEngagementRate = new online.syncio.component.MyPanel();
        lblEngagementRate = new online.syncio.component.MyLabel();
        lblAverageEngagementRate = new online.syncio.component.MyLabel();
        lblEngagementRateIcon = new online.syncio.component.MyLabel();
        lblEngagementRateQuestion = new online.syncio.component.MyLabel();
        pnlNewUsersChart = new online.syncio.component.MyPanel();
        myPanel1 = new online.syncio.component.MyPanel();
        lblEngagementRate1 = new online.syncio.component.MyLabel();
        pnlResult = new online.syncio.component.MyPanel();
        lblEngagementRateQuestion1 = new online.syncio.component.MyLabel();

        setLayout(new java.awt.BorderLayout());

        pnlContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTitle.setPreferredSize(new java.awt.Dimension(0, 40));

        lblTitle.setText("Dashboard");

        btnPrint.setBackground(new java.awt.Color(254, 255, 255));
        btnPrint.setText("Export to PNG");
        btnPrint.setFont(new java.awt.Font("SF Pro Display Medium", 0, 13)); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 870, Short.MAX_VALUE)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContainer.add(pnlTitle, java.awt.BorderLayout.PAGE_START);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);

        pnlPosts.setBackground(new java.awt.Color(245, 245, 245));
        pnlPosts.setRoundBottomLeft(10);
        pnlPosts.setRoundBottomRight(10);
        pnlPosts.setRoundTopLeft(10);
        pnlPosts.setRoundTopRight(10);

        lblPosts.setText("POSTS");

        lblPostsTotal.setText("0000");
        lblPostsTotal.setFont(new java.awt.Font("SF Pro Display Medium", 0, 22)); // NOI18N

        lblPostsIcon.setBackground(new java.awt.Color(239, 239, 239));
        lblPostsIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPostsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/posts_32px.png"))); // NOI18N
        lblPostsIcon.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N
        lblPostsIcon.setRadius(50);

        lblPostsQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblPostsQuestion.setToolTipText("Total number of posts");

        javax.swing.GroupLayout pnlPostsLayout = new javax.swing.GroupLayout(pnlPosts);
        pnlPosts.setLayout(pnlPostsLayout);
        pnlPostsLayout.setHorizontalGroup(
            pnlPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPostsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblPostsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPostsLayout.createSequentialGroup()
                        .addComponent(lblPosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(lblPostsQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPostsTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnlPostsLayout.setVerticalGroup(
            pnlPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPostsLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPostsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlPostsLayout.createSequentialGroup()
                        .addComponent(lblPosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblPostsTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(pnlPostsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblPostsQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlUsers.setBackground(new java.awt.Color(245, 245, 245));
        pnlUsers.setRoundBottomLeft(10);
        pnlUsers.setRoundBottomRight(10);
        pnlUsers.setRoundTopLeft(10);
        pnlUsers.setRoundTopRight(10);

        lblUsers.setText("USERS");

        lblUsersTotal.setText("0000");
        lblUsersTotal.setFont(new java.awt.Font("SF Pro Display Medium", 0, 22)); // NOI18N

        lblUsersIcon.setBackground(new java.awt.Color(239, 239, 239));
        lblUsersIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsersIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/users_32px.png"))); // NOI18N
        lblUsersIcon.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N
        lblUsersIcon.setRadius(50);

        lblUsersQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblUsersQuestion.setToolTipText("Total number of registered users");

        javax.swing.GroupLayout pnlUsersLayout = new javax.swing.GroupLayout(pnlUsers);
        pnlUsers.setLayout(pnlUsersLayout);
        pnlUsersLayout.setHorizontalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUsersIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsersLayout.createSequentialGroup()
                        .addComponent(lblUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(lblUsersQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUsersTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnlUsersLayout.setVerticalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsersLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsersIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlUsersLayout.createSequentialGroup()
                        .addComponent(lblUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblUsersTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblUsersQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEngagementRate.setBackground(new java.awt.Color(245, 245, 245));
        pnlEngagementRate.setPreferredSize(new java.awt.Dimension(280, 90));
        pnlEngagementRate.setRoundBottomLeft(10);
        pnlEngagementRate.setRoundBottomRight(10);
        pnlEngagementRate.setRoundTopLeft(10);
        pnlEngagementRate.setRoundTopRight(10);

        lblEngagementRate.setText("ENGAGEMENT RATE");

        lblAverageEngagementRate.setText("0000");
        lblAverageEngagementRate.setFont(new java.awt.Font("SF Pro Display Medium", 0, 22)); // NOI18N

        lblEngagementRateIcon.setBackground(new java.awt.Color(239, 239, 239));
        lblEngagementRateIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEngagementRateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/engagementrate_32px.png"))); // NOI18N
        lblEngagementRateIcon.setFont(new java.awt.Font("SF Pro Display Medium", 0, 18)); // NOI18N
        lblEngagementRateIcon.setRadius(50);

        lblEngagementRateQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblEngagementRateQuestion.setToolTipText("The average engagement rate per user based on likes and comments ((Total Likes + Total Comments) / Total Users)");

        javax.swing.GroupLayout pnlEngagementRateLayout = new javax.swing.GroupLayout(pnlEngagementRate);
        pnlEngagementRate.setLayout(pnlEngagementRateLayout);
        pnlEngagementRateLayout.setHorizontalGroup(
            pnlEngagementRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEngagementRateLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblEngagementRateIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlEngagementRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEngagementRateLayout.createSequentialGroup()
                        .addComponent(lblEngagementRate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lblEngagementRateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAverageEngagementRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        pnlEngagementRateLayout.setVerticalGroup(
            pnlEngagementRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEngagementRateLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlEngagementRateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEngagementRateIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEngagementRateLayout.createSequentialGroup()
                        .addComponent(lblEngagementRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblAverageEngagementRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(pnlEngagementRateLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblEngagementRateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlNewUsersChart.setBackground(new java.awt.Color(255, 255, 255));
        pnlNewUsersChart.setLayout(new java.awt.BorderLayout());

        myPanel1.setBackground(new java.awt.Color(245, 245, 245));
        myPanel1.setRoundBottomLeft(15);
        myPanel1.setRoundBottomRight(15);
        myPanel1.setRoundTopLeft(15);
        myPanel1.setRoundTopRight(15);

        lblEngagementRate1.setText("Top User Interactions");

        pnlResult.setBackground(new java.awt.Color(245, 245, 245));
        pnlResult.setPreferredSize(new java.awt.Dimension(240, 399));

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        lblEngagementRateQuestion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/question_16px.png"))); // NOI18N
        lblEngagementRateQuestion1.setToolTipText("Top User Interactions (likes + comments)");

        javax.swing.GroupLayout myPanel1Layout = new javax.swing.GroupLayout(myPanel1);
        myPanel1.setLayout(myPanel1Layout);
        myPanel1Layout.setHorizontalGroup(
            myPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblEngagementRate1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEngagementRateQuestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addComponent(pnlResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        myPanel1Layout.setVerticalGroup(
            myPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPanel1Layout.createSequentialGroup()
                .addGroup(myPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblEngagementRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblEngagementRateQuestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlNewUsersChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlPosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlEngagementRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEngagementRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNewUsersChart, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(myPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Export.panelToImage(pnlMain);
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnPrint;
    private online.syncio.component.MyLabel lblAverageEngagementRate;
    private online.syncio.component.MyLabel lblEngagementRate;
    private online.syncio.component.MyLabel lblEngagementRate1;
    private online.syncio.component.MyLabel lblEngagementRateIcon;
    private online.syncio.component.MyLabel lblEngagementRateQuestion;
    private online.syncio.component.MyLabel lblEngagementRateQuestion1;
    private online.syncio.component.MyLabel lblPosts;
    private online.syncio.component.MyLabel lblPostsIcon;
    private online.syncio.component.MyLabel lblPostsQuestion;
    private online.syncio.component.MyLabel lblPostsTotal;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyLabel lblUsers;
    private online.syncio.component.MyLabel lblUsersIcon;
    private online.syncio.component.MyLabel lblUsersQuestion;
    private online.syncio.component.MyLabel lblUsersTotal;
    private online.syncio.component.MyPanel myPanel1;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlEngagementRate;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlNewUsersChart;
    private online.syncio.component.MyPanel pnlPosts;
    private online.syncio.component.MyPanel pnlResult;
    private online.syncio.component.MyPanel pnlTitle;
    private online.syncio.component.MyPanel pnlUsers;
    // End of variables declaration//GEN-END:variables
}
