package online.syncio.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

public class MyComboBox<E> extends JComboBox<E> {
    
    private int fontBold = 1;

    public int getFontBold() {
        return fontBold;
    }

    public void setFontBold(int fontBold) {
        this.fontBold = fontBold;
        setFont(ComponentInit.fontStyle(getFontBold(), this));
    }
    
    public MyComboBox() {
        setUI(new ComboSuggestionUI());
        ComponentInit.applyCommonProperties(this);
    }

    private class ComboSuggestionUI extends BasicComboBoxUI {

        @Override
        public void installUI(JComponent jc) {
            super.installUI(jc);
            Border border = new Border();
            JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
            txt.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent fe) {
//                    border.setColor(new Color(128, 189, 255));
                }

                @Override
                public void focusLost(FocusEvent fe) {
//                    border.setColor(new Color(206, 212, 218));
                }
            });
            txt.setSelectionColor(new Color(0, 149, 246));
            txt.setBorder(new EmptyBorder(0, 4, 0, 4));
            comboBox.setBackground(Color.WHITE);
            comboBox.setBorder(border);
        }

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        protected ComboPopup createPopup() {
            return new ComboSuggestionPopup(comboBox);
        }

        @Override
        protected ListCellRenderer createRenderer() {
            return new ListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList jlist, Object e, int i, boolean bln, boolean bln1) {
                    String text = e == null ? "" : e.toString();
                    JLabel label = new JLabel(text);
                    label.setFont(comboBox.getFont());
                    if (i >= 0) {
                        label.setBorder(new EmptyBorder(5, 8, 5, 8));
                    } else {
                        label.setBorder(null);
                    }
                    if (bln) {
                        label.setOpaque(true);
                        label.setBackground(new Color(240, 240, 240));
                        label.setForeground(new Color(0, 149, 246));
                    }
                    return label;
                }
            };
        }

        @Override
        public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {
        }

        private class ComboSuggestionPopup extends BasicComboPopup {

            public ComboSuggestionPopup(JComboBox combo) {
                super(combo);
                setBorder(new Border(1));
            }

            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroll = super.createScroller();
                list.setBackground(Color.WHITE);
                ScrollBarCustom sb = new ScrollBarCustom();
                sb.setPreferredSize(new Dimension(12, 70));
                scroll.setVerticalScrollBar(sb);
                ScrollBarCustom sbH = new ScrollBarCustom();
                sbH.setOrientation(JScrollBar.HORIZONTAL);
                scroll.setHorizontalScrollBar(sbH);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                return scroll;
            }
        }

        private class ArrowButton extends JButton {

            public ArrowButton() {
                setContentAreaFilled(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setBackground(new Color(150, 150, 150));
            }

            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                Graphics2D g2 = (Graphics2D) grphcs.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                int sizeX = 12;
                int sizeY = 8;
                int x = (width - sizeX) / 2;
                int y = (height - sizeY) / 2;
                int px[] = {x, x + sizeX, x + sizeX / 2};
                int py[] = {y, y, y + sizeY};
                g2.setColor(getBackground());
                g2.fillPolygon(px, py, px.length);
                g2.dispose();
            }
        }

        private class Border extends EmptyBorder {

            public Color getFocusColor() {
                return focusColor;
            }

            public void setFocusColor(Color focusColor) {
                this.focusColor = focusColor;
            }

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
            }

            private Color focusColor = Color.BLACK;
            private Color color = new Color(206, 212, 218);

            public Border(int border) {
                super(border, border, border, border);
            }

            public Border() {
                this(5);
            }

            @Override
            public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) grphcs.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (cmpnt.isFocusOwner()) {
                    g2.setColor(focusColor);
                } else {
                    g2.setColor(color);
                }
                g2.drawRect(x, y, width - 1, height - 1);
                g2.dispose();
            }
        }
    }
    
    
    
    private class ScrollBarCustom extends JScrollBar {
        public ScrollBarCustom() {
            setUI(new ModernScrollBarUI());
            setPreferredSize(new Dimension(8, 8));
            setForeground(new Color(180, 180, 180));
            setBackground(Color.WHITE);
            setUnitIncrement(20);
        }

        private class ModernScrollBarUI extends BasicScrollBarUI {

            private final int THUMB_SIZE = 60;

            @Override
            protected Dimension getMinimumThumbSize() {
                if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                    return new Dimension(0, THUMB_SIZE);
                } else {
                    return new Dimension(THUMB_SIZE, 0);
                }
            }

            @Override
            protected JButton createIncreaseButton(int i) {
                return new ScrollBarButton();
            }

            @Override
            protected JButton createDecreaseButton(int i) {
                return new ScrollBarButton();
            }

            @Override
            protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {
            }

            @Override
            protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int x = rctngl.x;
                int y = rctngl.y;
                int width = rctngl.width - 4;
                int height = rctngl.height;
                if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                    y += 8;
                    height -= 16;
                } else {
                    x += 8;
                    width -= 16;
                }
                g2.setColor(scrollbar.getForeground());
                g2.fillRoundRect(x + 2, y, width, height, 10, 10);
            }

            private class ScrollBarButton extends JButton {

                public ScrollBarButton() {
                    setBorder(BorderFactory.createEmptyBorder());
                }

                @Override
                public void paint(Graphics grphcs) {
                }
            }
        }
    }
}
