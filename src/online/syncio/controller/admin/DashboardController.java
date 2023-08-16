package online.syncio.controller.admin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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
import online.syncio.component.MyChart;
import online.syncio.component.SearchedCard;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.PostDAO;
import online.syncio.dao.UserDAO;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.view.admin.Dashboard;

/**
 * Controller class for managing the dashboard.
 */
public class DashboardController {

    private Dashboard dashboard;

    private UserDAO userDAO = MongoDBConnect.getUserDAO();
    private PostDAO postDAO = MongoDBConnect.getPostDAO();

    public DashboardController(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * Processes and displays the new user chart on the dashboard.
     */
    public void processNewUserChart() {
        ArrayList<String> dateList = generateLastSevenDays();
        ArrayList<Integer> valueList = getNewUserCounts(dateList);

        formatAndAddNewUserChart(dateList, valueList);
    }

    /**
     * Generates a list of date strings for the last seven days.
     *
     * @return An ArrayList of date strings.
     */
    private ArrayList<String> generateLastSevenDays() {
        ArrayList<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);

        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateList.add(sdf.format(cal.getTime()));
        }

        return dateList;
    }

    /**
     * Retrieves the count of new users for each date in the given list.
     *
     * @param dateList The list of date strings.
     * @return An ArrayList of new user counts.
     */
    private ArrayList<Integer> getNewUserCounts(ArrayList<String> dateList) {
        ArrayList<Integer> valueList = new ArrayList<>();
        MongoCollection<User> collectionUser = userDAO.getAllByCollection();

        for (String date : dateList) {
            long newUserCount = collectionUser.countDocuments(
                    Filters.and(
                            Filters.gte("dateCreated", date + " 00:00:00"), // Start of the day
                            Filters.lt("dateCreated", date + " 23:59:59") // End of the day
                    )
            );
            valueList.add((int) newUserCount);
        }

        return valueList;
    }

    /**
     * Formats and adds a new user chart to the dashboard.
     *
     * @param dateList The list of date strings.
     * @param valueList The list of new user counts.
     */
    private void formatAndAddNewUserChart(ArrayList<String> dateList, ArrayList<Integer> valueList) {
        for (int i = 0; i < dateList.size(); i++) {
            dateList.set(i, dateList.get(i).substring(5, 10)); //remove year
        }

        MyChart newUserChart = new MyChart(dateList, valueList);
        newUserChart.setChartHEIGHT(469);
        newUserChart.setChartName("New Users Last 7 Days");
        dashboard.getPnlNewUsersChart().add(newUserChart);
        dashboard.getPnlNewUsersChart().revalidate();
        dashboard.getPnlNewUsersChart().repaint();
    }

    /**
     * Displays engagement statistics on the dashboard.
     */
    public void displayEngagementStats() {
        long numUsers = userDAO.getAllByCollection().countDocuments();
        long numPosts = postDAO.getAllByCollection().countDocuments();
        dashboard.getLblUsersTotal().setText(String.valueOf(numUsers));
        dashboard.getLblUsersTotal().setText(String.valueOf(numPosts));

        long likeCount = 0;
        long commentCount = 0;
        Map<String, Integer> engagementCountMap = new HashMap<>();

        MongoCollection<Post> collectionPost = postDAO.getAllByCollection();
        FindIterable<Post> postCollection = collectionPost.find();

        for (Post post : postCollection) {
            likeCount += post.getLikeList().size();
            commentCount += post.getCommentList().size();

            incrementEngagementCount(engagementCountMap, post.getLikeList());
            incrementEngagementCount(engagementCountMap, post.getCommentList());
        }

        double averageEngagementRate = (double) (likeCount + commentCount) / numUsers;
        dashboard.getLblAverageEngagementRate().setText(String.format("%.2f", averageEngagementRate));

        displayTopEngagedUsers(engagementCountMap);
    }

    /**
     * Increments engagement counts for users in the given engagement list.
     *
     * @param engagementCountMap The map containing engagement counts.
     * @param engagementList The list of engagements.
     */
    private void incrementEngagementCount(Map<String, Integer> engagementCountMap, List<? extends UserIDAndDate> engagementList) {
        for (UserIDAndDate engagement : engagementList) {
            String userID = engagement.getUserID();
            engagementCountMap.put(userID, engagementCountMap.getOrDefault(userID, 0) + 1);
        }
    }

    /**
     * Displays the top engaged users on the dashboard.
     *
     * @param engagementCountMap The map containing engagement counts.
     */
    private void displayTopEngagedUsers(Map<String, Integer> engagementCountMap) {
        List<Map.Entry<String, Integer>> sortedEngagementList = new ArrayList<>(engagementCountMap.entrySet());
        sortedEngagementList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        int topUsersCount = Math.min(4, sortedEngagementList.size());
        List<Map.Entry<String, Integer>> topUsers = sortedEngagementList.subList(0, topUsersCount);

        dashboard.getPnlResult().removeAll();
        dashboard.getPnlResult().setLayout(new BoxLayout(dashboard.getPnlResult(), BoxLayout.Y_AXIS));
        Box.createVerticalStrut(30);

        for (Map.Entry<String, Integer> entry : topUsers) {
            String userID = entry.getKey();
            User user = userDAO.getByID(userID);
            dashboard.getPnlResult().add(new SearchedCard(user, new Color(245, 245, 245)));
            Box.createVerticalStrut(20);
        }

        dashboard.getPnlResult().revalidate();
        dashboard.getPnlResult().repaint();
    }
}
