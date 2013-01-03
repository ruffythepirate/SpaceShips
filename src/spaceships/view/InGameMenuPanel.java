/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author Ruffy
 */
public class InGameMenuPanel extends javax.swing.JPanel {

    public static final int MENU_ITEM_CONTINUE = 0;
    public static final int MENU_ITEM_QUIT = 1;
    
    private JLabel[] menuItems;
    
    private int selectedMenuItem = 0;
    
    /**
     * Creates new form InGameMenuPanel
     */
    public InGameMenuPanel() {
        initComponents();

    }

    public InGameMenuPanel(String[] menuItemStrings, IInGameMenuContainer form) {
        initComponents();
        initializeNonEditorThings(form);
        initializeMenuItems(menuItemStrings);
    }
    
    public void selectBelowMenuItem() {
        selectedMenuItem++;
        if(getSelectedMenuItem() >= menuItems.length) {
            selectedMenuItem = 0;
        }
        updateDisplayedSelectedMenuItem();
    }
    
    public void selectAboveMenuItem() {
        selectedMenuItem--;
        if(getSelectedMenuItem() < 0) {
            selectedMenuItem = menuItems.length - 1;
        }
        updateDisplayedSelectedMenuItem();
    }

    private void updateDisplayedSelectedMenuItem() {
        for(int i = 0; i < menuItems.length; i++) {
            if(i == getSelectedMenuItem()) {
                menuItems[i].setForeground(Color.RED);
            } else {
                menuItems[i].setForeground(Color.BLACK);
            }
        }
    }
    
    private JLabel createMenuItem(String menuItem) {
        JLabel menuItemLabel = new JLabel(menuItem);
        menuItemLabel.setText(menuItem);
        return menuItemLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void initializeBorder() {
        Border panelBorder = new BevelBorder(BevelBorder.RAISED);

        setBorder(panelBorder);
    }

    private void initializeLayoutManager() {
        LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layoutManager);
    }

    
    
    private void initializeNonEditorThings(IInGameMenuContainer form) {
        initializeBorder();
        initializeLayoutManager();
        InGameMenuPresenter presenter = new InGameMenuPresenter(this, form);
        addKeyListener(presenter);
        
    }

    private void initializeMenuItems(String[] menuItemStrings) {
        menuItems = new JLabel[menuItemStrings.length];
        for (int i = 0; i < menuItems.length; i++) {
            JLabel menuItem = createMenuItem(menuItemStrings[i]);
            menuItems[i] = menuItem;
            add(menuItem);
        }
        
        updateDisplayedSelectedMenuItem();
    }

    /**
     * @return the selectedMenuItem
     */
    public int getSelectedMenuItem() {
        return selectedMenuItem;
    }
}
