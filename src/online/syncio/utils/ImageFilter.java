package online.syncio.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import org.bson.types.Binary;

/**
 * Utility class for applying various image filters and transformations to BufferedImage and Binary images.
 */
public class ImageFilter {

    public static void main(String[] args) {
        File file = new File("src/online/syncio/resources/images/icons/s.jpg");
        //File file = new File("License Plate Photos/ca_10.jpeg");
        BufferedImage img = null;

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        if (img != null) {
            display(img);
            img = brighten(img, 20);
//            img = toGrayScale(img);
//            img = toGrayScale2(img);
            //display(img);
//            img = pixelate(img);
//            img = pixelate2(img, 3);
//            img = resize(img, 150);
//            img = blur(img);
//            img = blur(blur(img));
//            img = heavyblur(img);
//            img = detectEdges(img);
            display(img);
        }
    }

    // display image in a JPanel popup
    /**
     * Display the provided image in a JPanel popup.
     *
     * @param img the image to be displayed
     */
    public static void display(BufferedImage img) {
        System.out.println("  Displaying image.");
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        frame.setSize(img.getWidth(), img.getHeight());
        frame.setLocationRelativeTo(null);
        label.setIcon(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // convert image to grayscale
    /**
    * Converts a given BufferedImage to grayscale.
    *
    * @param img The original BufferedImage to be converted.
    * @return A new BufferedImage representing the grayscale version of the input image.
    */
    public static BufferedImage toGrayScale(BufferedImage img) {
        System.out.println("  Converting to GrayScale.");
        BufferedImage grayImage = new BufferedImage(
                  img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return grayImage;
    }

    /**
     * Convert the provided Binary image to grayscale.
     *
     * @param img the Binary image to be converted
     * @return the grayscale Binary image
     */
    public static Binary toGrayScale(Binary img) {
        System.out.println("  Converting to GrayScale.");
        BufferedImage bufferedImage = ImageHelper.readBinaryAsBufferedImage(img);

        BufferedImage grayImage = new BufferedImage(
                  bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
        return ImageHelper.bufferedImageToBinary(grayImage);
    }

    /**
     * Convert the provided BufferedImage to grayscale using averaging method.
     *
     * @param img the BufferedImage to be converted
     * @return the grayscale BufferedImage
     */
    public static BufferedImage toGrayScale2(BufferedImage img) {
        System.out.println("  Converting to GrayScale2.");
        BufferedImage grayImage = new BufferedImage(
                  img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int rgb = 0, r = 0, g = 0, b = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                rgb = (int) (img.getRGB(x, y));
                r = ((rgb >> 16) & 0xFF);
                g = ((rgb >> 8) & 0xFF);
                b = (rgb & 0xFF);
                rgb = (int) ((r + g + b) / 3);
                //rgb = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                rgb = (255 << 24) | (rgb << 16) | (rgb << 8) | rgb;
                grayImage.setRGB(x, y, rgb);
            }
        }
        return grayImage;
    }

    /**
    * Converts the given binary image to grayscale using a simple averaging method.
    * Each pixel's color channels (red, green, blue) are averaged to produce the grayscale value.
    *
    * @param img The binary image to be converted to grayscale.
    * @return A new binary image representing the grayscale version of the input image.
    */
    public static Binary toGrayScale2(Binary img) {
        System.out.println("  Converting to GrayScale2.");
        BufferedImage bufferedImage = ImageHelper.readBinaryAsBufferedImage(img);
        
        BufferedImage grayImage = new BufferedImage(
                bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int rgb = 0, r = 0, g = 0, b = 0;
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                rgb = (int) (bufferedImage.getRGB(x, y));
                r = ((rgb >> 16) & 0xFF);
                g = ((rgb >> 8) & 0xFF);
                b = (rgb & 0xFF);
                rgb = (int) ((r + g + b) / 3);
                //rgb = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                rgb = (255 << 24) | (rgb << 16) | (rgb << 8) | rgb;
                grayImage.setRGB(x, y, rgb);
            }
        }
        return ImageHelper.bufferedImageToBinary(grayImage);
    }
    
    // apply 2x2 pixelation to a grayscale image
    /** Apply 2x2 pixelation to
     * a grayscale image.
     *
     * @param img the grayscale BufferedImage to be pixelated
     * @return the pixelated BufferedImage
     */
    public static BufferedImage pixelate(BufferedImage img) {
        BufferedImage pixImg = new BufferedImage(
                  img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int pix = 0, p = 0;
        for (int y = 0; y < img.getHeight() - 2; y += 2) {
            for (int x = 0; x < img.getWidth() - 2; x += 2) {
                pix = (int) ((img.getRGB(x, y) & 0xFF)
                          + (img.getRGB(x + 1, y) & 0xFF)
                          + (img.getRGB(x, y + 1) & 0xFF)
                          + (img.getRGB(x + 1, y + 1) & 0xFF)) / 4;
                p = (255 << 24) | (pix << 16) | (pix << 8) | pix;
                pixImg.setRGB(x, y, p);
                pixImg.setRGB(x + 1, y, p);
                pixImg.setRGB(x, y + 1, p);
                pixImg.setRGB(x + 1, y + 1, p);
            }
        }
        return pixImg;
    }

    // apply nxn pixelation to a grayscale image
    /**
     * Apply nxn pixelation to a grayscale image.
     *
     * @param img the grayscale BufferedImage to be pixelated
     * @param n the size of the pixelation blocks
     * @return the pixelated BufferedImage
     */
    public static BufferedImage pixelate2(BufferedImage img, int n) {
        BufferedImage pixImg = new BufferedImage(
                  img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int pix = 0, p = 0;
        for (int y = 0; y < img.getHeight() - n; y += n) {
            for (int x = 0; x < img.getWidth() - n; x += n) {
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        pix += (img.getRGB(x + a, y + b) & 0xFF);
                    }
                }
                pix = (int) (pix / n / n);
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        p = (255 << 24) | (pix << 16) | (pix << 8) | pix;
                        pixImg.setRGB(x + a, y + b, p);
                    }
                }
                pix = 0;
            }
        }
        return pixImg;
    }

    // scale a grayscale image
    /**
     * Scale a grayscale image to a new height.
     *
     * @param img the grayscale BufferedImage to be scaled
     * @param newHeight the new height for the scaled image
     * @return the scaled BufferedImage
     */
    public static BufferedImage resize(BufferedImage img, int newHeight) {
        System.out.println("  Scaling image.");
        double scaleFactor = (double) newHeight / img.getHeight();
        BufferedImage scaledImg = new BufferedImage(
                  (int) (scaleFactor * img.getWidth()), newHeight, BufferedImage.TYPE_BYTE_GRAY);
        AffineTransform at = new AffineTransform();
        at.scale(scaleFactor, scaleFactor);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(img, scaledImg);
    }

    // apply 3x3 Gaussian blur to a grayscale image
    /**
     * Apply 3x3 Gaussian blur to a grayscale image.
     *
     * @param img the grayscale BufferedImage to be blurred
     * @return the blurred BufferedImage
     */
    public static BufferedImage blur(BufferedImage img) {
        BufferedImage blurImg = new BufferedImage(
                  img.getWidth() - 2, img.getHeight() - 2, BufferedImage.TYPE_BYTE_GRAY);
        int pix = 0;
        for (int y = 0; y < blurImg.getHeight(); y++) {
            for (int x = 0; x < blurImg.getWidth(); x++) {
                pix = (int) (4 * (img.getRGB(x + 1, y + 1) & 0xFF)
                          + 2 * (img.getRGB(x + 1, y) & 0xFF)
                          + 2 * (img.getRGB(x + 1, y + 2) & 0xFF)
                          + 2 * (img.getRGB(x, y + 1) & 0xFF)
                          + 2 * (img.getRGB(x + 2, y + 1) & 0xFF)
                          + (img.getRGB(x, y) & 0xFF)
                          + (img.getRGB(x, y + 2) & 0xFF)
                          + (img.getRGB(x + 2, y) & 0xFF)
                          + (img.getRGB(x + 2, y + 2) & 0xFF)) / 16;
                int p = (255 << 24) | (pix << 16) | (pix << 8) | pix;
                blurImg.setRGB(x, y, p);
            }
        }
        return blurImg;
    }

    // apply 5x5 Gaussian blur to a grayscale image
    /**
     * Apply 5x5 Gaussian blur to a grayscale image.
     *
     * @param img the grayscale BufferedImage to be heavily blurred
     * @return the heavily blurred BufferedImage
     */
    public static BufferedImage heavyblur(BufferedImage img) {
        BufferedImage blurImg = new BufferedImage(
                  img.getWidth() - 4, img.getHeight() - 4, BufferedImage.TYPE_BYTE_GRAY);
        int pix = 0;
        for (int y = 0; y < blurImg.getHeight(); y++) {
            for (int x = 0; x < blurImg.getWidth(); x++) {
                pix = (int) (10 * (img.getRGB(x + 3, y + 3) & 0xFF)
                          + 6 * (img.getRGB(x + 2, y + 1) & 0xFF)
                          + 6 * (img.getRGB(x + 1, y + 2) & 0xFF)
                          + 6 * (img.getRGB(x + 2, y + 3) & 0xFF)
                          + 6 * (img.getRGB(x + 3, y + 2) & 0xFF)
                          + 4 * (img.getRGB(x + 1, y + 1) & 0xFF)
                          + 4 * (img.getRGB(x + 1, y + 3) & 0xFF)
                          + 4 * (img.getRGB(x + 3, y + 1) & 0xFF)
                          + 4 * (img.getRGB(x + 3, y + 3) & 0xFF)
                          + 2 * (img.getRGB(x, y + 1) & 0xFF)
                          + 2 * (img.getRGB(x, y + 2) & 0xFF)
                          + 2 * (img.getRGB(x, y + 3) & 0xFF)
                          + 2 * (img.getRGB(x + 4, y + 1) & 0xFF)
                          + 2 * (img.getRGB(x + 4, y + 2) & 0xFF)
                          + 2 * (img.getRGB(x + 4, y + 3) & 0xFF)
                          + 2 * (img.getRGB(x + 1, y) & 0xFF)
                          + 2 * (img.getRGB(x + 2, y) & 0xFF)
                          + 2 * (img.getRGB(x + 3, y) & 0xFF)
                          + 2 * (img.getRGB(x + 1, y + 4) & 0xFF)
                          + 2 * (img.getRGB(x + 2, y + 4) & 0xFF)
                          + 2 * (img.getRGB(x + 3, y + 4) & 0xFF)
                          + (img.getRGB(x, y) & 0xFF)
                          + (img.getRGB(x, y + 2) & 0xFF)
                          + (img.getRGB(x + 2, y) & 0xFF)
                          + (img.getRGB(x + 2, y + 2) & 0xFF)) / 74;
                int p = (255 << 24) | (pix << 16) | (pix << 8) | pix;
                blurImg.setRGB(x, y, p);
            }
        }
        return blurImg;
    }

    // detect edges of a grayscale image using Sobel algorithm
    // (for best results, apply blur before finding edges)
    /**
     * Detect edges of a grayscale image using the Sobel algorithm.
     *
     * @param img the grayscale BufferedImage to detect edges from
     * @return the edge-detected BufferedImage
     */
    public static BufferedImage detectEdges(BufferedImage img) {
        int h = img.getHeight(), w = img.getWidth(), threshold = 30, p = 0;
        BufferedImage edgeImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        int[][] vert = new int[w][h];
        int[][] horiz = new int[w][h];
        int[][] edgeWeight = new int[w][h];
        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                vert[x][y] = (int) (img.getRGB(x + 1, y - 1) & 0xFF + 2 * (img.getRGB(x + 1, y) & 0xFF) + img.getRGB(x + 1, y + 1) & 0xFF
                          - img.getRGB(x - 1, y - 1) & 0xFF - 2 * (img.getRGB(x - 1, y) & 0xFF) - img.getRGB(x - 1, y + 1) & 0xFF);
                horiz[x][y] = (int) (img.getRGB(x - 1, y + 1) & 0xFF + 2 * (img.getRGB(x, y + 1) & 0xFF) + img.getRGB(x + 1, y + 1) & 0xFF
                          - img.getRGB(x - 1, y - 1) & 0xFF - 2 * (img.getRGB(x, y - 1) & 0xFF) - img.getRGB(x + 1, y - 1) & 0xFF);
                edgeWeight[x][y] = (int) (Math.sqrt(vert[x][y] * vert[x][y] + horiz[x][y] * horiz[x][y]));
                if (edgeWeight[x][y] > threshold) {
                    p = (255 << 24) | (255 << 16) | (255 << 8) | 255;
                } else {
                    p = (255 << 24) | (0 << 16) | (0 << 8) | 0;
                }
                edgeImg.setRGB(x, y, p);
            }
        }
        return edgeImg;
    }

    // brighten color image by a percentage
    /**
     * Brighten a color image by a specified percentage.
     *
     * @param img the color BufferedImage to be brightened
     * @param percentage the percentage to increase the brightness by
     * @return the brightened BufferedImage
     */
    public static BufferedImage brighten(BufferedImage img, int percentage) {
        int r = 0, g = 0, b = 0, rgb = 0, p = 0;
        int amount = (int) (percentage * 255 / 100); // rgb scale is 0-255, so 255 is 100%
        BufferedImage newImage = new BufferedImage(
                  img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < img.getHeight(); y += 1) {
            for (int x = 0; x < img.getWidth(); x += 1) {
                rgb = img.getRGB(x, y);
                r = ((rgb >> 16) & 0xFF) + amount;
                g = ((rgb >> 8) & 0xFF) + amount;
                b = (rgb & 0xFF) + amount;
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                p = (255 << 24) | (r << 16) | (g << 8) | b;
                newImage.setRGB(x, y, p);
            }
        }
        return newImage;
    }

    // brighten color image by a percentage
    /**
    * Brightens the given binary image by increasing the intensity of each pixel's color channels (red, green, blue).
    *
    * @param img        The binary image to be brightened.
    * @param percentage The percentage by which to brighten the image. A value of 0 will not change the image, while a value
    *                   of 100 will increase the intensity of each color channel to its maximum value (255).
    * @return A new binary image representing the brightened version of the input image.
    */
    public static Binary brighten(Binary img, int percentage) {
        BufferedImage bufferedImage = ImageHelper.readBinaryAsBufferedImage(img);

        int r = 0, g = 0, b = 0, rgb = 0, p = 0;
        int amount = (int) (percentage * 255 / 100); // rgb scale is 0-255, so 255 is 100%
        BufferedImage newImage = new BufferedImage(
                  bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < bufferedImage.getHeight(); y += 1) {
            for (int x = 0; x < bufferedImage.getWidth(); x += 1) {
                rgb = bufferedImage.getRGB(x, y);
                r = ((rgb >> 16) & 0xFF) + amount;
                g = ((rgb >> 8) & 0xFF) + amount;
                b = (rgb & 0xFF) + amount;
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                p = (255 << 24) | (r << 16) | (g << 8) | b;
                newImage.setRGB(x, y, p);
            }
        }
        return ImageHelper.bufferedImageToBinary(newImage);
    }

}
