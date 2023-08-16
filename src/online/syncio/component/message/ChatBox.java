package online.syncio.component.message;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import online.syncio.dao.MongoDBConnect;
import online.syncio.model.LoggedInUser;
import online.syncio.model.Message;
import online.syncio.model.User;
import online.syncio.resources.fonts.MyFont;
import online.syncio.view.user.Main;

public class ChatBox extends JComponent {

    private final Font regularFont = new MyFont().getSFProDisplayRegular();

    private final BoxType boxType;
    private final Message message;

    private ImageAvatar senderAvatar = new ImageAvatar();

    private JTextPane text;
    private JLabel labelDate;

    public ChatBox(BoxType boxType, Message message) {
        this.boxType = boxType;
        this.message = message;
        init();
    }

    public ChatBox(BoxType boxType, ImageIcon senderAvatar, Message message) {
        this.boxType = boxType;
        this.senderAvatar.setImage(senderAvatar);
        this.message = message;
        init();
    }

    private void init() {
        initBox();
    }

    private void initBox() {
        String rightToLeft = boxType == BoxType.RIGHT ? ",rtl" : "";
        setLayout(new MigLayout("inset 5" + rightToLeft, "[40!]5[]", "[top]"));

        senderAvatar.setBorderSize(1);
        senderAvatar.setBorderSpace(1);
        senderAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        senderAvatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (message.getSenderID().equalsIgnoreCase(LoggedInUser.getCurrentUser().getIdAsString())) {
                    Main.getInstance().getBtnProfile().doClick();
                } else {
                    User user = MongoDBConnect.getUserDAO().getByID(message.getSenderID());
                    Main.getInstance().profile.getController().loadProfile(user);
                    Main.getInstance().showTab("profile");
                }
            }
        });

        text = new JTextPane();
        text.setFont(regularFont.deriveFont(0, 18f));

        text.setEditorKit(new AutoWrapText());

        if (rightToLeft.isBlank()) {
            text.setBackground(new Color(153, 153, 153, 255));
            text.setForeground(new Color(0, 0, 0));
            text.setSelectionColor(new Color(255, 255, 255, 255));
        } else {
            text.setBackground(new Color(0, 0, 0, 0));
            text.setForeground(new Color(255, 255, 255));
            text.setSelectionColor(new Color(200, 200, 200, 100));
        }

        text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        text.setOpaque(false);
        text.setEditable(false);

        text.setText(message.getText());
        User messagingUser = MongoDBConnect.getUserDAO().getByID(message.getSenderID());

        labelDate = new JLabel(messagingUser.getUsername() + " | " + message.getDateSent());
        labelDate.setForeground(new Color(127, 127, 127));
        labelDate.setFont(regularFont);

        add(senderAvatar, "height 40,width 40");
        add(text, "gapy 20, wrap");
        add(labelDate, "gapx 20,span 2");
    }

    @Override

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        if (boxType == BoxType.LEFT) {
            Area area = new Area(new RoundRectangle2D.Double(25, 25, width - 25, height - 25 - 16 - 10, 5, 5));
            area.subtract(new Area(new Ellipse2D.Double(5, 5, 45, 45)));
            g2.setPaint(new Color(239, 239, 239));
            g2.fill(area);
        } else {
            Area area = new Area(new RoundRectangle2D.Double(0, 25, width - 25, height - 25 - 16 - 10, 5, 5));
            area.subtract(new Area(new Ellipse2D.Double(width - 50, 5, 45, 45)));
            g2.setColor(new Color(55, 151, 240));
            g2.fill(area);
        }
        g2.dispose();
        super.paintComponent(g);
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public Message getMessage() {
        return message;
    }

    public static enum BoxType {
        LEFT, RIGHT
    }
}
