/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import spaceships.SpaceShips;

/**
 *
 * @author johancarlsson
 */
public class Bullet extends MovingObject implements IGraphicItem {

    
    
    int stepsLeftInLife = 70;
    
    /**
     * Creates a new simple bulle tobject.
     * @param startX The coordinate where the bullet starts out from.
     * @param startY The coordinate where the bullet starts out from.
     * @param angle The direction that the bullet will fly in.
     * @param initialSpeed The initial speed of the bullet.
     */
    public Bullet(float startX, float startY, float angle, float initialSpeed) {
        this.x = startX;
        this.y = startY;
        this.turnAngle = angle;
        this.speed = initialSpeed;
        initializeShape();
        setDirectionToTurnAngle();
    }

    @Override
    public void move() {
        super.move();
        stepsLeftInLife--;
        //The bullet has been spent, it should now be removed from the physical world.
        if(stepsLeftInLife <= 0)
        {
            SpaceShips.getInstance().removeBulletFromWorld(this);
        }
    }

    
    
    private void initializeShape() {
        originalShapeY = new float[3];
        originalShapeY[0] = 0.0f;
        originalShapeY[1] = -0.5f;
        originalShapeY[2] = 0.5f;

        originalShapeX = new float[3];
        originalShapeX[0] = 1.0f;
        originalShapeX[1] = -1.0f;
        originalShapeX[2] = -1.0f;

        rotatedShapeX = new float[3];
        rotatedShapeY = new float[3];
        updateRotationMatrix();
        updateRotatedShape();

    }
}
