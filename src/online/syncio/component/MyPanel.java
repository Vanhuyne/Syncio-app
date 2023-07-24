package online.syncio.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JPanel;
import online.syncio.utils.ImageHelper;

public class MyPanel extends JPanel {
    private int radius = 15;
    private Color borderColor = Color.BLACK;
    private int borderThickness = 0;
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }
    
    public void setBorderThicknessColor(int borderThickness, Color borderColor) {
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
    }
    
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    
    
    
    public MyPanel() {
        setOpaque(false);
    }
    
    
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
        
        // Draw border
        if (borderThickness > 0) {
            Graphics2D g2Border = (Graphics2D) grphcs.create();
            g2Border.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2Border.setColor(borderColor);
            g2Border.setStroke(new BasicStroke(borderThickness));
            g2Border.draw(area);
            g2Border.dispose();
        }
        
        if(getImg() != null && getImg().getWidth(null) != -1 && getImg().getHeight(null) != -1) {
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(rh);
            
            //center
            int centerWidth = 0;
            int centerHeight = 0;
            int imageWidth = ImageHelper.imageToBufferedImage(getImg()).getWidth();
            int imageHeight = ImageHelper.imageToBufferedImage(getImg()).getHeight();
            
            centerWidth = (imageWidth - getWidth()) / 2;
            centerHeight = (imageHeight - getHeight()) / 2;
//            int centerWidth = (getWidth() - ImageHelper.imageToBufferedImage(getImg()).getWidth()) / 2;
//            int centerHeight = (getHeight() - ImageHelper.imageToBufferedImage(getImg()).getHeight()) / 2;
            
//            if(centerWidth < 0) centerWidth = 0;
//            if(centerHeight < 0) centerHeight = 0;
            
            grphcs.drawImage(img, centerWidth * -1, centerHeight * -1, null);
        }
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
    
    
    
    private Image img;
    private int imgHeight;

    public Image getImg() {
        return img;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImg(Image img) {
        //realWidth and realHeight are width and height of image after resize (this panel)
        int realWidth = getWidth();
        int realHeight = getHeight();
        
        BufferedImage bImg = ImageHelper.imageToBufferedImage(img); //convert to BufferedImage
                
        if(!img.equals("") && getWidth() != 0 && getHeight() != 0) {
            //original_width and original_height are original size of image
            double originalWidth = bImg.getWidth();
            double originalHeight = bImg.getHeight();

            double raitoWidth = 1.000;
            raitoWidth = originalWidth / (double)getWidth();
            
            realWidth = (int) (originalWidth / raitoWidth);
            realHeight = (int) (originalHeight / raitoWidth);
            this.imgHeight = realHeight;
            this.img = ImageHelper.resizing(img, realWidth, realHeight).getImage();
        }
        else {
            this.img = null;
        }
    }
    
    
    
    public void setImg(String img) {
        //realWidth and realHeight are width and height of image after resize
        int realWidth = getWidth();
        int realHeight = getHeight();
        
        BufferedImage bImg = null;
        try {
            if(!img.equals("") && getWidth() != 0 && getHeight() != 0) {
                bImg = ImageIO.read(new File(img)); //convert to BufferedImage
                
                //original_width and original_height are original size of image
                double originalWidth = bImg.getWidth();
                double originalHeight = bImg.getHeight();

                double raitoWidth = 1.000;
                raitoWidth = originalWidth / (double)getWidth();

                realWidth = (int) (originalWidth / raitoWidth);
                realHeight = (int) (originalHeight / raitoWidth);
                this.imgHeight = realHeight;
                this.img = ImageHelper.resizing(img, realWidth, realHeight).getImage();
            }
            else {
                this.img = null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

