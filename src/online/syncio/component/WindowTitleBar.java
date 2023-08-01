package online.syncio.component;

import java.awt.Color;
import javax.swing.JFrame;
import online.syncio.dao.MongoDBConnect;

/**
 * The WindowTitleBar class represents a custom title bar for a JFrame window.
 * It provides buttons for closing, resizing, and minimizing the window.
 */
public class WindowTitleBar extends javax.swing.JPanel {

    private JFrame frame;
    
    /**
     * Constructs a new instance of the WindowTitleBar.
     */
    public WindowTitleBar() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
    }
    
    /**
     * Sets the JFrame associated with the title bar.
     * 
     * @param frame the JFrame to be associated with the title bar
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        btnLogo = new online.syncio.component.MyButton();
        btnMinimize = new online.syncio.component.MyButton();
        btnResize = new online.syncio.component.MyButton();
        btnClose = new online.syncio.component.MyButton();

        pnlMain.setRadius(0);
        pnlMain.setRoundTopLeft(20);
        pnlMain.setRoundTopRight(20);

        btnLogo.setBackground(new java.awt.Color(239, 239, 239));
        btnLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/logo_24px.png"))); // NOI18N
        btnLogo.setBorderThickness(0);

        btnMinimize.setBackground(new java.awt.Color(239, 239, 239));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/minimize_24px.png"))); // NOI18N
        btnMinimize.setBorderThickness(0);
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });

        btnResize.setBackground(new java.awt.Color(239, 239, 239));
        btnResize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/resize_24px.png"))); // NOI18N
        btnResize.setBorderThickness(0);

        btnClose.setBackground(new java.awt.Color(239, 239, 239));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/close_24px.png"))); // NOI18N
        btnClose.setBorderThickness(0);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1124, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // Add a shutdown hook to close the MongoDB connection before exiting
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            MongoDBConnect.close();
        }));
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        if (frame != null) {
            frame.setState(JFrame.ICONIFIED);
        }
    }//GEN-LAST:event_btnMinimizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnClose;
    private online.syncio.component.MyButton btnLogo;
    private online.syncio.component.MyButton btnMinimize;
    private online.syncio.component.MyButton btnResize;
    private online.syncio.component.MyPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
