package online.syncio.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import online.syncio.view.login.Signup;

public class GoogleOAuthHelper {
    
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String CREDENTIALS_FILE_PATH, JsonFactory JSON_FACTORY, List<String> SCOPES) {
        try {
            // Load client secrets.
            InputStream in = Signup.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
            if (in == null) {
                System.out.print("Resource not found: " + CREDENTIALS_FILE_PATH);
            }
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            
//            // save to TOKENS_DIRECTORY_PATH
//            // Build flow and trigger user authorization request.
//            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();

            //select Google account every time they sign up / sign in
            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setAccessType("offline") // Set access type to "offline"
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
            //returns an authorized Credential object.
            return credential;
        } catch (IOException ex) {
            Logger.getLogger(GoogleOAuthHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
