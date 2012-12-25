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

    protected double turnAngle;
    protected double rotationSpeed;
    protected float x, y;
    protected float dx, dy;
    protected float speed;
    protected float directionX, directionY;
    //Graphics
    protected Color spaceShipColor = Color.WHITE;
    protected float originalShapeX[];
    protected float originalShapeY[];
    protected float rotatedShapeX[];
    protected float rotatedShapeY[];
    protected float rotationMatrix[][];

    public void accelerate(float acceleration) {
        directionX = directionX * speed + acceleration * rotationMatrix[0][0];
        directionY = directionY * speed + acceleration * rotationMatrix[1][0];
        speed = (float)Math.sqrt(directionX * directionX
                + directionY * directionY);
        directionX /= speed;
        directionY /= speed;
    }

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
