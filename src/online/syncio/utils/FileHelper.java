package online.syncio.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;

/**
 * Helper class for various file-related operations.
 */
public class FileHelper {
    
     /**
     * Downloads a file from a given URL and saves it to the specified output directory with the given filename.
     *
     * @param link The URL of the file to be downloaded.
     * @param outputDirectory The directory where the file will be saved.
     * @param outputFileName The name of the file to be saved.
     */
    public static void downloadFileFromWebsite(String link, String outputDirectory, String outputFileName) {
        try {
            URL url = new URL(link);
            
            // Check if you have write permission in the output directory
            if (!Files.isWritable(Paths.get(outputDirectory))) {
                GlassPanePopup.showPopup(new MyDialog("Permission Error", "We don't have permission to update in " + outputDirectory + "<br>Please run the application with administrative privileges and try again."), "dialog");
            }
            
            // Check if the URL exists
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                GlassPanePopup.showPopup(new MyDialog("URL Error", "The download URL does not exist or is not accessible."), "dialog");
                return;
            }
            
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            
            File outputDir = new File(outputDirectory);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(outputDir, outputFileName);
            FileOutputStream fis = new FileOutputStream(outputFile);
            
            byte[] buffer = new byte[1024];
            int count=0;
            while((count = bis.read(buffer,0,1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            
            fis.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     /**
     * Unzips a given ZIP file into the specified directory.
     *
     * @param directory The directory where the ZIP file is located.
     * @param fileName The name of the ZIP file.
     */
    public static void unzip(String directory, String fileName) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(directory + "/" + fileName))) {
            byte[] buffer = new byte[1024];
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                File entryFile = new File(directory, entry.getName());

                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    try (OutputStream outputStream = new FileOutputStream(entryFile)) {
                        int length;
                        while ((length = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                    }
                }

                zipInputStream.closeEntry();
            }
            
            // Close the zipInputStream
            zipInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    /**
     * Deletes a file with the given filename from the specified directory.
     *
     * @param directory The directory where the file is located.
     * @param fileName The name of the file to be deleted.
     */
    public static void deleteFile(String directory, String fileName) {
        try {
            Files.deleteIfExists(Paths.get(directory, fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
