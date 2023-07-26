package online.syncio.view;

import java.awt.Color;
import online.syncio.component.ConnectionPanel;
import online.syncio.controller.ManagementUserController;

public class ManagementUser extends ConnectionPanel {
    private static Main main;
    private ManagementUserController controller;

    public ManagementUser() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));
        
        this.controller = new ManagementUserController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new online.syncio.component.MyPanel();
        myPanel1 = new online.syncio.component.MyPanel();
        pnlTitle = new online.syncio.component.MyPanel();
        txtSearch = new online.syncio.component.MyTextField();
        btnSearch = new online.syncio.component.MyButton();
        lblTitle1 = new online.syncio.component.MyLabel();
        pnlMain = new online.syncio.component.MyPanel();
        lblTitle = new online.syncio.component.MyLabel();
        lblTitle2 = new online.syncio.component.MyLabel();
        lblTitle3 = new online.syncio.component.MyLabel();
        lblTitle4 = new online.syncio.component.MyLabel();
        lblTitle5 = new online.syncio.component.MyLabel();
        txtSearch1 = new online.syncio.component.MyTextField();

        setLayout(new java.awt.BorderLayout());

        pnlContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        myPanel1.setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(255, 255, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTitle.setPreferredSize(new java.awt.Dimension(0, 40));

        txtSearch.setBackground(new java.awt.Color(239, 239, 239));
        txtSearch.setBorderColor(new java.awt.Color(239, 239, 239));

        btnSearch.setBackground(new java.awt.Color(0, 149, 246));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setBorderColor(new java.awt.Color(0, 149, 246));
        btnSearch.setRadius(5);

        lblTitle1.setText("User Management");

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 665, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitleLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        myPanel1.add(pnlTitle, java.awt.BorderLayout.PAGE_START);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setText("User ID");

        lblTitle2.setText("Email");

        lblTitle3.setText("Username");

        lblTitle4.setText("Date Created");

        lblTitle5.setText("Role");

        txtSearch1.setBackground(new java.awt.Color(239, 239, 239));
        txtSearch1.setText("asdasdsadsa");
        txtSearch1.setBorderColor(new java.awt.Color(239, 239, 239));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(806, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        myPanel1.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlContainer.add(myPanel1, java.awt.BorderLayout.CENTER);

        add(pnlContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnSearch;
    private online.syncio.component.MyLabel lblTitle;
    private online.syncio.component.MyLabel lblTitle1;
    private online.syncio.component.MyLabel lblTitle2;
    private online.syncio.component.MyLabel lblTitle3;
    private online.syncio.component.MyLabel lblTitle4;
    private online.syncio.component.MyLabel lblTitle5;
    private online.syncio.component.MyPanel myPanel1;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlTitle;
    private online.syncio.component.MyTextField txtSearch;
    private online.syncio.component.MyTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
