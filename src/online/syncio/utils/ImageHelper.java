package online.syncio.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.bson.types.Binary;

/**
 * The ImageHelper class provides utility methods for handling and manipulating
 * images.
 */
public class ImageHelper {

    private static final Image defaultImage = new javax.swing.ImageIcon(ImageHelper.class.getResource("/online/syncio/resources/images/icons/avt_128px.png")).getImage();

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
        icon.paintIcon(null, g, 0, 0);
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

    public static Binary bufferedImageToBinary(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = baos.toByteArray();
        return new Binary(byteArray);
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

    public static ImageIcon resizing(BufferedImage bufferedImage, int width, int height) {
        Image image = new ImageIcon(bufferedImage).getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);
    }

    public static Image resizeImageToWidth(BufferedImage bufferedImage, int width) {
        return bufferedImage.getScaledInstance(width, -1, Image.SCALE_DEFAULT);
    }
    
    public static Image resizeImageToHeight(BufferedImage bufferedImage, int height) {
        return bufferedImage.getScaledInstance(-1, height, Image.SCALE_DEFAULT);
    }

    public static Binary resizingAndCompressingWidthToBinary(BufferedImage bufferedImage, int width, float compressionQuality) {
        try {
            //resize image
            bufferedImage = imageToBufferedImage(bufferedImage.getScaledInstance(width, -1, Image.SCALE_DEFAULT));

            BufferedImage compressedImage = new BufferedImage(
                    bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            Graphics2D g2d = compressedImage.createGraphics();
            g2d.drawImage(bufferedImage, 0, 0, null);
            g2d.dispose();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(byteArrayOutputStream);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(compressionQuality);
            writer.write(null, new IIOImage(compressedImage, null, null), param);

            ios.close();
            writer.dispose();

            return ImageHelper.bufferedImageToBinary(compressedImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static byte[] readAsByte(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            int n = fis.available(); //tra ve so byte uoc tinh co the doc (hoac bo qua)
            byte[] data = new byte[n];

            fis.read(data);
            fis.close();

            return data;
        } catch (IOException ex) {
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
        }

        return imageBytes;
    }

    public static void write(String path, byte[] data) {
        try {
            FileOutputStream fos = new FileOutputStream(path);

            fos.write(data);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static ImageIcon toRoundImage(ImageIcon image, int size) {
        Image newImg = image.getImage();

        BufferedImage roundImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a round shape
        Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, size, size);
        g2d.clip(ellipse);

        // Draw the image within the round shape
        g2d.drawImage(newImg, 0, 0, size, size, null);
        g2d.dispose();

        return new ImageIcon(roundImage);
    }

    public static ImageIcon toRoundImage(BufferedImage image, int size) {
        if(image.getWidth() < image.getHeight()) {
            image = imageToBufferedImage(resizeImageToWidth(image, size));
        }
        else {
            image = imageToBufferedImage(resizeImageToHeight(image, size));
        }
        
        BufferedImage mask = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = mask.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.fillOval(0, 0, size - 1, size - 1);
        g2d.dispose();
    
        BufferedImage masked = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        g2d = masked.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.drawImage(image, 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g2d.drawImage(mask, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(masked);
    }

    public static Image getDefaultImage() {
        return defaultImage;
    }

}
