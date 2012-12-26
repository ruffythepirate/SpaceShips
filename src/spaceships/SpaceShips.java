/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships;

import java.awt.event.KeyEvent;
import spaceships.logic.*;
import spaceships.view.SpaceShipPanel;
import spaceships.view.SpaceShipsGameContainer;

/**
 *
 * @author johancarlsson
 */
public class SpaceShips implements Runnable {

    private Thread mainGameThread;
    private boolean gameIsRunning = true;
    GraphicsWorld graphicsWorld;
    PhysicalWorld physicalWorld;
    ControlManager controlManager;
    SpaceShipPanel gamePanel;
    SpaceShip spaceShip;
    Astroid astroid;
    long framePeriod = 20;

    public SpaceShips() {

        controlManager = new ControlManager();
        initializeControls();

        graphicsWorld = new GraphicsWorld();

        
        spaceShip = new SpaceShip();
        astroid = Astroid.createRandomAstroid(15, 15.0f, 2.0f, 0.05f);
        
        graphicsWorld.addGraphicItem(spaceShip);
        graphicsWorld.addGraphicItem(astroid);

        physicalWorld = new PhysicalWorld();

        //mainGameThread
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SpaceShips spaceShips = new SpaceShips();

        spaceShips.start();
    }

    public void start() {
        mainGameThread = new Thread(this);
        gameIsRunning = true;
        final SpaceShipsGameContainer container = new SpaceShipsGameContainer();
        gamePanel = container.getSpaceShipPanel();
        gamePanel.addKeyListener(controlManager);
        container.addKeyListener(controlManager);
        container.initializeWorld(graphicsWorld);

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                container.setVisible(true);
            }
        });

        mainGameThread.start();

    }

    @Override
    public void run() {
        long endTime;
        long startTime;

        while (gameIsRunning) {

            startTime = System.currentTimeMillis();

            doControls();

            astroid.move();
            spaceShip.move();
            spaceShip.wrapPosition(gamePanel.getWidth(), gamePanel.getHeight());
            astroid.wrapPosition(gamePanel.getWidth(), gamePanel.getHeight());
            spaceShip.applyFriction(0.95f);
            gamePanel.repaint();

            try {
                endTime = System.currentTimeMillis();
                // don’t sleep for a negative amount of time
                if (framePeriod - (endTime - startTime) > 0) {
                    Thread.sleep(framePeriod - (endTime - startTime));
                }
            } catch (InterruptedException ex) {
            }
        }

    }

    private void doControls() {
        for (KeyCommand command : controlManager.getAllCommands()) {
            if (command.isActive()) {
                handleCommand(command);
            }
        }
    }

    private void handleCommand(KeyCommand command) {
        if (command.isActive()) {
            switch (command.getActionId()) {
                case 100:
                    spaceShip.accelerate(.4f);
                    break;
                case 101:
                    spaceShip.turn(-0.5);
                    break;
                case 102:
                    spaceShip.turn(0.5);
                    break;
                case 103:
                    break;
                //Fire a bullet.
                case 110:
                    //spaceShip.createBullet();
                    break;
            }
        }
    }

    private void initializeControls() {
        controlManager.addKeyCommand(KeyEvent.VK_UP, 100);
        controlManager.addKeyCommand(KeyEvent.VK_LEFT, 101);
        controlManager.addKeyCommand(KeyEvent.VK_RIGHT, 102);
        controlManager.addKeyCommand(KeyEvent.VK_SPACE, 103);
    }
}
