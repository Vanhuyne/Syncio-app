package online.syncio.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

/**
 * Panel for displaying report options and capturing user-selected reasons for reporting.
 */
public class Options extends javax.swing.JPanel {

    public enum OptionType {
        MORE_OPTIONS,
        REPORT_REASON
    }

    public interface ReasonSelectedCallback {
        void onReasonSelected(int reason);
    }

    public interface OptionSelectedCallback {
        void onOptionSelected(int option);
    }

    private ReasonSelectedCallback reasonCallback;
    private OptionSelectedCallback optionCallback;

    private OptionType optionType;
    private int selectedValue = -1;

    public static final Options moreOptionsPanel = new Options(OptionType.MORE_OPTIONS);
    public static final Options reportReasonPanel = new Options(OptionType.REPORT_REASON);

    public static enum MoreOptions {
        COPYLINK(0, "Copy link"),
        REPORT(1, "Report");

        private final int value;
        private final String label;

        MoreOptions(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }

    public static enum ReportReason {
        SPAM(0, "It's spam"),
        VIOLENCE(1, "Violence or dangerous organizations"),
        FALSE_INFORMATION(2, "False information"),
        DISLIKE(3, "I just don't like it");

        private final int value;
        private final String label;

        ReportReason(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }
    }
    
    /**
     * Retrieves the label associated with a report reason value.
     * @param value The report reason value.
     * @return The label corresponding to the value.
     */
    public static String getReportReasonLabel(int value) {
        for (Options.ReportReason reason : Options.ReportReason.values()) {
            if (reason.getValue() == value) {
                return reason.getLabel();
            }
        }
        return null;
    }

    public static String getMoreOptionsLabel(int value) {
        for (Options.MoreOptions moreOption : Options.MoreOptions.values()) {
            if (moreOption.getValue() == value) {
                return moreOption.getLabel();
            }
        }
        return null;
    }

  
  
    public Options() {
=======

    public Options(OptionType optionType) {
        this.optionType = optionType;

        initComponents();

        pnlReasonContainer.setLayout(new BoxLayout(pnlReasonContainer, BoxLayout.Y_AXIS));

        Enum<?>[] options;
        if (optionType == OptionType.MORE_OPTIONS) {
            options = MoreOptions.values();
            lblTitle.setText("Options");
            lblAsk.setText("Please select one");
        } else if (optionType == OptionType.REPORT_REASON) {
            options = ReportReason.values();
            lblTitle.setText("Report");
            lblAsk.setText("Why are you reporting this post?");
        } else {
            options = new Enum<?>[0];
        }

        for (Enum<?> option : options) {
            MyLabel optionLabel = new MyLabel();
            if (option instanceof MoreOptions) {
                MoreOptions moreOption = (MoreOptions) option;
                optionLabel.setText(moreOption.getLabel());
            } else if (option instanceof ReportReason) {
                ReportReason reportReason = (ReportReason) option;
                optionLabel.setText(reportReason.getLabel());
            }
             
            optionLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(239, 239, 239)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            optionLabel.setMaximumSize(new java.awt.Dimension(400, 40));
            optionLabel.setMinimumSize(new java.awt.Dimension(400, 40));

            optionLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (optionType == OptionType.MORE_OPTIONS) {
                        optionSelected(option);
                        GlassPanePopup.closePopup("moreoptions");
                    } else if (optionType == OptionType.REPORT_REASON) {
                        reportReasonSelected((ReportReason) option);
                        GlassPanePopup.closePopup("report");
                    }
                }
            });
            pnlReasonContainer.add(optionLabel);
        }
    }

    /**
     * Sets the callback for capturing the selected report reason.
     * @param callback The callback to be set.
     */
    public void setReasonSelectedCallback(ReasonSelectedCallback callback) {
        this.reasonCallback = callback;
    }

    public void setOptionSelectedCallback(OptionSelectedCallback callback) {
        this.optionCallback = callback;
    }

    private void optionSelected(Enum<?> option) {
        selectedValue = option.ordinal();

        if (optionCallback != null) {
            optionCallback.onOptionSelected(selectedValue);
        }
    }

    private void reportReasonSelected(ReportReason reason) {
        int selectedValue = ((ReportReason) reason).getValue();

        if (reasonCallback != null) {
            reasonCallback.onReasonSelected(selectedValue);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        lblTitle = new online.syncio.component.MyLabel();
        lblAsk = new online.syncio.component.MyLabel();
        pnlReasonContainer = new online.syncio.component.MyPanel();

        setLayout(new java.awt.BorderLayout());

        pnlContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlContainer.setRadius(20);
        pnlContainer.setLayout(new javax.swing.BoxLayout(pnlContainer, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Report");
        lblTitle.setFont(new java.awt.Font("SF Pro Display Bold", 0, 16)); // NOI18N
        lblTitle.setFontBold(2);
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setMaximumSize(new java.awt.Dimension(400, 40));
        lblTitle.setMinimumSize(new java.awt.Dimension(400, 40));
        lblTitle.setPreferredSize(new java.awt.Dimension(400, 40));
        pnlContainer.add(lblTitle);

        lblAsk.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(239, 239, 239)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        lblAsk.setText("Why are you reporting this post?");
        lblAsk.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        lblAsk.setMaximumSize(new java.awt.Dimension(400, 40));
        lblAsk.setMinimumSize(new java.awt.Dimension(400, 40));
        lblAsk.setPreferredSize(new java.awt.Dimension(400, 40));
        pnlContainer.add(lblAsk);

        pnlReasonContainer.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlReasonContainerLayout = new javax.swing.GroupLayout(pnlReasonContainer);
        pnlReasonContainer.setLayout(pnlReasonContainerLayout);
        pnlReasonContainerLayout.setHorizontalGroup(
            pnlReasonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlReasonContainerLayout.setVerticalGroup(
            pnlReasonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        pnlContainer.add(pnlReasonContainer);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyLabel lblAsk;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlReasonContainer;
    // End of variables declaration//GEN-END:variables
}
