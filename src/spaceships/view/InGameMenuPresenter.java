/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import spaceships.SpaceShips;

/**
 *
 * @author Ruffy
 */
public class InGameMenuPresenter implements KeyListener {

    private InGameMenuPanel m_View;
    private IInGameMenuContainer m_Container;
    private boolean m_EscIsPressed;

    public InGameMenuPresenter(InGameMenuPanel panel, IInGameMenuContainer spaceShipsForm) {
        m_View = panel;
        m_Container = spaceShipsForm;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                m_View.selectAboveMenuItem();
                break;
            case KeyEvent.VK_DOWN:
                m_View.selectBelowMenuItem();
                break;
            case KeyEvent.VK_ENTER:
                int selectedItem = m_View.getSelectedMenuItem();
                executeMenuItem(selectedItem);
                break;
            case KeyEvent.VK_ESCAPE:
                m_EscIsPressed = true;
                break;
        }
    }

    private void executeMenuItem(int menuItem) {
        switch (menuItem) {
            case InGameMenuPanel.MENU_ITEM_CONTINUE:
                SpaceShips.getInstance().closeInGameMenu();
                break;
            case InGameMenuPanel.MENU_ITEM_QUIT:
                SpaceShips.getInstance().quit();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                if (m_EscIsPressed) {
                    m_EscIsPressed = false;
                    SpaceShips.getInstance().closeInGameMenu();
                }
                break;
        }
    }
}
