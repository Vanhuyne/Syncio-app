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

public class MyTable extends JTable {
    
    private int fontBold = 1;
    private int fontHeaderBold = 2;
    JLabel tableHeaderTitle;
    
    public int getFontBold() {
        return fontBold;
    }

    public void setFontBold(int fontBold) {
        this.fontBold = fontBold;
        setFont(ComponentInit.fontStyle(getFontBold(), this));
    }

    public int getFontHeaderBold() {
        return fontHeaderBold;
    }

    public void setFontHeaderBold(int fontHeaderBold) {
        this.fontHeaderBold = fontHeaderBold;
    }
    
    
    
    public MyTable() {
        Object[][] data = {{"Row 1, Column 1", "Row 1, Column 2"}, {"Row 2, Column 1", "Row 2, Column 2"}};
        Object[] columnNames = {"Column 1", "Column 2"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        setModel(model);
        
        ComponentInit.applyCommonProperties(this);
        
        // Style
        setPreferredSize(new Dimension(400, 200));
        setPreferredScrollableViewportSize(new Dimension(400, 200));
        setRowHeight(30); //width height
        setForeground(Color.BLACK);
        setSelectionForeground(Color.WHITE); //change text color of selected row
        setSelectionBackground(new Color(0, 149, 246)); //change background color of selected row
        
        getTableHeader().setPreferredSize(new Dimension(0,30)); //width height
        getTableHeader().setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT); //Left-align Headings

        setIntercellSpacing(new Dimension(0, 0));
        setShowGrid(false);
        getTableHeader().setBorder(BorderFactory.createEmptyBorder());

        //header line
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
          private final CellBorder border = new CellBorder(2, 2, 2, 2);
          @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                tableHeaderTitle = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                border.isStartCell = column == 0;
                tableHeaderTitle.setFont(ComponentInit.fontStyle(getFontHeaderBold(), tableHeaderTitle));
                tableHeaderTitle.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(219, 219, 219)), BorderFactory.createEmptyBorder(2, 3, 2, 3)));
                return tableHeaderTitle;
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
