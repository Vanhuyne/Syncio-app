package online.syncio.config;

/**
 * Provides account-related constants for the application.
 */
public class Account {
    /**
     * The MongoDB connection string for connecting to the database.
     */
    public static final String CONNECTION_STRING = "mongodb+srv://YourAppUser:YourSecurePassword@your-cluster.mongodb.net/"; // Update this with your actual MongoDB connection string
    
    /**
     * The name of the database used in the application.
     */
    public static final String DATABASE_NAME = "YourAppDatabase"; // Update this with your actual MongoDB database name

    /**
     * The API key for sending email notifications.
     */
    public static final String API_KEY_SENDEMAIL = "YOUR_EMAIL_API_KEY"; // Update this with your actual email API key
   
    /**
     * The URL of the GitHub repository associated with the application.
     */
    public static final String GITHUB_REPOSITORIE = "https://github.com/yourusername/your-repo/"; // Update this with your app's GitHub repository URL

}
