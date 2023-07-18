package online.syncio.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MyScrollPane extends JScrollPane {

    public MyScrollPane() {
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());
        
        //Remove border intersection between JTable and JScrollPane
        setBorder(BorderFactory.createLineBorder(new Color(219, 219, 219)));
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        EventQueue.invokeLater(() -> {
            setComponentZOrder(getVerticalScrollBar(), 0);
            setComponentZOrder(getHorizontalScrollBar(), 1);
            setComponentZOrder(getViewport(), 2);
            getVerticalScrollBar().setOpaque(false);
            getHorizontalScrollBar().setOpaque(false);
        });
    }

    
    
    private class ScrollLayout extends ScrollPaneLayout {
        @Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) parent;
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }
        }
    }
    
    
    
    private static class ScrollBarWin11UI extends BasicScrollBarUI {
        private Animator animator;
        private float animate;
        private boolean show;
        private boolean hover;
        private boolean press;
        private final int scrollSize = 14;
        private final MouseAdapter mouseEvent;

        /**
         * Creates the UI for the given component.
         *
         * @param c The component for which to create the UI.
         * @return The created ScrollBarWin11UI.
         */
        public static ComponentUI createUI(JComponent c) {
            return new ScrollBarWin11UI();
        }

        /**
         * Creates a new instance of the ScrollBarWin11UI.
         */
        public ScrollBarWin11UI() {
            mouseEvent = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    press = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    press = false;
                    if (!hover) {
                        start(false);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    if (!show) {
                        start(true);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    if (!press) {
                        start(false);
                    }
                }
            };
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            c.setPreferredSize(new Dimension(scrollSize, scrollSize));
            c.addMouseListener(mouseEvent);
            c.setForeground(new Color(150, 150, 150));
            initAnimator();
        }

        /**
         * Starts the animation with the specified show parameter.
         *
         * @param show true if the animation should show, false otherwise
         */
        private void start(boolean show) {
            if (animator.isRunning()) {
                float f = animator.getTimingFraction();
                animator.stop();
                animator.setStartFraction(1f - f);
            } else {
                animator.setStartFraction(0f);
            }
            this.show = show;
            animator.start();
        }

        /**
         * Initializes the animator for the scrollbar.
         */
        private void initAnimator() {
            animator = new Animator(150, new TimingTargetAdapter() {
                @Override
                public void timingEvent(float fraction) {
                    if (show) {
                        animate = fraction;
                    } else {
                        animate = 1f - fraction;
                    }
                    if (scrollbar != null) {
                        scrollbar.repaint();
                    }
                }
            });
            animator.setResolution(5);
            animator.setAcceleration(.5f);
            animator.setDeceleration(.5f);
            animator.setStartDelay(100);
        }

        /**
         * Creates and returns a custom scroll button with the specified orientation.
         *
         * @param orientation the orientation of the scroll button
         * @return the created scroll button
         */
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new ScrollButton(scrollbar.getOrientation(), true);
        }

        /**
         * Creates and returns a custom scroll button with the specified orientation.
         *
         * @param orientation the orientation of the scroll button
         * @return the created scroll button
         */
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new ScrollButton(scrollbar.getOrientation(), false);
        }

        /**
         * Paints the track of the scrollbar.
         *
         * @param g           the Graphics object to paint on
         * @param c           the JComponent (scrollbar) being painted
         * @param trackBounds the bounding rectangle of the track
         */
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setComposite(AlphaComposite.SrcOver.derive(animate * 0.5f));
            g2.setColor(scrollbar.getForeground().brighter());
            g2.fill(new Rectangle2D.Double(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height));
            g2.dispose();
        }

        /**
         * Paints the thumb of the scrollbar.
         *
         * @param g           the Graphics object to paint on
         * @param c           the JComponent (scrollbar) being painted
         * @param thumbBounds the bounding rectangle of the thumb
         */
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(scrollbar.getForeground());
            double border = scrollSize * 0.3f - (animate * 1);
            double sp = 10 * animate;
            g2.setComposite(AlphaComposite.SrcOver.derive(1f - (1f - animate) * 0.3f));
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                double width = thumbBounds.getWidth() - border * 2;
                double height = thumbBounds.getHeight() - sp * 2;
                g2.fill(new RoundRectangle2D.Double(thumbBounds.x + border, thumbBounds.y + sp, width, height, width, width));
            } else {
                double width = thumbBounds.getWidth() - sp * 2;
                double height = thumbBounds.getHeight() - border * 2;
                g2.fill(new RoundRectangle2D.Double(thumbBounds.x + sp, thumbBounds.y + border, width, height, height, height));
            }
            g2.dispose();
            g.dispose();
        }



        /**
         * Represents a custom scroll button for the scrollbar.
         */
        private class ScrollButton extends JButton {

            private final int orientation;
            private final boolean isIncrease;
            private final Shape arrow;
            private boolean mouseHover;
            private boolean mousePress;

            /**
             * Constructs a new ScrollButton.
             *
             * @param orientation the orientation of the scrollbar (JScrollBar.VERTICAL or JScrollBar.HORIZONTAL)
             * @param isIncrease  true if the button represents the increase (down/right) direction, false otherwise
             */
            public ScrollButton(int orientation, boolean isIncrease) {
                this.orientation = orientation;
                this.isIncrease = isIncrease;
                setContentAreaFilled(false);
                setPreferredSize(new Dimension(scrollSize, scrollSize));
                List<Point2D> points = new ArrayList<>();
                double width = scrollSize * 0.8f;
                double height = scrollSize * 0.7f;
                if (orientation == JScrollBar.VERTICAL) {
                    if (isIncrease) {
                        points.add(new Point2D.Double(width / 2, height));
                        points.add(new Point2D.Double(width, 0));
                        points.add(new Point2D.Double(0, 0));
                    } else {
                        points.add(new Point2D.Double(width / 2, 0));
                        points.add(new Point2D.Double(width, height));
                        points.add(new Point2D.Double(0, height));
                    }
                } else {
                    if (isIncrease) {
                        points.add(new Point2D.Double(0, 0));
                        points.add(new Point2D.Double(width, height / 2));
                        points.add(new Point2D.Double(0, height));
                    } else {
                        points.add(new Point2D.Double(width, 0));
                        points.add(new Point2D.Double(0, height / 2));
                        points.add(new Point2D.Double(width, height));
                    }
                }
                arrow = new PolygonCorner().getRoundedGeneralPathFromPoints(points, scrollSize * 0.5f);
                addMouseListener(mouseEvent);
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
                    public void mousePressed(MouseEvent e) {
                        mousePress = true;
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        mousePress = false;
                    }
                });
            }

            /**
             * Paints the component, including the scroll button.
             *
             * @param g the Graphics object to paint on
             */
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setComposite(AlphaComposite.SrcOver.derive(animate * 0.5f));
                g2.setColor(scrollbar.getForeground().brighter());
                int x = 0;
                int y = isIncrease ? 8 : 0;
                int width = getWidth();
                int height = getHeight() - 8;
                if (orientation == JScrollBar.VERTICAL) {
                    g2.fill(new Rectangle2D.Double(x, y, width, height));
                } else {
                    g2.fill(new Rectangle2D.Double(y, x, height, width));
                }
                g2.setComposite(AlphaComposite.SrcOver.derive(animate));
                if (mousePress) {
                    g2.setColor(new Color(110, 110, 110));
                } else if (mouseHover) {
                    g2.setColor(new Color(130, 130, 130));
                } else {
                    g2.setColor(scrollbar.getForeground());
                }
                double ax = scrollSize * 0.1f;
                double ay = scrollSize * 0.15f;
                g2.translate(ax, ay);
                g2.fill(arrow);
                g2.dispose();
                super.paintComponent(g);
            }
        }



        /**
         * Utility class for generating a rounded GeneralPath from a list of points, creating a polygon with rounded corners.
         */
        public class PolygonCorner {
            /**
             * Generates a rounded GeneralPath from a list of points.
             *
             * @param points   The list of points defining the polygon.
             * @param arcSize  The size of the corner arcs.
             * @return A rounded GeneralPath representing the polygon with rounded corners.
             */
            public GeneralPath getRoundedGeneralPathFromPoints(List<Point2D> l, float arcSize) {
                l.add(l.get(0));
                l.add(l.get(1));
                GeneralPath p = new GeneralPath();
                Point2D startPoint = calculatePoint(l.get(l.size() - 1), l.get(l.size() - 2), arcSize);
                p.moveTo(startPoint.getX(), startPoint.getY());
                for (int pointIndex = 1; pointIndex < l.size() - 1; pointIndex++) {
                    Point2D p1 = l.get(pointIndex - 1);
                    Point2D p2 = l.get(pointIndex);
                    Point2D p3 = l.get(pointIndex + 1);
                    Point2D mPoint = calculatePoint(p1, p2, arcSize);
                    p.lineTo(mPoint.getX(), mPoint.getY());
                    mPoint = calculatePoint(p3, p2, arcSize);
                    p.curveTo(p2.getX(), p2.getY(), p2.getX(), p2.getY(), mPoint.getX(), mPoint.getY());
                }
                return p;
            }

            /**
             * Calculates the point on the curve based on the given points and arc size.
             *
             * @param p1      The previous point.
             * @param p2      The current point.
             * @param arcSize The size of the corner arc.
             * @return The calculated point on the curve.
             */
            private Point2D calculatePoint(Point2D p1, Point2D p2, float arcSize) {
                double d1 = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2f) + Math.pow(p1.getY() - p2.getY(), 2f));
                double per = arcSize / d1;
                double d_x = (p1.getX() - p2.getX()) * per;
                double d_y = (p1.getY() - p2.getY()) * per;
                double xx = p2.getX() + d_x;
                double yy = p2.getY() + d_y;
                return new Point.Double(xx, yy);
            }
        }
    }
}
