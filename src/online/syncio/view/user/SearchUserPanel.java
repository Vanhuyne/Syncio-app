package online.syncio.view.user;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import online.syncio.component.MyTextField;
import online.syncio.controller.user.SearchController;

/**
 * Represents a user interface for searching and displaying search results for users.
 */
public class SearchUserPanel extends JPanel {

    private SearchController controller;

    /**
     * Initializes a new instance of the SearchUserPanel class.
     */
    public SearchUserPanel() {
        controller = new SearchController(this);

        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        setOpaque(true);

        pnlResult.setLayout(new BoxLayout(pnlResult, BoxLayout.Y_AXIS));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    /**
     * Returns the panel containing the search results for users.
     *
     * @return The panel containing the search results.
     */
    public JPanel getPnlResult() {
        return pnlResult;
    }

    /**
     * Returns the text field used for searching.
     *
     * @return The search text field.
     */
    public MyTextField getTxtSearch() {
        return txtSearch;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        txtSearch = new online.syncio.component.MyTextField();
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

        txtSearch.setBackground(new java.awt.Color(239, 239, 239));
        txtSearch.setOpaque(true);
        txtSearch.setPlaceholder("Looking for someone?");
        txtSearch.setPreferredSize(new java.awt.Dimension(260, 45));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        scrollPane.setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);

        pnlResult.setBackground(new java.awt.Color(255, 255, 255));
        pnlResult.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(219, 219, 219)));

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(pnlResult);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 613, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                    .addContainerGap(90, Short.MAX_VALUE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
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

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        controller.find();
    }//GEN-LAST:event_txtSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlResult;
    private online.syncio.component.MyScrollPane scrollPane;
    private online.syncio.component.MyTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
