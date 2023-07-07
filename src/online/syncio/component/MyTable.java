package online.syncio.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import online.syncio.model.MyFont;

public class MyTable extends JTable {
    
    public MyTable() {
        Object[][] data = {{"Row 1, Column 1", "Row 1, Column 2"}, {"Row 2, Column 1", "Row 2, Column 2"}};
        Object[] columnNames = {"Column 1", "Column 2"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        setModel(model);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Style
        setRowHeight(30); //width height
        setForeground(Color.BLACK);
        setFont(new MyFont().SFProDisplayMedium);
        
        getTableHeader().setPreferredSize(new Dimension(0,30)); //width height
//        getTableHeader().setFont(new Font("Open Sans", 1, 3));
        getTableHeader().setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT); //Left-align Headings
        
        setSelectionForeground(Color.WHITE); //change text color of selected row
        setSelectionBackground(new Color(0, 149, 246)); //change background color of selected row
        
        

        setIntercellSpacing(new Dimension(0, 0));
        setShowGrid(false);
        getTableHeader().setBorder(BorderFactory.createEmptyBorder());

        //header line
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
          private final CellBorder border = new CellBorder(2, 2, 2, 2);
          @Override public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected, boolean hasFocus,
              int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                border.isStartCell = column == 0;
                c.setFont(new MyFont().SFProDisplayBold);
                c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(219, 219, 219)), BorderFactory.createEmptyBorder(2, 3, 2, 3)));
                return c;
            }
        });
        
        //cell line
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final CellBorder border = new CellBorder(2, 2, 2, 2);

            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
              JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
              border.isStartCell = column == 0;
              c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(219, 219, 219)), BorderFactory.createEmptyBorder(2, 3, 2, 3)));
              return c;
            }
        });
        
    }
    
    
    
    private class CellBorder extends EmptyBorder {
        private final Color gridColor = UIManager.getColor("Table.gridColor");
        public boolean isStartCell = false;
        protected CellBorder(int top, int left, int bottom, int right) {
            super(top, left, bottom, right);
        }
        @Override public boolean isBorderOpaque() {
            return true;
        }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.translate(x, y);
            g2.setPaint(gridColor);
            if (!isStartCell) {
                g2.drawLine(0, 0, 0, h - 1); // Left line
            }
            g2.dispose();
        }
    }
}
