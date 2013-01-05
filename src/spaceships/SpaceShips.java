/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import spaceships.logic.*;
import spaceships.view.SpaceShipPanel;
import spaceships.view.SpaceShipsForm;

/**
 *
 * @author johancarlsson
 */
public class SpaceShips implements Runnable {

    private static SpaceShips s_Instance;
    private SpaceShipsForm m_MainForm;
    private Thread mainGameThread;
    private boolean gameIsRunning = true;
    GraphicsWorld graphicsWorld;
    PhysicalWorld physicalWorld;
    ControlManager controlManager;
    SpaceShipPanel gamePanel;
    long framePeriod = 20;
    //World objects.
    SpaceShip spaceShip;
    List<Astroid> astroids;
    List<Bullet> bullets;
    List<Bullet> bulletsToRemove;
    List<Explosion> explosions;
    List<Explosion> explosionsToRemove;
    private boolean m_Paused;

    public static SpaceShips getInstance() {
        if (s_Instance == null) {
            s_Instance = new SpaceShips();
        }
        return s_Instance;
    }

    private SpaceShips() {
        bullets = Collections.synchronizedList(new ArrayList<Bullet>());
        bulletsToRemove = new ArrayList<Bullet>();
        astroids = Collections.synchronizedList(new ArrayList<Astroid>());
        explosions = new ArrayList<Explosion>();
        explosionsToRemove = new ArrayList<Explosion>();

        controlManager = new ControlManager();
        initializeControls();

        graphicsWorld = new GraphicsWorld();


        spaceShip = new SpaceShip();

        graphicsWorld.addGraphicItem(spaceShip);

        physicalWorld = new PhysicalWorld();

        //mainGameThread
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SpaceShips spaceShips = getInstance();

        spaceShips.start();
    }

    public void start() {
        mainGameThread = new Thread(this);
        gameIsRunning = true;
        m_MainForm = new SpaceShipsForm();
        gamePanel = m_MainForm.getSpaceShipPanel();
        gamePanel.addKeyListener(controlManager);
        m_MainForm.addKeyListener(controlManager);
        m_MainForm.initializeWorld(graphicsWorld);

        addAstroidToWorld(Astroid.createRandomAstroid(gamePanel.getWidth() / 2,
                gamePanel.getHeight() / 2,
                5,
                2.0f,
                0.05f));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                m_MainForm.setVisible(true);
            }
        });

        mainGameThread.start();

    }

    public void addAstroidToWorld(Astroid astroidToAdd) {
        graphicsWorld.addGraphicItem(astroidToAdd);
        astroids.add(astroidToAdd);
    }

    public void removeAstroidFromWorld(Astroid astroidToRemove) {
        graphicsWorld.removeGraphicItem(astroidToRemove);
        astroids.remove(astroidToRemove);
    }

    public void removeBulletFromWorld(Bullet bulletToRemove) {
        graphicsWorld.removeGraphicItem(bulletToRemove);
        bullets.remove(bulletToRemove);
    }

    @Override
    public void run() {
        long endTime;
        long startTime;

        while (gameIsRunning) {
            startTime = System.currentTimeMillis();
            if (!isPaused()) {
                doControls();
                moveObjects();
                doCollissionDetection();
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
    }

    public void closeInGameMenu() {
        m_MainForm.hideInGameMenu();
        setPaused(false);
        m_MainForm.requestFocus();
    }

    /**
     * Quits the application.
     */
    public void quit() {
        System.exit(0);
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
                    if (spaceShip.isRealoaded()) {
                        Bullet newBullet = spaceShip.createBullet();
                        this.bullets.add(newBullet);
                        graphicsWorld.addGraphicItem(newBullet);
                    }
                    break;
                case 1000:
                    setPaused(true);
                    m_MainForm.showInGameMenu();
                    command.setActive(false);
                    break;
            }
        }
    }

    private void initializeControls() {
        controlManager.addKeyCommand(KeyEvent.VK_UP, 100);
        controlManager.addKeyCommand(KeyEvent.VK_LEFT, 101);
        controlManager.addKeyCommand(KeyEvent.VK_RIGHT, 102);
        controlManager.addKeyCommand(KeyEvent.VK_SPACE, 110);
        controlManager.addKeyCommand(KeyEvent.VK_ESCAPE, 1000);
    }

    /**
     * @return the m_Paused
     */
    public boolean isPaused() {
        return m_Paused;
    }

    /**
     * @param m_Paused the m_Paused to set
     */
    public void setPaused(boolean m_Paused) {
        this.m_Paused = m_Paused;
    }

    private void moveObjects() {
        moveAstroids();
        moveSpaceShips();
        moveExplosions();
        moveBullets();
    }

    private void doCollissionDetection() {
        doBulletCollissionDetection();
        doSpaceShipCollissionDetection();
    }

    private void doBulletCollissionDetection() {
        for (Bullet bullet : this.bullets) {
            //Check if the object collided with the astroid.
            for (Astroid astroid : astroids) {
                boolean bulletHitAstroid = bullet.doesCollide(astroid);
                if (bulletHitAstroid) {
                    astroid.explodeAstroid(bullet);
                    Explosion astroidExplosion = astroid.createExplosion();
                    explosions.add(astroidExplosion);
                    graphicsWorld.addGraphicItem(astroidExplosion);
                    bullet.setAlive(false);
                    bullet.getOwningShip().addScore(100);
                    break;
                }
            }
        }
    }

    private void doSpaceShipCollissionDetection() {
        if (spaceShip.isAlive()) {
            for (Astroid astroid : astroids) {
                boolean shipHitAstroid = spaceShip.intersects(astroid);
                if (shipHitAstroid) {
                    Explosion shipExplosion = spaceShip.createExplosion();
                    spaceShip.setAlive(false);
                    explosions.add(shipExplosion);
                    graphicsWorld.addGraphicItem(shipExplosion);
                    //Explode ship.                
                    graphicsWorld.removeGraphicItem(spaceShip);
                    gamePanel.setGameOver(true);
                    //Do game over if single player..

                    // Respawn ship if multiplayer.
                    break;
                }
            }
        }
    }

    private void moveAstroids() {
        for (Astroid astroid : astroids) {
            astroid.move();
            astroid.wrapPosition(gamePanel.getWidth(), gamePanel.getHeight());
        }
    }

    private void moveSpaceShips() {
        spaceShip.move();
        spaceShip.wrapPosition(gamePanel.getWidth(), gamePanel.getHeight());
        spaceShip.applyFriction(0.95f);
    }

    private void moveBullets() {
        for (Bullet bullet : this.bullets) {
            bullet.move();
            bullet.wrapPosition(gamePanel.getWidth(), gamePanel.getHeight());
            if (!bullet.isAlive()) {
                bulletsToRemove.add(bullet);
            }
        }


        for (Bullet bulletToRemove : bulletsToRemove) {
            removeBulletFromWorld(bulletToRemove);
        }
        bulletsToRemove.clear();
    }

    private void moveExplosions() {
        for (Explosion explosion : explosions) {
            explosion.update();
            if (!explosion.isAlive()) {
                explosionsToRemove.add(explosion);
            }
        }
        for (Explosion explosionToRemove : explosionsToRemove) {
            graphicsWorld.removeGraphicItem(explosionToRemove);
            explosions.remove(explosionToRemove);
        }
        explosionsToRemove.clear();
    }
}
