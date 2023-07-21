package online.syncio.view;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Sorts.descending;
import com.mongodb.client.result.InsertOneResult;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import online.syncio.component.GlassPanePopup;
import online.syncio.component.MyDialog;
import online.syncio.component.MyTable;
import online.syncio.dao.MongoDBConnect;
import online.syncio.utils.TimeHelper;
import org.bson.Document;

public class ChamCong extends javax.swing.JFrame {
    
    private static final String SESSION_MEMBER_KEY = "user";
    String member;
    
    DefaultTableModel model;
    MyTable myTable;
    private MongoDatabase database = MongoDBConnect.getDatabase();

    public ChamCong() {
        setUndecorated(true);
        initComponents();
        GlassPanePopup.install(this);
        setBackground(new Color(0f, 0f, 0f, 0f));
        setLocationRelativeTo(null);
        
        //member
        cboMember.addItem("Duong");
        cboMember.addItem("Huy");
        cboMember.addItem("Sanh");
        cboMember.addItem("Thuan");

        member = getSessionValue();
        cboMember.setSelectedItem(member);
        
        
        //table
        //tao model
        myTable = tblTask.getTable();
        model = new DefaultTableModel();

        // Set the table model to the tblAlbum table
        myTable.setModel(model);
        
        //disable table editing
        myTable.setDefaultEditor(Object.class, null); 
        
        //table header
        String [] colNames = {"DateTime", "Type", "Member", "Description"};
        model.setColumnIdentifiers(colNames);

        //column width
        myTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        myTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        myTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        myTable.getColumnModel().getColumn(3).setPreferredWidth(800);
        
        //data
        fillToTable();
        txtTask.requestFocus();
        
        
        // When the Enter key is pressed, click on the btnLogin.
        KeyListener enterKeyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAdd.doClick();
                }
            }
        };
        txtTask.addKeyListener(enterKeyListener);
        
        
        //Double click on Table
        myTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                int row = myTable.getSelectedRow();
                if (mouseEvent.getClickCount() == 2 && myTable.getSelectedRow() != -1) {
                    String task = "";
                    for(int i = 0; i < myTable.getColumnCount(); i++) {
                        task += myTable.getValueAt(row, i) + "\n";
                    }

                    JOptionPane.showMessageDialog(null, "<html><body width='400'><p>" + task);
                }
            };
        });
    }
    
    
    
    public void fillToTable() {
        model.setRowCount(0); //clear rows in the table
        MongoCollection<Document> tasks = database.getCollection("task", Document.class);
        
        //them tung dong vao
        if(tasks != null) {
            for(Document task : tasks.find().sort(descending("dateTime"))) {
                model.addRow(new Object[] {task.get("dateTime"), task.get("type"), task.get("member"), task.get("task")});
            }
        }
    }
    
    
    
    public boolean addTask() {
        MongoCollection<Document> tasks = database.getCollection("task", Document.class);

        String type = "INFO";

        member = cboMember.getSelectedItem().toString();

        String task = txtTask.getText();
        
        try {
            Document t = new Document("dateTime", TimeHelper.getCurrentDateTime()).append("type", type).append("member", member).append("task", task);
            InsertOneResult result = tasks.insertOne(t);
            System.out.println("Inserted a Task with the following id: " + result.getInsertedId().asObjectId().getValue());

            return true;
        }
        catch(Exception ex) {
            System.out.println("Failed to insert into MongoDB: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
    
    
    private static void saveSessionValue(String value) {
        Preferences preferences = Preferences.userNodeForPackage(ChamCong.class);
        preferences.put(SESSION_MEMBER_KEY, value);
    }

    private static String getSessionValue() {
        Preferences preferences = Preferences.userNodeForPackage(ChamCong.class);
        return preferences.get(SESSION_MEMBER_KEY, null);
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
        pnlMain = new online.syncio.component.MyPanel();
        txtTask = new online.syncio.component.MyTextField();
        cboMember = new online.syncio.component.MyComboBox();
        tblTask = new online.syncio.component.MyScrollPaneWithTable();
        btnAdd = new online.syncio.component.MyButton();
        pnlTitleBar = new online.syncio.component.WindowTitleBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContainer.setBackground(null);
        pnlContainer.setRadius(20);
        pnlContainer.setRoundBottomLeft(20);
        pnlContainer.setRoundBottomRight(20);
        pnlContainer.setRoundTopLeft(20);
        pnlContainer.setRoundTopRight(20);
        pnlContainer.setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);

        txtTask.setFont(new java.awt.Font("SF Pro Display Medium", 0, 16)); // NOI18N

        cboMember.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMemberItemStateChanged(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 149, 246));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.setBorderColor(new java.awt.Color(0, 149, 246));
        btnAdd.setFontBold(2);
        btnAdd.setRadius(5);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblTask, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(cboMember, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtTask, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(tblTask, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTask, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMember, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pnlContainer.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlTitleBar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        pnlContainer.add(pnlTitleBar, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnlContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //validate
        if(txtTask.getText().trim().isEmpty()) return;
        
        //add
        if(addTask()) {
            txtTask.setText("");
            fillToTable();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cboMemberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMemberItemStateChanged
        if(this.isVisible()) {
            saveSessionValue(cboMember.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cboMemberItemStateChanged

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChamCong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChamCong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private online.syncio.component.MyButton btnAdd;
    private online.syncio.component.MyComboBox cboMember;
    private online.syncio.component.MyPanel pnlContainer;
    private online.syncio.component.MyPanel pnlMain;
    private online.syncio.component.WindowTitleBar pnlTitleBar;
    private online.syncio.component.MyScrollPaneWithTable tblTask;
    private online.syncio.component.MyTextField txtTask;
    // End of variables declaration//GEN-END:variables
}
