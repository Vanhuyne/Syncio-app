package online.syncio.view.user;

import java.awt.Color;
import javax.swing.JPanel;
import online.syncio.controller.user.NotificationController;

/**
 * Represents a user interface for displaying notifications and managing user interactions.
 * This class provides functionality for showing a list of notifications.
 */
public class NotificationsPanel extends JPanel {

    private NotificationController controller;

    /**
     * Initializes the notifications panel UI components and sets up necessary configurations.
     */
    public NotificationsPanel() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        setOpaque(true);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        controller = new NotificationController(this);
    }

    /**
     * Adds the last notification to the notifications panel.
     */
    public void addLastNotification() {

    }

    /**
     * Returns the controller responsible for managing notification interactions.
     *
     * @return The NotificationController instance associated with this notifications panel.
     */
    public NotificationController getController() {
        return controller;
    }

    /**
     * Returns the panel used for displaying notification results.
     *
     * @return The JPanel instance representing the notification result area.
     */
    public JPanel getPnlResult() {
        return pnlResult;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        scrollPane = new online.syncio.component.MyScrollPane();
        pnlResult = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        setMaximumSize(new java.awt.Dimension(314, 679));
        setMinimumSize(new java.awt.Dimension(314, 679));
        setPreferredSize(new java.awt.Dimension(314, 679));

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(219, 219, 219)));
        pnlMain.setMaximumSize(new java.awt.Dimension(314, 679));
        pnlMain.setMinimumSize(new java.awt.Dimension(314, 679));

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);

        pnlResult.setBackground(new java.awt.Color(255, 255, 255));
        pnlResult.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));
        pnlResult.setLayout(new javax.swing.BoxLayout(pnlResult, javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setViewportView(pnlResult);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlResult;
    private online.syncio.component.MyScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
