package online.syncio.model;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class MyFont {

    public Font SFProDisplayThin;
    public Font SFProDisplayLight;
    public Font SFProDisplayRegular;
    public Font SFProDisplayMedium;
    public Font SFProDisplaySemibold;
    public Font SFProDisplayBold;
    public Font SFProDisplayHeavy;
    public Font SFProDisplayBlack;

    public MyFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            this.SFProDisplayThin = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Thin.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayThin);

            this.SFProDisplayLight = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Light.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayLight);

            this.SFProDisplayRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Regular.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayRegular);

            this.SFProDisplayMedium = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Medium.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayMedium);

            this.SFProDisplaySemibold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Semibold.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplaySemibold);

            this.SFProDisplayBold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Bold.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayBold);

            this.SFProDisplayHeavy = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Heavy.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayHeavy);

            this.SFProDisplayBlack = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Black.otf")).deriveFont(14f);
            ge.registerFont(SFProDisplayBlack);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
