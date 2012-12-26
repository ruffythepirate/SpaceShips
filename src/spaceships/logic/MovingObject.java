/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author johancarlsson
 */
public abstract class MovingObject implements IGraphicItem, IPhysicalItem {

    /**
     * The angle that the shape is currently rotated to.
     */
    protected double turnAngle;
    /**
     * The speed with which the turn angle is updating.
     */
    protected double rotationSpeed;
    /**
     * The coordinates for the body.
     */
    protected float x, y;
    /**
     * The speed that the body is currently moving at.
     */
    protected float speed;
    /**
     * The speed of the current object in direction x and y.
     */
    protected float directionX, directionY;
    
    //Graphics
    /**
     * The color that the shape is rendered in.
     */
    protected Color spaceShipColor = Color.WHITE;
    /**
     * The coordinates for the shape if they would be rotated. The unrotated
     * angle corresponds to the shape pointing to the right.
     */
    protected float originalShapeX[], originalShapeY[];
    
    protected float rotatedShapeX[], rotatedShapeY[];
    private float rotationMatrix[][];

    public MovingObject()
    {
                //Allocates the rotation matrix.
        rotationMatrix = new float[2][];
        rotationMatrix[0] = new float[2];
        rotationMatrix[1] = new float[2];

    }
    
    /**
     * Increases the speed of the shape.
     * @param acceleration The amount to increase the speed to.
     */
    public void accelerate(float acceleration) {
        directionX = directionX * speed + acceleration * rotationMatrix[0][0];
        directionY = directionY * speed + acceleration * rotationMatrix[1][0];
        speed = (float)Math.sqrt(directionX * directionX
                + directionY * directionY);
        directionX /= speed;
        directionY /= speed;
    }

    /**
     * Adds a rotation speed to the object. (IE. the rotation speed
     * will accelerate.
     * @param degrees The amount that the rotation speed will accelerate.
     */
    public void turn(double degrees) {
        rotationSpeed += (degrees / 180) * Math.PI;
    }

    private void updateRotation() {
        if (Math.abs(rotationSpeed) > 0.01) {
            turnAngle += rotationSpeed;
            updateRotationMatrix();
            updateRotatedShape();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.translate((int) x, (int) y);
        graphics.setColor(spaceShipColor);
        for (int i = 0; i < rotatedShapeX.length - 1; i++) {
            graphics.drawLine((int) rotatedShapeX[i], (int) rotatedShapeY[i],
                    (int) rotatedShapeX[i + 1], (int) rotatedShapeY[i + 1]);
        }

        graphics.drawLine((int) rotatedShapeX[rotatedShapeX.length - 1], (int) rotatedShapeY[rotatedShapeX.length - 1],
                (int) rotatedShapeX[0], (int) rotatedShapeY[0]);


        graphics.translate((int) -x, (int) -y);
    }

    /**
     * Moves the shape by applying the turning speed and dx and dy.
     */
    @Override
    public void move() {

        updateRotation();


        x += directionX * speed;
        y += directionY * speed;
    }

    @Override
    public void wrapPosition(int width, int height) {
        if (width > 0) {
            while (x < 0) {
                x += width;
            }
            while (x > width) {
                x -= width;
            }
        }
        if (height > 0) {
            while (y < 0) {
                y += height;
            }
            while (y > height) {
                y -= height;
            }
        }
    }

    @Override
    public void applyFriction(float frictionFactor) {
        speed = speed * frictionFactor;
        rotationSpeed = rotationSpeed * frictionFactor;
    }

    protected void updateRotationMatrix() {
        float sin = (float) Math.sin(turnAngle);
        float cos = (float) Math.cos(turnAngle);

        rotationMatrix[0][0] = cos;
        rotationMatrix[0][1] = -sin;
        rotationMatrix[1][0] = sin;
        rotationMatrix[1][1] = cos;
    }

    protected void updateRotatedShape() {
        for (int i = 0; i < rotatedShapeX.length; i++) {
            rotatedShapeX[i] = originalShapeX[i] * rotationMatrix[0][0]
                    + originalShapeY[i] * rotationMatrix[0][1];

            rotatedShapeY[i] = originalShapeX[i] * rotationMatrix[1][0]
                    + originalShapeY[i] * rotationMatrix[1][1];
        }
    }
}
