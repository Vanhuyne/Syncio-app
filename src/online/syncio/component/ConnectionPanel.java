package online.syncio.component;

import com.mongodb.client.MongoDatabase;
import javax.swing.JPanel;
import online.syncio.view.Main;
import online.syncio.view.MainAdmin;

public class ConnectionPanel extends JPanel {

    public Main main;
    public MainAdmin mainAdmin;
    public MongoDatabase database;

    public void setConnection(Main main) {
        this.main = main;
    }
    
    public void setConnection(MainAdmin mainAdmin) {
        this.mainAdmin = mainAdmin;
    }
}
