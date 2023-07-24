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

public final class Main extends javax.swing.JFrame {

    private MongoDatabase database;
    private User currentUser;

    static ConnectionPanel[] connectionPanelList;
    static MyButton[] btnMenuList;
    private CreateNewPost popupCreate;
    public static String prevTab, curTab;
    public MyFont myFont = new MyFont();

    public Main() {
        setUndecorated(true);
        initComponents();
        GlassPanePopup.install(this);
        setBackground(new Color(0f, 0f, 0f, 0f));
        setLocationRelativeTo(null);
    }

    public void addComponents() {
        connectionPanelList = new ConnectionPanel[]{new Home(), new Search(), new Message(), new Notification(),
            new Profile(), new OtherUserProfile(), new EditProfile()};

        pnlTabContent.setLayout(new CardLayout());

        for (ConnectionPanel pnl : connectionPanelList) {
            String pnlName = pnl.getClass().getSimpleName().trim().toLowerCase();
            pnlTabContent.add(pnl, pnlName);
            pnl.setConnection(this);
        }

        btnMenuList = new MyButton[]{btnHome, btnSearch, btnMessage, btnNotification, btnCreate, btnProfile};

        for (MyButton btn : btnMenuList) {
            btn.addActionListener((ActionEvent e) -> {
                MyButton btn1 = (MyButton) e.getSource();
                String name1 = btn1.getName().trim();
                if ((name1.equals("message") || (name1.equals("notification")) || (name1.equals("profile")) || (name1.equals("create"))) && LoggedInUser.getCurrentUser() == null) {
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

        if (!newTab.equals("create")) {
            CardLayout c = (CardLayout) pnlTabContent.getLayout();
            c.show(pnlTabContent, curTab);
        }
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

        if (!newTab.equals("create")) {
            CardLayout c = (CardLayout) pnlTabContent.getLayout();
            c.show(pnlTabContent, curTab);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new online.syncio.component.MyPanel();
        pnlTitleBar = new online.syncio.component.WindowTitleBar();
        pnlLeftMenu = new online.syncio.component.MyPanel();
        btnHome = new online.syncio.component.MyButton();
        btnSearch = new online.syncio.component.MyButton();
        btnMessage = new online.syncio.component.MyButton();
        btnNotification = new online.syncio.component.MyButton();
        btnCreate = new online.syncio.component.MyButton();
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

        btnHome.setBackground(null);
        btnHome.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 15, 5, 0));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/home_28px.png"))); // NOI18N
        btnHome.setText(" Home");
        btnHome.setBorderThickness(0);
        btnHome.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.setMaximumSize(new java.awt.Dimension(200, 60));
        btnHome.setMinimumSize(new java.awt.Dimension(200, 60));
        btnHome.setName("home"); // NOI18N
        btnHome.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnHome);

        btnSearch.setBackground(null);
        btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/search_28px.png"))); // NOI18N
        btnSearch.setText(" Search");
        btnSearch.setBorderThickness(0);
        btnSearch.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSearch.setMaximumSize(new java.awt.Dimension(200, 50));
        btnSearch.setMinimumSize(new java.awt.Dimension(200, 50));
        btnSearch.setName("search"); // NOI18N
        btnSearch.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnSearch);

        btnMessage.setBackground(null);
        btnMessage.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/message_28px.png"))); // NOI18N
        btnMessage.setText(" Messages");
        btnMessage.setBorderThickness(0);
        btnMessage.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnMessage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMessage.setMaximumSize(new java.awt.Dimension(200, 50));
        btnMessage.setMinimumSize(new java.awt.Dimension(200, 50));
        btnMessage.setName("message"); // NOI18N
        btnMessage.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnMessage);

        btnNotification.setBackground(null);
        btnNotification.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnNotification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/notification_28px.png"))); // NOI18N
        btnNotification.setText(" Notifications");
        btnNotification.setBorderThickness(0);
        btnNotification.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnNotification.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNotification.setMaximumSize(new java.awt.Dimension(200, 50));
        btnNotification.setMinimumSize(new java.awt.Dimension(200, 50));
        btnNotification.setName("notification"); // NOI18N
        btnNotification.setPreferredSize(new java.awt.Dimension(200, 50));
        pnlLeftMenu.add(btnNotification);

        btnCreate.setBackground(null);
        btnCreate.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 15, 5, 0));
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/online/syncio/resources/images/icons/create_28px.png"))); // NOI18N
        btnCreate.setText(" Create");
        btnCreate.setBorderThickness(0);
        btnCreate.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N
        btnCreate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCreate.setMaximumSize(new java.awt.Dimension(200, 50));
        btnCreate.setMinimumSize(new java.awt.Dimension(200, 50));
        btnCreate.setName("create"); // NOI18N
        btnCreate.setPreferredSize(new java.awt.Dimension(200, 50));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        pnlLeftMenu.add(btnCreate);

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
        btnHome.doClick();
    }//GEN-LAST:event_formWindowOpened

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (LoggedInUser.getCurrentUser() != null) {
            GlassPanePopup.showPopup(new CreateNewPost(this), "createnewpost");
            GlassPanePopup.showPopup(popupCreate, "createnewpost");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
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
    private online.syncio.component.MyButton btnCreate;
    private online.syncio.component.MyButton btnHome;
    private online.syncio.component.MyButton btnMessage;
    private online.syncio.component.MyButton btnNotification;
    private online.syncio.component.MyButton btnProfile;
    private online.syncio.component.MyButton btnSearch;
    private online.syncio.component.MyPanel pnlLeftMenu;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.MyPanel pnlTabContent;
    private online.syncio.component.WindowTitleBar pnlTitleBar;
    // End of variables declaration//GEN-END:variables
}
