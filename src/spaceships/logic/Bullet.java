/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author johancarlsson
 */
public class Bullet extends MovingObject implements IGraphicItem {

    public Bullet(int startX, int startY, float angle, float initialSpeed) {
        this.x = startX;
        this.y = startY;
        this.turnAngle = angle;

        initializeShape();
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
