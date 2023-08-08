package online.syncio.component;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.layout.UnitValue;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GlassPanePopup {

    protected JLayeredPane getLayerPane() {
        return layerPane;
    }

    private static GlassPanePopup instance;
    private JLayeredPane layerPane;

    private GlassPanePopup() {
        init();
    }

    private void init() {
        layerPane = new JLayeredPane();
        layerPane.setLayout(new CardLayout());
    }

    public void addAndShowPopup(Component component, Option option, String name) {
        Popup popup = new Popup(this, component, option);
        if (name != null) {
            popup.setName(name);
        }
        layerPane.add(popup, 0);
        popup.setVisible(true);
        popup.setShowPopup(true);
        if (!layerPane.isVisible()) {
            layerPane.setVisible(true);
        }
        layerPane.grabFocus();
    }

    private void updateLayout() {
        for (Component com : layerPane.getComponents()) {
            com.revalidate();
        }
    }

    public static void install(JFrame fram) {
        instance = new GlassPanePopup();
        fram.setGlassPane(instance.layerPane);
        fram.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                SwingUtilities.invokeLater(() -> {
                    instance.updateLayout();
                });
            }
        });
    }

    public static void showPopup(Component component, Option option, String name) {
        if (component.getMouseListeners().length == 0) {
            component.addMouseListener(new MouseAdapter() {
            });
        }
        instance.addAndShowPopup(component, option, name);
    }

    public static void showPopup(Component component, Option option) {
        showPopup(component, option, null);
    }

    public static void showPopup(Component component, String name) {
        showPopup(component, new DefaultOption(), name);
    }

    public static void showPopup(Component component) {
        showPopup(component, new DefaultOption(), null);
    }

    public static void closePopup(int index) {
        index = instance.getLayerPane().getComponentCount() - 1 - index;

        if (index >= 0 && index < instance.getLayerPane().getComponentCount()
                && instance.getLayerPane().getComponent(index) instanceof Popup popup) {
            popup.setShowPopup(false);
        }
    }

    public static void closePopupLast() {
        closePopup(getPopupCount() - 1);
    }

    public static void closePopup(String name) {
        for (Component com : instance.layerPane.getComponents()) {
            if (com.getName() != null && com.getName().equals(name) && com instanceof Popup) {
                Popup popup = (Popup) com;
                popup.setShowPopup(false);
            }
        }
    }

    public static void closePopupAll() {
        for (Component com : instance.layerPane.getComponents()) {
            if (com instanceof Popup popup) {
                popup.setShowPopup(false);
            }
        }
    }

    public static int getPopupCount() {
        return instance.layerPane.getComponentCount();
    }

    protected synchronized void removePopup(Component popup) {
        layerPane.remove(popup);
        if (layerPane.getComponentCount() == 0) {
            layerPane.setVisible(false);
        }
    }

    // Option
    public interface Option {

        public LayoutCallback getLayoutCallBack(Component parent);

        public String getLayout(Component parent, float animate);

        public boolean closeWhenClickOutside();

        public boolean blockBackground();

        public Color background();

        public float opacity();

        public int duration();

        public float getAnimate();

        void setAnimate(float animate);
    }

    // Popup
    public class Popup extends JComponent {

        private final DecimalFormat df = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US));
        private final GlassPanePopup parent;
        private final Component component;
        private final Option option;
        private Animator animator;
        private MigLayout layout;
        private float animate;
        private boolean show;
        private boolean mouseHover;

        public Popup(GlassPanePopup parent, Component component, Option option) {
            this.parent = parent;
            this.component = component;
            this.option = option;
            init();
            initAnimator();
        }

        private void init() {
            layout = new MigLayout();
            initOption();
            setLayout(layout);
            add(component, option.getLayout(parent.getLayerPane(), 0));
        }

        private void initOption() {
            LayoutCallback callBack = option.getLayoutCallBack(parent.getLayerPane());
            if (callBack != null) {
                layout.addLayoutCallback(callBack);
            }
            if (option.closeWhenClickOutside()) {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mouseHover = true;
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mouseHover = false;
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (mouseHover && SwingUtilities.isLeftMouseButton(e)) {
                            setShowPopup(false);
                        }
                    }
                });
            } else if (option.blockBackground()) {
                addMouseListener(new MouseAdapter() {
                });
            }
        }

        private void initAnimator() {
            animator = new Animator(option.duration(), new TimingTargetAdapter() {
                @Override
                public void timingEvent(float fraction) {
                    if (show) {
                        animate = fraction;
                    } else {
                        animate = 1f - fraction;
                    }
                    float f = Float.parseFloat(df.format(animate));
                    option.setAnimate(f);
                    String lc = option.getLayout(parent.getLayerPane(), f);
                    if (lc != null) {
                        layout.setComponentConstraints(component, lc);
                    }
                    repaint();
                    revalidate();
                }

                @Override
                public void end() {
                    if (!show) {
                        parent.removePopup(Popup.this);
                    }
                }
            });
            animator.setAcceleration(.5f);
            animator.setDeceleration(.5f);
            animator.setResolution(5);
        }

        public void setShowPopup(boolean show) {
            if (this.show != show) {
                if (animator.isRunning()) {
                    float f = animator.getTimingFraction();
                    animator.stop();
                    animator.setStartFraction(1f - f);
                } else {
                    animator.setStartFraction(0);
                }
                this.show = show;
                animator.start();
                if (!show) {
                    if (getMouseListeners().length != 0) {
                        removeMouseListener(getMouseListeners()[0]);
                    }
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setComposite(AlphaComposite.SrcOver.derive(animate * option.opacity()));
            g2.setColor(option.background());
            g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
            g2.setComposite(AlphaComposite.SrcOver.derive(animate));
            super.paintComponent(g);
        }
    }

    // DefaultOption
    public static class DefaultOption implements Option {

        private float animate;

        @Override
        public LayoutCallback getLayoutCallBack(Component parent) {
            return new DefaultLayoutCallBack(parent);
        }

        @Override
        public String getLayout(Component parent, float animate) {
            float an = 20f / parent.getHeight();
            float space = 0.5f + an - (animate * an);
            return "pos 0.5al " + space + "al";
        }

        @Override
        public boolean closeWhenClickOutside() {
            return true;
        }

        @Override
        public boolean blockBackground() {
            return true;
        }

        @Override
        public Color background() {
            return new Color(100, 100, 100);
        }

        @Override
        public float opacity() {
            return 0.5f;
        }

        @Override
        public int duration() {
            return 300;
        }

        @Override
        public float getAnimate() {
            return animate;
        }

        @Override
        public void setAnimate(float animate) {
            this.animate = animate;
        }
    }

    // DefaultLayoutCallBack
    public static class DefaultLayoutCallBack extends LayoutCallback {

        public Component getParent() {
            return parent;
        }

        private final Component parent;

        public DefaultLayoutCallBack(Component parent) {
            this.parent = parent;
        }

        @Override
        public BoundSize[] getSize(ComponentWrapper cw) {
            Dimension ps = parent.getSize();
            Dimension s = ((Component) cw.getComponent()).getPreferredSize();
            int margin = 50;
            int w = s.width;
            int h = s.height;
            if (s.getWidth() > ps.getWidth() - margin) {
                w = Math.max(ps.width - margin, cw.getMinimumWidth(0));
            }
            if (s.getHeight() > ps.getHeight() - margin) {
                h = Math.max(ps.height - margin, cw.getMinimumHeight(0));
            }
            return new BoundSize[]{new BoundSize(new UnitValue(w), ""), new BoundSize(new UnitValue(h), "")};
        }
    }
}
