package online.syncio.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFont {

//    public Font SFProDisplayThin;
//    public Font SFProDisplayLight;
    public Font SFProDisplayRegular;
    public Font SFProDisplayMedium;
//    public Font SFProDisplaySemibold;
    public Font SFProDisplayBold;
//    public Font SFProDisplayHeavy;
//    public Font SFProDisplayBlack;

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    
    public MyFont() {
//        try {
//            
//            this.SFProDisplayThin = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Thin.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayThin);
//
//            this.SFProDisplayLight = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Light.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayLight);
//
//            this.SFProDisplayRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Regular.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayRegular);
//
//            this.SFProDisplayMedium = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Medium.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayMedium);
//
//            this.SFProDisplaySemibold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Semibold.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplaySemibold);
//
//            this.SFProDisplayBold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Bold.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayBold);
//
//            this.SFProDisplayHeavy = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Heavy.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayHeavy);
//
//            this.SFProDisplayBlack = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Black.otf")).deriveFont(14f);
//            ge.registerFont(SFProDisplayBlack);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public Font getSFProDisplayRegular() {
        try {
            this.SFProDisplayRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Regular.otf"));
            ge.registerFont(SFProDisplayRegular);
            return SFProDisplayRegular;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    

    public Font getSFProDisplayMedium() {
        try {
            this.SFProDisplayMedium = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Medium.otf"));
            ge.registerFont(SFProDisplayMedium);
            return SFProDisplayMedium;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    
    public Font getSFProDisplayBold() {
        try {
            this.SFProDisplayBold = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/online/syncio/resources/fonts/SF-Pro-Display-Bold.otf"));
            ge.registerFont(SFProDisplayBold);
            return SFProDisplayBold;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
