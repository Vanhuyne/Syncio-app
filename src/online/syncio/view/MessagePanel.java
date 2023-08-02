package online.syncio.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import online.syncio.component.message.ChatArea;
import online.syncio.model.User;

public class MessagePanel extends JPanel {

    private CardLayout cardLayout;

    public MessagePanel() {
        initComponents();
        setBackground(new Color(0f, 0f, 0f, 0f));

        cardLayout = (CardLayout) getLayout();
    }

    public void openMessage(User messagingUser) {
        Component[] componentList = getComponents();

        boolean found = false;

        try {
            for (Component c : componentList) {
                if (c instanceof ChatArea) {
                    if (c.getName().equalsIgnoreCase(messagingUser.getUsername().trim())) {
                        cardLayout.show(this, messagingUser.getUsername());
                        found = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            ChatArea ca = new ChatArea();
            ca.setMessagingUser(messagingUser);
            add(ca, ca.getName().trim());

            cardLayout.show(this, messagingUser.getUsername());

            found = true;
        }

        if (!found) {
            ChatArea ca = new ChatArea();
            ca.setMessagingUser(messagingUser);
            add(ca, ca.getName().trim());

            cardLayout.show(this, messagingUser.getUsername());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
