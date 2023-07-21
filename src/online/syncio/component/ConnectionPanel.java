package online.syncio.component;

import com.mongodb.client.MongoDatabase;
import javax.swing.JPanel;
import online.syncio.view.Main;

public class ConnectionPanel extends JPanel {

    public Main main;
    public MongoDatabase database;

    public void setConnection(Main main) {
        this.main = main;
        this.database = main.getDatabase();
    }
}
