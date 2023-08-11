package online.syncio.utils;

import java.io.IOException;
import java.net.URI;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebsiteHelper {
    
    public static boolean isUrlExists(String url) {
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.HEAD).execute();
            int statusCode = response.statusCode();
            return statusCode == 200;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    
    public static String getTextFromWeb(String url, String cssSelector) {
        String text = null;
        int statusCode = -1;
        
        try {
            if(!isUrlExists(url)) {
                System.out.println("Not Found URL to get text");
                return null;
            }
            
            URI uri = new URI(url);
            Connection.Response response = Jsoup.connect(uri.toASCIIString()).execute();
            statusCode = response.statusCode(); // get status code
            
            if (statusCode == 200) { // check if status code is OK (200)
                Document doc = response.parse();
                Element element = doc.selectFirst(cssSelector);

                if (element != null) {
                    text = element.text();
                    return text;
                } else {
                    System.out.println("Element not found");
                }
            }
        } catch (HttpStatusException ex) {
            //404
            System.out.println("HTTP error fetching URL. Status=" + statusCode);
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
