package online.syncio.view;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.List;
import online.syncio.dao.MongoDBConnect;
import online.syncio.model.Post;
import online.syncio.model.UserIDAndDate;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class NewClass {
    private static MongoDatabase database = MongoDBConnect.getDatabase();
    
//    public static void main(String[] args) {
//        MongoCollection<NewClassModel> posts = database.getCollection("posts", NewClassModel.class);
//        
//        ArrayList<UserIDDate> lUserIDAndDate = new ArrayList<>();
//        lUserIDAndDate.add(new UserIDDate("id1", "2023"));
//        
//        ArrayList<String> lString = new ArrayList<>();
//        lString.add("string 1");
//        lString.add("string 2");
//        
//        NewClassModel m = new NewClassModel("duong", lUserIDAndDate, lString);
//        
//        InsertOneResult result = posts.insertOne(m);
//            System.out.println("Inserted a Post with the following id: " + result.getInsertedId().asObjectId().getValue());
//    }
//    
    
    
    public static void main(String[] args) {
        MongoCollection<Post> grades = database.getCollection("posts", Post.class);
            
            // create a new grade.
//            List<UserIDAndDate> lLike = new ArrayList<>();
//            lLike.add(new UserIDAndDate("d", "2023-07-24 10:53:22"));
//            Post newGrade = new Post("trbrtrbr", lLike);
//            grades.insertOne(newGrade);
//            System.out.println("Grade inserted.");

            // find this grade.
            Post post = grades.find(eq("idAsString", "64bdf5b423a2c0487f96d4c1")).first();
            System.out.println("Grade found:\t" + post);
            System.out.println("Size: " + post.getLLike().size());
            
            // delete this grade
//            Document filterByGradeId = new Document("userID", "scscscscscsqwdqw");
//            System.out.println("Grade deleted:\t" + grades.deleteOne(filterByGradeId));
            
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://SyncioApp:HCzZXCzshY5fv1au@cluster0.r5fkiwd.mongodb.net/");
//        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
//        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
//        MongoClientSettings clientSettings = MongoClientSettings.builder()
//                                                                .applyConnectionString(connectionString)
//                                                                .codecRegistry(codecRegistry)
//                                                                .build();
//        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
//
//            MongoDatabase db = mongoClient.getDatabase("Syncio");
//            
//
//
//            // update this grade: adding an exam grade
//            List<UserIDDate> newScores = new ArrayList<>(grade.getScores());
//            newScores.add(new UserIDDate().setType("exam").setScore(42d));
//            grade.setlUserIDAndDate(newScores);
//            Document filterByGradeId = new Document("_id", grade.getId());
//            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
//            Grade updatedGrade = grades.findOneAndReplace(filterByGradeId, grade, returnDocAfterReplace);
//            System.out.println("Grade replaced:\t" + updatedGrade);
//
//            // delete this grade
//            System.out.println("Grade deleted:\t" + grades.deleteOne(filterByGradeId));
//        }
    }
}
