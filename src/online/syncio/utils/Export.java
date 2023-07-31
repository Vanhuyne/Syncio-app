package online.syncio.utils;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.MessageFormat;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Export class provides utility methods for exporting Swing components.
 */
public class Export {
    /**
     * Exports the specified JPanel to a PNG image file.
     *
     * @param pnl the JPanel to be exported
     */
    public static void panelToImage(JPanel pnl) {
        // Create an image of the panel
        BufferedImage image = new BufferedImage(pnl.getWidth(), pnl.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        pnl.print(g);

        // Save the image to a file
        try {
            JFileChooser save = new JFileChooser();
            
            // disable the "All files" option
            save.setAcceptAllFileFilterUsed(false);

            int result = save.showSaveDialog(null); //gọi hộp thoại

            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = save.getSelectedFile();
                String fileName = selectedFile.getName(); // get the selected file name

                // Remove the extension part from the file name
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex > 0) {
                    fileName = fileName.substring(0, dotIndex);
                }

                String path = selectedFile.getParent(); // get the selected path
                ImageIO.write(image, "PNG", new File(path, fileName + ".png"));
                JOptionPane.showMessageDialog(null, "Export successful to " + fileName + ".png");
            }   
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error exporting: " + ex.getMessage());
        }
    }
    
    
    
    /**
     * Exports the specified JTable to a PDF file.
     *
     * @param tbl the JTable to be exported
     * @param fileName the file name of the exported PDF (without file extension)
     */
    public static void tableToPDF(JTable tbl, String fileName) {
        try {
            boolean complete = tbl.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat(fileName), new MessageFormat("Page - {0}"));
            if (complete) {
                JOptionPane.showMessageDialog(null, "Export successful");
            } else {
                JOptionPane.showMessageDialog(null, "Cancel");
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null, "Error exporting: " + pe.getMessage());
        }
    }
    
    
    
    public static void writeToExcell(JTable table, String sheetName)  {
        try{
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(null);
            File saveFile = jFileChooser.getSelectedFile();

            if(saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet(sheetName);

                // Start writing from column 2 (index 1) and row 2 (index 1)
                int startColumn = 1;
                int startRow = 1;
                
                // Create a CellStyle with bold font for the header row
                CellStyle headerCellStyle = wb.createCellStyle();
                Font headerFont = wb.createFont();
                headerFont.setBold(true);
                headerCellStyle.setFont(headerFont);

                Row rowCol = sheet.createRow(startRow);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(startColumn + i);
                    cell.setCellValue(table.getColumnName(i));
                    cell.setCellStyle(headerCellStyle); // Apply the bold font style to the cell
                }

                for (int j = 0; j < table.getRowCount(); j++) {
                    Row row = sheet.createRow(startRow + j + 1); // Add 1 to skip the header row
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        Cell cell = row.createCell(startColumn + k);
                        if (table.getValueAt(j, k) != null) {
                            cell.setCellValue(table.getValueAt(j, k).toString());
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(saveFile);
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
            else{
//                JOptionPane.showMessageDialog(null,"Cancel");
            }
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
    }
    
    public static void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }
    }
}
