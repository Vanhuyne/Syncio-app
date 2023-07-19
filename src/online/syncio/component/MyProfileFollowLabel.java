package online.syncio.component;

import javax.swing.*;
import java.awt.*;
import online.syncio.resources.fonts.MyFont;

public class MyProfileFollowLabel extends JLabel {

    private Font regularFont = new MyFont().getSFProDisplayRegular(), boldFont = new MyFont().getSFProDisplayBold();

    public MyProfileFollowLabel() {
        super();
        setFontStyle();
    }

    public MyProfileFollowLabel(String text) {
        super(text);
        setFontStyle();
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setFontStyle();
    }

    private void setFontStyle() {
        String text = getText();
        if (text != null && text.length() > 0) {
            String[] parts = text.split(" ");
            if (parts.length == 2) {
                String numberPart = parts[0];
                String wordsPart = parts[1];
                String formattedText = "<html><b>" + numberPart + "</b> " + wordsPart + "</html>";
                super.setText(formattedText);
                super.setFont(boldFont);
            }
        }
    }

}
