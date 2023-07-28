package online.syncio.view;

import com.mongodb.client.MongoDatabase;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import online.syncio.component.ConnectionPanel;
import online.syncio.resources.fonts.MyFont;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyButton;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPanel;
import online.syncio.model.LoggedInUser;
import online.syncio.model.User;

public final class MainAdmin extends javax.swing.JFrame {

    private MongoDatabase database;
    private User currentUser;

    static ConnectionPanel[] connectionPanelList;
    static MyButton[] btnMenuList;
    public static String prevTab, curTab;
    public MyFont myFont = new MyFont();

    public MainAdmin() {
        setUndecorated(true);
        initComponents();
        GlassPanePopup.install(this);
        setBackground(new Color(0f, 0f, 0f, 0f));
        setLocationRelativeTo(null);
    }

    public void addComponents() {
        connectionPanelList = new ConnectionPanel[]{new ManagementDashboard(), new ManagementUser(), new ManagementHiddenPost(), new ManagementReportedPost(), new Profile(), new OtherUserProfile(), new EditProfile()};

        pnlTabContent.setLayout(new CardLayout());

        for (ConnectionPanel pnl : connectionPanelList) {
            String pnlName = pnl.getClass().getSimpleName().trim().toLowerCase();
            pnlTabContent.add(pnl, pnlName);
            pnl.setConnection(this);
        }

        btnMenuList = new MyButton[]{btnDashboard, btnUser, btnHiddenPost, btnReportedPost, btnProfile};

        for (MyButton btn : btnMenuList) {
            btn.addActionListener((ActionEvent e) -> {
                MyButton btn1 = (MyButton) e.getSource();
                String name1 = btn1.getName().trim();
                if (!LoggedInUser.isAdmin()) {
                    dispose();
                    new Login().setVisible(true);
                    GlassPanePopup.showPopup(new MyDialog("Login Required", "To access this feature, please log in to your account."), "dialog");
                    return;
                }
                showTab(name1, btn1);
            });
        }
    }

    public void showTab(String newTab) {
        for (MyButton b : btnMenuList) {
            if (b.getName().trim().equalsIgnoreCase(curTab)) {
                b.setFontBold(1);
                break;
            }

            if (b.getName().trim().equalsIgnoreCase(newTab)) {
                b.setFontBold(2);
            }
        }

        prevTab = curTab;
        curTab = newTab;

        CardLayout c = (CardLayout) pnlTabContent.getLayout();
        c.show(pnlTabContent, curTab);
    }

    public void showTab(String newTab, MyButton btn) {
        btn.setFontBold(2);
        for (MyButton b : btnMenuList) {
            if (b.getName().trim().equalsIgnoreCase(curTab)) {
                b.setFontBold(1);
                break;
            }
        }

        prevTab = curTab;
        curTab = newTab;

        CardLayout c = (CardLayout) pnlTabContent.getLayout();
        c.show(pnlTabContent, curTab);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        pnlTitleBar = new online.syncio.component.WindowTitleBar();
        pnlLeftMenu = new online.syncio.component.MyPanel();
        btnDashboard = new online.syncio.component.MyButton();
        btnUser = new online.syncio.component.MyButton();
        btnHiddenPost = new online.syncio.component.MyButton();
        btnReportedPost = new online.syncio.component.MyButton();
        btnProfile = new online.syncio.component.MyButton();
        pnlTabContent = new online.syncio.component.MyPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlMain.setBackground(null);
        pnlMain.setRadius(20);
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);
        pnlMain.setRoundTopLeft(20);
        pnlMain.setRoundTopRight(20);
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlTitleBar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlTitleBar.setFrame(this);
        pnlMain.add(pnlTitleBar, java.awt.BorderLayout.PAGE_START);

        pnlLeftMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeftMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(219, 219, 219)));
        pnlLeftMenu.setMaximumSize(new java.awt.Dimension(200, 679));
        pnlLeftMenu.setMinimumSize(new java.awt.Dimension(200, 679));
        pnlLeftMenu.setPreferredSize(new java.awt.Dimension(200, 679));
        pnlLeftMenu.setRoundBottomLeft(20);
        pnlLeftMenu.setLayout(new javax.swing.BoxLayout(pnlLeftMenu, javax.swing.BoxLayout.Y_AXIS));

        btnDashboard.setBackground(null);
        btnDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 15, 5, 0));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/dashboard_28px.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderThickness(0);
        btnDashboard.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setMaximumSize(new java.awt.Dimension(200, 60));
        btnDashboard.setMinimumSize(new java.awt.Dimension(200, 60));
        btnDashboard.setName("managementdashboard"); // NOI18N
        btnDashboard.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnDashboard);

        btnUser.setBackground(null);
        btnUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/management-user_28px.png"))); // NOI18N
        btnUser.setText("User");
        btnUser.setBorderThickness(0);
        btnUser.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUser.setMaximumSize(new java.awt.Dimension(200, 50));
        btnUser.setMinimumSize(new java.awt.Dimension(200, 50));
        btnUser.setName("managementuser"); // NOI18N
        btnUser.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnUser);

        btnHiddenPost.setBackground(null);
        btnHiddenPost.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnHiddenPost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/hidden-post_28px.png"))); // NOI18N
        btnHiddenPost.setText("Hidden Post");
        btnHiddenPost.setBorderThickness(0);
        btnHiddenPost.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnHiddenPost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHiddenPost.setMaximumSize(new java.awt.Dimension(200, 50));
        btnHiddenPost.setMinimumSize(new java.awt.Dimension(200, 50));
        btnHiddenPost.setName("managementhiddenpost"); // NOI18N
        btnHiddenPost.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnHiddenPost);

        btnReportedPost.setBackground(null);
        btnReportedPost.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnReportedPost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/reported-post_28px.png"))); // NOI18N
        btnReportedPost.setText("Reported Post");
        btnReportedPost.setBorderThickness(0);
        btnReportedPost.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnReportedPost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReportedPost.setMaximumSize(new java.awt.Dimension(200, 50));
        btnReportedPost.setMinimumSize(new java.awt.Dimension(200, 50));
        btnReportedPost.setName("managementreportedpost"); // NOI18N
        btnReportedPost.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnReportedPost);

        btnProfile.setBackground(null);
        btnProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/profile_28px.png"))); // NOI18N
        btnProfile.setText(" Profile");
        btnProfile.setBorderThickness(0);
        btnProfile.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProfile.setMaximumSize(new java.awt.Dimension(200, 50));
        btnProfile.setMinimumSize(new java.awt.Dimension(200, 50));
        btnProfile.setName("profile"); // NOI18N
        btnProfile.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnProfile);

        pnlMain.add(pnlLeftMenu, java.awt.BorderLayout.LINE_START);

        pnlTabContent.setBackground(null);
        pnlTabContent.setRoundBottomRight(20);
        pnlTabContent.setLayout(new java.awt.CardLayout());
        pnlMain.add(pnlTabContent, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        btnDashboard.doClick();
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new MainAdmin().setVisible(true);
        });
    }

    public MyPanel getPnlTabContent() {
        return pnlTabContent;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setConnection(MongoDatabase database, User user) {
        this.database = database;
        this.currentUser = user;

        addComponents();
    }

    public User getCurrentUser() {
        return currentUser;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnDashboard;
    private online.syncio.component.MyButton btnHiddenPost;
    private online.syncio.component.MyButton btnProfile;
    private online.syncio.component.MyButton btnReportedPost;
    private online.syncio.component.MyButton btnUser;
    private online.syncio.component.MyPanel pnlLeftMenu;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlTabContent;
    private online.syncio.component.WindowTitleBar pnlTitleBar;
    // End of variables declaration//GEN-END:variables
}
