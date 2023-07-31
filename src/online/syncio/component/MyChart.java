package online.syncio.component;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import online.syncio.resources.fonts.MyFont;

public class MyChart extends JPanel {
    private String chartName = "My Chart";
    private int chartWIDTH;
    private int chartHEIGHT = 300;
    private int chartColWidth = 70;
    private int chartGap = 10;
    private int chartPadding = 50;
    private Color chartColColor = new Color(0, 149, 246);
    private ArrayList<Double> chartColValue = new ArrayList<>();
    private boolean isDouble = false;
    private ArrayList<String> chartColName = new ArrayList<>();
    private Color chartColNameColor = Color.BLACK;
    private Color chartColValueColor = Color.BLACK;
    private Color chartLineColor = new Color(219, 219, 219);
    private Color chartLineValueColor = new Color(128, 128, 128);

    public MyChart() {
        // Initialize the MyChart with sample data
        chartColValue.add(10.0);
        chartColValue.add(20.0);
        chartColValue.add(15.75);

        chartColName.add("John Doe");
        chartColName.add("Alice");
        chartColName.add("Bob");

        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, chartColValue.size(), chartGap, 5));
        chartWIDTH = (chartColValue.size() * chartColWidth) +  (chartColValue.size() - 1) * chartGap + (chartPadding * 2);
        setPreferredSize(new Dimension(chartWIDTH, chartHEIGHT));
        setSize(chartWIDTH, chartHEIGHT);
    }
    
    public MyChart(ArrayList<String> chartColName, ArrayList<?> chartColValue) {
        this.chartColName = chartColName;
        this.chartColValue = new ArrayList<>();

        // Convert the List<?> to ArrayList<Double>
        for (Object value : chartColValue) {
            if (value instanceof Double) {
                this.chartColValue.add((Double) value);
            } else if (value instanceof Integer) {
                this.chartColValue.add(((Integer) value).doubleValue());
            }
        }

        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, chartColValue.size(), chartGap, 5));
        chartWIDTH = (chartColValue.size() * chartColWidth) +  (chartColValue.size() - 1) * chartGap + (chartPadding * 2);
        setPreferredSize(new Dimension(chartWIDTH, chartHEIGHT));
        setSize(chartWIDTH, chartHEIGHT);
    }

        

    @Override
    protected void paintComponent(Graphics g) {
        int numCols = chartColValue.size();
        
        int realSize = (numCols * chartColWidth) + ((numCols - 1) * chartGap) + (chartPadding * 2);
        
        double ratio = (double) realSize / getSize().width;
        this.chartWIDTH = getSize().width;
        this.chartColWidth = (chartWIDTH - (chartPadding * 2 + chartGap * (numCols - 1))) / numCols;
        
        if(containsDouble(chartColValue)) isDouble = true;
        
        Graphics2D g2d = (Graphics2D)g;    
        g2d.setFont(new MyFont().getSFProDisplayMedium());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        super.paintComponent(g);
        
        int fullHeight = chartHEIGHT;
        
        // Calculate the maximum column height
        double maxColumnHeight = fullHeight - chartPadding * 2;

        // Calculate the step size for each line
        int lineNum = maxColumnHeight < 5 ? 1 : 5;
        double stepSize = maxColumnHeight / (double)lineNum;

        // Draw the lines through the chart with numbers
        for (int i = 0; i <= lineNum; i++) {
            int lineY = fullHeight - (int) (stepSize * i) - chartPadding;
            g2d.setColor(chartLineColor);
            g2d.drawLine(chartPadding - 20, lineY, chartWIDTH - 20, lineY);

            // Draw the number above the line
            g2d.setFont(new MyFont().getSFProDisplayRegular().deriveFont(10f));
            FontMetrics fm = g2d.getFontMetrics();
            String numberLabel = String.format(isDouble ? "%.2f" : "%.0f", Collections.max(chartColValue) / 5 * i);
            int numberLabelWidth = fm.stringWidth(numberLabel);
            int numberLabelX = chartPadding - 20;
            int numberLabelY = lineY - fm.getHeight() / 2; // Align the number above the line
            g2d.setColor(chartLineValueColor);
            g2d.drawString(numberLabel, numberLabelX, numberLabelY);
        }


        // Draw the chart
        g2d.setFont(new MyFont().getSFProDisplayMedium());
        for (int i = 0; i < numCols; i++) {
            Double value = chartColValue.get(i);
            String name = chartColName.get(i);

            // col size
            double percent = (double) ((chartHEIGHT - chartPadding * 2) / Collections.max(chartColValue));
            int columnHeight = (int) (value * percent);

            // col background
            g.setColor(chartColColor);
            g.fillRect((i * (chartColWidth + chartGap)) + chartPadding, fullHeight - columnHeight - chartPadding, chartColWidth, columnHeight);

            // col labels - value
            g2d.setColor(chartColValueColor);
            FontMetrics fm = g2d.getFontMetrics();
            String valueLabel = String.format(isDouble ? "%.2f" : "%.0f", value);
            int valueLabelWidth = fm.stringWidth(valueLabel);
            int valueLabelX = (i * (chartColWidth + chartGap) + (chartColWidth - valueLabelWidth) / 2) + chartPadding;
            int valueLabelY = fullHeight - columnHeight - 5 - chartPadding;

            // col labels - name
            g2d.setColor(chartColNameColor);
            int nameLabelWidth = fm.stringWidth(name);
            int nameLabelX = (i * (chartColWidth + chartGap) + (chartColWidth - nameLabelWidth) / 2) + chartPadding;
            int nameLabelY = fullHeight - chartPadding + 15;

            g2d.drawString(valueLabel, valueLabelX, valueLabelY);
            g2d.drawString(name, nameLabelX, nameLabelY);
        }
        
        // Draw the chart name at the bottom center of the chart
        g2d.setFont(new MyFont().getSFProDisplayBold());
        int chartNameWidth = g2d.getFontMetrics().stringWidth(chartName);
        int chartNameX = ((numCols * (chartColWidth + chartGap) - chartGap - chartNameWidth) / 2) + chartPadding;
        g2d.drawString(chartName, chartNameX, fullHeight - 10);
    }

    
    
    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    
    public int getChartWIDTH() {
        return chartWIDTH;
    }

    public void setChartWIDTH(int chartWIDTH) {
        this.chartWIDTH = chartWIDTH;
    }

    public int getChartHEIGHT() {
        return chartHEIGHT;
    }

    public void setChartHEIGHT(int chartHEIGHT) {
        this.chartHEIGHT = chartHEIGHT;
    }

    public int getChartColWidth() {
        return chartColWidth;
    }

//    public void setChartColWidth(int chartColWidth) {
//        this.chartColWidth = chartColWidth;
//    }

    public int getChartGap() {
        return chartGap;
    }

    public void setChartGap(int chartGap) {
        this.chartGap = chartGap;
    }

    public int getChartPadding() {
        return chartPadding;
    }

    public void setChartPadding(int chartPadding) {
        this.chartPadding = chartPadding;
    }

    public Color getChartColColor() {
        return chartColColor;
    }

    public void setChartColColor(Color chartColColor) {
        this.chartColColor = chartColColor;
    }

    public ArrayList<Double> getChartColValue() {
        return chartColValue;
    }

    public void setChartColValue(ArrayList<Double> chartColValue) {
        this.chartColValue = chartColValue;
    }

    public ArrayList<String> getChartColName() {
        return chartColName;
    }

    public void setChartColName(ArrayList<String> chartColName) {
        this.chartColName = chartColName;
    }

    public Color getChartColNameColor() {
        return chartColNameColor;
    }

    public void setChartColLabelColor(Color chartColNameColor) {
        this.chartColNameColor = chartColNameColor;
    }
    
    
    
    private boolean containsDouble(ArrayList<? extends Number> list) {
        for (Number number : list) {
            if (number.doubleValue() != number.intValue()) {
                System.out.println(number.doubleValue());
                System.out.println(number.intValue());
                return true;
            }
        }
        return false;
    }
    
}
