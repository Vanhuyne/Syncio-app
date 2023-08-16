package online.syncio.component.message;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Custom Swing component for displaying circular avatar images with optional border.
 */
public class ImageAvatar extends JComponent {

    /**
     * Get the currently set avatar image.
     * @return The avatar image icon.
     */
    public Icon getImage() {
        return image;
    }

    /**
     * Set the avatar image icon to be displayed.
     * @param image The avatar image icon.
     */
    public void setImage(Icon image) {
        this.image = image;
        repaint();
    }

    /**
     * Get the size of the border around the circular avatar.
     * @return The border size in pixels.
     */
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * Set the size of the border around the circular avatar.
     * @param borderSize The border size in pixels.
     */
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

    /**
     * Get the space between the avatar image and the border.
     * @return The border space in pixels.
     */
    public int getBorderSpace() {
        return borderSpace;
    }

    /**
     * Set the space between the avatar image and the border.
     * @param borderSpace The border space in pixels.
     */
    public void setBorderSpace(int borderSpace) {
        this.borderSpace = borderSpace;
        repaint();
    }

    private Icon image;
    private int borderSize = 6;
    private int borderSpace = 5;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height) - (borderSize * 2 + borderSpace * 2);
            int x = (width - diameter) / 2;
            int y = (height - diameter) / 2;
            Rectangle size = getAutoSize(image, diameter);
            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_img = img.createGraphics();
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2_img.fillOval(0, 0, diameter, diameter);
            Composite composite = g2_img.getComposite();
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(image), size.x, size.y, size.width, size.height, null);
            g2_img.setComposite(composite);
            g2_img.dispose();
            g2.drawImage(img, x, y, null);
        }
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private Rectangle getAutoSize(Icon image, int size) {
        int w = size;
        int h = size;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }
        int cw = size;
        int ch = size;
        int x = (cw - width) / 2;
        int y = (ch - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}
