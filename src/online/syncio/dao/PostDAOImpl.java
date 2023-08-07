package online.syncio.dao;

import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.nin;
import static com.mongodb.client.model.Filters.not;
import static com.mongodb.client.model.Filters.size;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import online.syncio.model.Post;
import online.syncio.model.User;
import online.syncio.model.UserIDAndDate;
import online.syncio.model.UserIDAndDateAndText;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class PostDAOImpl implements PostDAO {

    private MongoDatabase database;
    private MongoCollection<Post> postCollection;

    public PostDAOImpl(MongoDatabase database) {
        this.database = database;
        postCollection = database.getCollection("posts", Post.class);
    }

    @Override
    public boolean add(Post post) {
        try {
            InsertOneResult result = postCollection.insertOne(post);
            System.out.println("Inserted a Post with the following id: " + result.getInsertedId().asObjectId().getValue());

            return true;
        } catch (Exception ex) {
            System.out.println("Failed to insert into MongoDB: " + ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateByID(Post t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(String entityID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Post getByID(String postID) {
        return postCollection.find(eq("_id", new ObjectId(postID))).first();
    }

    @Override
    public List<Post> getAll() {
        List<Post> lPost = new ArrayList<>();

        try {
            postCollection.find().sort(Sorts.descending("datePosted")).into(lPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lPost;
    }

    @Override
    public MongoCollection<Post> getAllByCollection() {
        return postCollection;
    }

    @Override
    public List<Post> getAllByUserID(String userID) {
        Bson filter = eq("userID", userID);
        FindIterable<Post> posts = postCollection.find(filter).sort(Sorts.descending("datePosted"));
        return posts.into(new ArrayList<>()); // Convert FindIterable directly to List
    }

    @Override
    public boolean addLike(String postID, String userID) {
        Bson likeFilter = Filters.eq("_id", new ObjectId(postID)); //get document
        Bson add = Updates.push("likeList", new UserIDAndDate(userID));
        postCollection.updateOne(likeFilter, add);
        return true;
    }

    @Override
    public boolean removeLike(String postID, String userID) {
        Bson likeFilter = Filters.eq("_id", new ObjectId(postID)); //get document
        Bson delete = Updates.pull("likeList", new Document("userID", userID));
        postCollection.updateOne(likeFilter, delete);
        return true;
    }

    @Override
    public FindIterable<Post> getAllPostOfFollowers(User user) {
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);

        // Get the list of follower IDs from the user object
        List<String> followerIds = new ArrayList<>();
        for (UserIDAndDate userIDAndDate : user.getFollowing()) {
            followerIds.add(userIDAndDate.getUserID());
        }

        // add itself
        followerIds.add(user.getId().toString());

        // Create a filter to find posts where the userID matches any of the follower IDs
        Bson filter = in("userID", followerIds);

        // Execute the query and return the result
        return posts.find(filter).sort(Sorts.descending("datePosted"));
    }

    @Override
    public boolean addComment(String text, String userID, String postID) {
        Bson cmtFilter = Filters.eq("_id", new ObjectId(postID)); //get document
        Bson add = Updates.push("commentList", new UserIDAndDateAndText(userID, text));
        postCollection.updateOne(cmtFilter, add);
        return true;
    }

    @Override
    public ChangeStreamIterable<Post> getChangeStream() {
        ChangeStreamIterable<Post> changeStreamPosts = postCollection.watch();
        changeStreamPosts.fullDocument(FullDocument.UPDATE_LOOKUP);
        return changeStreamPosts;
    }

    @Override
    public FindIterable<Post> getAllPostOther(User user) {
        MongoCollection<Post> posts = database.getCollection("posts", Post.class);

        // Get the list of follower IDs from the user object
        List<String> followerIds = new ArrayList<>();
        for (UserIDAndDate userIDAndDate : user.getFollowing()) {
            followerIds.add(userIDAndDate.getUserID());
        }

        // add itself
        followerIds.add(user.getId().toString());

        // Create a filter to find posts where the userID matches any of the follower IDs
        Bson filter = nin("userID", followerIds);

        // Execute the query and return the result
        return posts.find(filter).sort(Sorts.descending("datePosted"));
    }

    @Override
    public FindIterable<Post> getAllByUserIDFindIterable(String userID) {
        Bson filter = eq("userID", userID);
        return postCollection.find(filter).sort(Sorts.descending("datePosted"));
    }

    @Override
    public boolean addReport(String text, String userID, String postID) {
        Bson cmtFilter = Filters.eq("_id", new ObjectId(postID)); //get document
        Bson add = Updates.push("reportList", new UserIDAndDateAndText(userID, text));
        postCollection.updateOne(cmtFilter, add);
        return true;
    }

    @Override
    public List<UserIDAndDateAndText> getReportList(String postID) {
        // Tạo truy vấn dựa vào postID
        Document query = new Document("_id", new ObjectId(postID));

        Post post = postCollection.find(query).first();

        // Kiểm tra xem bài viết có tồn tại và có reportList không
        if (post != null && post.getReportList() != null) {
            return post.getReportList();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isUserIDInListReport(String userID, String postID) {

        ObjectId postIdObject = new ObjectId(postID);
        // Create the filter to find the document with the given postID and userID in the reportList
        Document query = new Document("_id", postIdObject)
                .append("reportList.userID", userID);

        // Use the countDocuments method to check if any document matches the query
        long count = postCollection.countDocuments(query);

        // If the count is greater than 0, it means the userID exists in the reportList for the given postID
        return count > 0;

    }

    @Override
    public FindIterable<Post> getAllReportedPost() {
        // Create a filter to find documents where "reportList" exists and is not an empty array
        Bson filter = and(exists("reportList"), not(size("reportList", 0)));
        // Execute the query and return the result
        return postCollection.find(filter);
    }

}
