/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Color;
import java.awt.Graphics;
import spaceships.logic.Bullet;

/**
 *
 * @author johancarlsson
 */
public class SpaceShip extends MovingObject {

    private SpaceShipTrail graphicEngineTrail;
    
    private int score = 0;
    
    private int reloadIterationsLeft = 0;    
    private boolean reloaded = true;
    private int reloadIterationsRequired = 20;
    
    private boolean alive = true;
    
    public SpaceShip() {
        super();
        initializeOriginalShape();

        graphicEngineTrail = new SpaceShipTrail();
        x = y = 50;
        initializeShapeRadius();
    }

    @Override
    public Explosion createExplosion() {
        Explosion explosion = new Explosion(x, y, directionX, directionY, 50);
        return explosion;
    }

    
    
    @Override
    public void accelerate(float acceleration) {
        super.accelerate(acceleration);
        float engineX = getEngineX();
        float engineY = getEngineY();
        graphicEngineTrail.registerAccelerateAt(engineX, engineY);
    }

    @Override
    public void paint(Graphics graphics, CameraSettings cameraSettings) {
        super.paint(graphics, cameraSettings);
        graphicEngineTrail.paint(graphics, cameraSettings);
    }
    
    

    
    public boolean isRealoaded()
    {
        return reloaded;
    }

    public Bullet createBullet()
    {
        Bullet bullet = new Bullet(x + rotatedShapeX[0], 
                y + (int)rotatedShapeY[0], (float)this.turnAngle, 5.0f, this);
        reloaded = false;
        reloadIterationsLeft = reloadIterationsRequired;
        
        return bullet;
    }


    @Override
    public void move() {
        super.move();
        if(reloadIterationsLeft > 0) {
            --reloadIterationsLeft;
            if(reloadIterationsLeft == 0) {
                reloaded = true;
            }
        }
        graphicEngineTrail.update();
    }
    
    public Color getColor() 
    {
        return getShapeColor();
    }
    
    private void initializeOriginalShape() {
        originalShapeY = new float[3];
        originalShapeY[0] = 0.0f;
        originalShapeY[1] = 3.0f;
        originalShapeY[2] = -3.0f;

        originalShapeX = new float[3];
        originalShapeX[0] = 7.0f;
        originalShapeX[1] = -2.0f;
        originalShapeX[2] = -2.0f;

        rotatedShapeX = new float[3];
        rotatedShapeY = new float[3];
        updateRotationMatrix();
        updateRotatedShape();
    }

    private float getEngineX() {
        return x + (rotatedShapeX[1] + rotatedShapeX[2]) / 2;
    }

    private float getEngineY() {
        return y + (rotatedShapeY[1] + rotatedShapeY[2]) / 2;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds points to the players score.
     * @param scoreToAdd
     * @return 
     */
    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
