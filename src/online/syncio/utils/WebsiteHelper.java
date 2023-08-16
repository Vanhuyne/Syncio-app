package online.syncio.utils;

import java.io.IOException;
import java.net.URI;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebsiteHelper {
    
    /**
     * Checks if a URL exists by sending a HEAD request and checking the status code.
     *
     * @param url The URL to check for existence.
     * @return true if the URL exists and returns a 200 status code, false otherwise.
     */
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
    
    
    /**
     * Retrieves the text content from a web page based on the given URL and CSS selector.
     *
     * @param url         The URL of the web page to retrieve text from.
     * @param cssSelector The CSS selector to locate the desired element.
     * @return The text content of the selected element, or null if not found or an error occurred.
     */
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
