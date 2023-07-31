package online.syncio.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JCheckBox;

public class MyCheckBox extends JCheckBox {

    private int radius = 5;
    private Color selectedBackgroundColor = new Color(0, 149, 246);

    public MyCheckBox() {
        ComponentInit.applyCommonProperties(this);
    }
    

    
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly = (getHeight() - 16) / 2;
        if (isSelected()) {
            if (isEnabled()) {
                g2.setColor(selectedBackgroundColor);
            } else {
                g2.setColor(Color.GRAY);
            }
            g2.fillRoundRect(1, ly, 16, 16, radius, radius);
            
            // Draw Check icon as a centered straight tick with a shorter left line
            int px[] = {5, 7, 13}; // X-coordinates of the three points for the centered tick
            int py[] = {ly + 9, ly + 12, ly + 5}; // Y-coordinates of the three points for the centered tick
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2)); // Increase the stroke thickness for a bolder tick

            g2.drawPolyline(px, py, px.length);
        } else {
            g2.setColor(new Color(219, 219, 219));
            g2.fillRoundRect(1, ly, 16, 16, radius, radius);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(2, ly + 1, 14, 14, radius, radius);
        }
        g2.dispose();
    }
}
