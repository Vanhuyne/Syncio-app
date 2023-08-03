package online.syncio.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.geom.RoundRectangle2D;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyButton;
import online.syncio.component.MyDialog;
import online.syncio.component.MyPanel;
import online.syncio.dao.MongoDBConnect;
import online.syncio.dao.UserDAO;
import online.syncio.model.LoggedInUser;
import online.syncio.resources.fonts.MyFont;
import online.syncio.utils.ActionHelper;
import online.syncio.utils.OtherHelper;

public final class Main extends javax.swing.JFrame {

    private static Main instance;
    static JPanel[] panelList;
    static MyButton[] btnMenuList;
    public static String prevTab, curTab;
    public MyFont myFont = new MyFont();
    public Profile profile;
    private MessagePanel messagePanel;

    private UserDAO userDAO;

    public Main() {
        instance = this;

        this.userDAO = MongoDBConnect.getUserDAO();

        setUndecorated(true);
        initComponents();
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20)); //rounded frame
        setLocationRelativeTo(null);
        GlassPanePopup.install(this);

        pnlSearch.setVisible(false);

        if(OtherHelper.getSessionValue("LOGGED_IN_USER") != null) {
            // da login = remember me
            LoggedInUser.setCurrentUser(userDAO.getByID(OtherHelper.getSessionValue("LOGGED_IN_USER")));
            this.profile = new Profile(LoggedInUser.getCurrentUser());
        }
        else if(LoggedInUser.getCurrentUser() != null) {
            //da login = username password
            this.profile = new Profile(LoggedInUser.getCurrentUser());
        }
        else if(LoggedInUser.getCurrentUser() == null) {
            //chua login
            btnProfile.setText("Log in");
            this.profile = new Profile(null);
        }

        messagePanel = new MessagePanel();

        addComponents();
    }
    
    //rounded frame
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
        super.paint(g);
    }
    
    

    public void addComponents() {
        panelList = new JPanel[]{new Home(), messagePanel, new Notification(), profile, new EditProfile()};

        pnlTabContent.setLayout(new CardLayout());

        for (JPanel pnl : panelList) {
            String pnlName = pnl.getClass().getSimpleName().trim().toLowerCase();

            if (pnlName.equalsIgnoreCase("messagepanel")) {
                pnlName = "message";
            }

            System.out.println(pnlName);

            pnlTabContent.add(pnl, pnlName);
        }

        btnMenuList = new MyButton[]{btnHome, btnSearch, btnMessage, btnNotification, btnCreate, btnProfile};

        for (MyButton btn : btnMenuList) {

            btn.addActionListener((ActionEvent e) -> {
                MyButton btn1 = (MyButton) e.getSource();
                String name1 = btn1.getName().trim();
                
                if ((name1.equals("message") || (name1.equals("notification")) || (name1.equals("profile")) || (name1.equals("create"))) && LoggedInUser.getCurrentUser() == null) {
                    //chua login
                    dispose();
                    new Login().setVisible(true);
                    if(!name1.equals("profile")) {
                        //bntProfile is Login, user click on to login >< show popup
                        GlassPanePopup.showPopup(new MyDialog("Login Required", "To access this feature, please log in to your account."), "dialog");
                    }
                }
                else {
                    if(name1.equals("profile")) {
                        this.profile.loadProfile(LoggedInUser.getCurrentUser());
                    }
                    
                    showTab(name1, btn1);
                }

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
            if (b.getName().trim().equalsIgnoreCase(curTab) && !curTab.equals(newTab)) {
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
        pnlContainer = new online.syncio.component.MyPanel();
        pnlSearch = new online.syncio.view.SearchUserPanel();
        pnlTabContent = new online.syncio.component.MyPanel();
        pnlLeftMenu = new online.syncio.component.MyPanel();
        btnHome = new online.syncio.component.MyButton();
        btnSearch = new online.syncio.component.MyButton();
        btnMessage = new online.syncio.component.MyButton();
        btnNotification = new online.syncio.component.MyButton();
        btnCreate = new online.syncio.component.MyButton();
        btnProfile = new online.syncio.component.MyButton();

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
        pnlMain.add(pnlTitleBar, java.awt.BorderLayout.NORTH);

        pnlContainer.setMaximumSize(new java.awt.Dimension(1080, 679));
        pnlContainer.setMinimumSize(new java.awt.Dimension(1080, 679));
        pnlContainer.setPreferredSize(new java.awt.Dimension(1080, 679));
        pnlContainer.setVerifyInputWhenFocusTarget(false);
        pnlContainer.setLayout(new javax.swing.OverlayLayout(pnlContainer));

        pnlSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 540));
        pnlSearch.setAlignmentX(1.0F);
        pnlSearch.setMaximumSize(new java.awt.Dimension(540, 679));
        pnlSearch.setMinimumSize(new java.awt.Dimension(540, 679));
        pnlSearch.setPreferredSize(new java.awt.Dimension(540, 679));
        pnlContainer.add(pnlSearch);

        pnlTabContent.setBackground(new Color(0f, 0f, 0f, 0f));
        pnlTabContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(219, 219, 219)));
        pnlTabContent.setMaximumSize(new java.awt.Dimension(1080, 679));
        pnlTabContent.setMinimumSize(new java.awt.Dimension(1080, 679));
        pnlTabContent.setPreferredSize(new java.awt.Dimension(1080, 679));
        pnlTabContent.setRoundBottomRight(20);
        pnlTabContent.setLayout(new java.awt.CardLayout());
        pnlContainer.add(pnlTabContent);

        pnlMain.add(pnlContainer, java.awt.BorderLayout.CENTER);

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
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
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

        pnlMain.add(pnlLeftMenu, java.awt.BorderLayout.WEST);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        btnHome.doClick();
    }//GEN-LAST:event_formWindowOpened

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (LoggedInUser.getCurrentUser() != null) {
            GlassPanePopup.showPopup(new CreateNewPost(this), "createnewpost");
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        pnlSearch.setVisible(!pnlSearch.isVisible());
    }//GEN-LAST:event_btnSearchActionPerformed

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

        ActionHelper.registerShutdownHook(); // Register the shutdown hook

        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    public static Main getInstance() {
        return instance;
    }

    public MyPanel getPnlTabContent() {
        return pnlTabContent;
    }

    public SearchUserPanel getPnlSearch() {
        return pnlSearch;
    }

    public void setPnlSearch(SearchUserPanel pnlSearch) {
        this.pnlSearch = pnlSearch;
    }

    public MyPanel getMyPanel1() {
        return pnlContainer;
    }

    public void setMyPanel1(MyPanel myPanel1) {
        this.pnlContainer = myPanel1;
    }

    public MyButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(MyButton btnSearch) {
        this.btnSearch = btnSearch;
    }

    public MessagePanel getMessagePanel() {
        return messagePanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnCreate;
    private online.syncio.component.MyButton btnHome;
    private online.syncio.component.MyButton btnMessage;
    private online.syncio.component.MyButton btnNotification;
    private online.syncio.component.MyButton btnProfile;
    private online.syncio.component.MyButton btnSearch;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlLeftMenu;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.view.SearchUserPanel pnlSearch;
    private online.syncio.component.MyPanel pnlTabContent;
    private online.syncio.component.WindowTitleBar pnlTitleBar;
    // End of variables declaration//GEN-END:variables
}
