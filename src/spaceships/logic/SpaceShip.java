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

    private int reloadIterationsLeft = 0;    
    private boolean reloaded = true;
    private int reloadIterationsRequired = 20;
    
    public SpaceShip() {
        super();
        initializeOriginalShape();

        x = y = 50;
        initializeShapeRadius();
    }
    
    public boolean isRealoaded()
    {
        return reloaded;
    }

    public Bullet createBullet()
    {
        Bullet bullet = new Bullet(x + rotatedShapeX[0], 
                y + (int)rotatedShapeY[0], (float)this.turnAngle, 5.0f);
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
    }
    
    
    
    private void initializeOriginalShape() {
        originalShapeY = new float[3];
        originalShapeY[0] = 0.0f;
        originalShapeY[1] = 3.0f;
        originalShapeY[2] = -3.0f;

        originalShapeX = new float[3];
        originalShapeX[0] = 9.0f;
        originalShapeX[1] = -2.0f;
        originalShapeX[2] = -2.0f;

        rotatedShapeX = new float[3];
        rotatedShapeY = new float[3];
        updateRotationMatrix();
        updateRotatedShape();
    }

}
