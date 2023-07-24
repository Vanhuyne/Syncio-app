package online.syncio.component;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MyRoundLabel extends JLabel {

    private Image defaultImage = new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile_24px.png")).getImage();

    public MyRoundLabel() {
        setPreferredSize(new Dimension(128, 128));
        setOpaque(false);
        setImage(defaultImage);
    }

    public void setImage(Image image) {
        Image roundImage = createRoundImage(image, 128);
        setIcon(new ImageIcon(roundImage));
    }

    private Image createRoundImage(Image image, int size) {
        BufferedImage roundImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a round shape
        Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, size, size);
        g2d.clip(ellipse);

        // Draw the image within the round shape
        g2d.drawImage(image, 0, 0, size, size, null);
        g2d.dispose();

        return roundImage;
    }
}
