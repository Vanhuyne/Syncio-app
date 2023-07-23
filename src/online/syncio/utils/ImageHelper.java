package online.syncio.utils;
    
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.bson.types.Binary;

/**
 * The ImageHelper class provides utility methods for handling and manipulating images.
 */
public class ImageHelper {
    
    /**
     * Converts an Icon to a BufferedImage.
     * 
     * @param icon the Icon to be converted
     * @return the converted BufferedImage
     */
    public static BufferedImage iconToBufferedImage(Icon icon) {
        BufferedImage bi = new BufferedImage(
            icon.getIconWidth(),
            icon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.setColor(Color.WHITE);
        g.dispose();
        
        return bi;
    }
    
    
    
    /**
     * Converts an Image to a BufferedImage.
     * 
     * @param image the Image to be converted
     * @return the converted BufferedImage
     */
    public static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bimage = new BufferedImage(
                image.getWidth(null), 
                image.getHeight(null), 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        
        return bimage;
    }
    
    public static BufferedImage stringToBufferedImage(String imagePath) {
        File file = new File(imagePath);
        BufferedImage bimage = null;
        
        try {
            bimage = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return bimage;
    }
    
    
    
    /**
     * Resizes an image from a file path to the specified dimensions.
     * 
     * @param imagePath the path of the image file
     * @param width the desired width of the resized image
     * @param height the desired height of the resized image
     * @return the resized ImageIcon
     */
    public static ImageIcon resizing(String imagePath, int width, int height) {
        Image image = new ImageIcon(imagePath).getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }
    
   
    
    /**
     * Resizes an image to the specified dimensions.
     * 
     * @param img the Image to be resized
     * @param width the desired width of the resized image
     * @param height the desired height of the resized image
     * @return the resized ImageIcon
     */
    public static ImageIcon resizing(Image img, int width, int height) {
        Image image = new ImageIcon(img).getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }
    
    
    
    public static byte[] readAsByte(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            int n = fis.available(); //tra ve so byte uoc tinh co the doc (hoac bo qua)
            byte[] data = new byte[n];
            
            fis.read(data);
            fis.close();
            
            return data;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    
    public static byte[] readAsByte(Image image) {
        byte[] imageBytes = null;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = imageToBufferedImage(image);
        
        try {
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return imageBytes;
    }
    
    
    
    public static BufferedImage readBinaryAsBufferedImage(Binary binary) {
        byte[] byteArray = binary.getData();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
        try {
            return ImageIO.read(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
} 
