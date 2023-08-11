package online.syncio.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileHelper {
    
    public static void downloadFileFromWebsite(String link, String outputDirectory, String outputFileName) {
        try {
            URL url = new URL(link);
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
    
    
    
    public static void deleteFile(String directory, String fileName) {
        try {
            Files.deleteIfExists(Paths.get(directory, fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
