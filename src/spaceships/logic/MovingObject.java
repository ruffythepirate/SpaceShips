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
     * The previous coordinates of the object.
     */
    protected float previousX, previousY;
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
    protected float objectRadius;
    protected float rotatedShapeX[], rotatedShapeY[];
    private float rotationMatrix[][];

    public MovingObject() {
        //Allocates the rotation matrix.
        rotationMatrix = new float[2][];
        rotationMatrix[0] = new float[2];
        rotationMatrix[1] = new float[2];

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Increases the speed of the shape.
     *
     * @param acceleration The amount to increase the speed to.
     */
    public void accelerate(float acceleration) {
        directionX = directionX * speed + acceleration * rotationMatrix[0][0];
        directionY = directionY * speed + acceleration * rotationMatrix[1][0];
        speed = (float) Math.sqrt(directionX * directionX
                + directionY * directionY);
        directionX /= speed;
        directionY /= speed;
    }

    /**
     * Adds a rotation speed to the object. (IE. the rotation speed will
     * accelerate.
     *
     * @param degrees The amount that the rotation speed will accelerate.
     */
    public void turn(double degrees) {
        rotationSpeed += (degrees / 180) * Math.PI;
    }

    protected void setDirectionToTurnAngle() {
        updateRotationMatrix();
        directionX = rotationMatrix[0][0];
        directionY = rotationMatrix[1][0];
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

        previousX = x;
        previousY = y;
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

    /**
     * Method that verifies if a given coordinate is contained within this shape
     * or not.
     *
     * @param x the x coordinate for the point
     * @param y the y coordinate for the point
     * @return True if the point is contained, other false.
     */
    public boolean shapeContainsPoint(float x, float y) {
        //The theory of this algorithm is to draw a straight line to the left
        //from the point. We then count the number of shape lines that this line intersects.
        //If it is a odd number - the point is contained, otherwise it is not.        
        //There is an assumed orientation of the lines, they are built clock-wise.
        int numberOfIntersectedLines = 0;
        
        //We place the coordinate in our local coordinate system.
        x -= this.x;
        y -= this.y;
        for (int i = 0; i < rotatedShapeX.length - 1; i++) {
            boolean pointCutLineFromRight = checkPointToTheRightOfLine(x, y,
                    rotatedShapeX[i], rotatedShapeY[i],
                    rotatedShapeX[i + 1], rotatedShapeY[i + 1]);
            if (pointCutLineFromRight) {
                numberOfIntersectedLines++;
            }
        }
        boolean pointCutLineFromRight = checkPointToTheRightOfLine(x, y,
                rotatedShapeX[rotatedShapeX.length - 1], rotatedShapeY[rotatedShapeX.length - 1],
                rotatedShapeX[0], rotatedShapeY[0]);
        if (pointCutLineFromRight) {
            numberOfIntersectedLines++;
        }

        return numberOfIntersectedLines % 2 == 1;
    }
    
    /**
     * Checks if the given point is close enough to the shape to might be able to intersect.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @return True if the point might intersect, otherwise false.
     */
    public boolean pointMightBeContained(float x, float y) {
        float xDiff = x - this.x;
        float yDiff = y - this.y;
        return Math.abs(xDiff) + Math.abs(yDiff) < 2* this.objectRadius;
    }

    /**
     * Checks if the given line intersects this shape or not.
     * @param startX The start coordinate for the line.
     * @param startY The start coordinate for the line.
     * @param endX The end coordinate for the line.
     * @param endY The end coordinate for the line.
     * @return True if they intersect, otherwise false.
     */
    public boolean lineIntersectsShape(float startX, float startY, float endX, float endY) {
        for (int i = 0; i < rotatedShapeX.length - 1; i++) {
            boolean linesIntersect = checkLineIntersects(startX, startY, endX, endY, rotatedShapeX[i], rotatedShapeY[i], rotatedShapeX[i + 1], rotatedShapeY[i + 1]);
            if (linesIntersect) {
                return true;
            }
        }
        boolean linesIntersect = checkLineIntersects(startX, startY, endX, endY, rotatedShapeX[rotatedShapeY.length - 1], rotatedShapeY[rotatedShapeY.length - 1], rotatedShapeX[0], rotatedShapeY[0]);
        if (linesIntersect) {
            return true;
        }
        return false;
    }

    public static boolean checkLineIntersects(float line1StartX, float line1StartY, float line1EndX, float line1EndY,
            float line2StartX, float line2StartY, float line2EndX, float line2EndY) {
        float line1VectorX = line1EndX - line1StartX;
        float line1VectorY = line1EndY - line1StartY;
        float line2VectorX = line2EndX - line2StartX;
        float line2VectorY = line2EndY - line2StartY;
        float startVectorX = line1StartX - line2StartX;
        float startVectorY = line1StartY - line2StartY;
        float determinant = line2VectorY * line1VectorX - line1VectorY * line2VectorX;
        //This means that the lines are parallell.
        if(determinant == 0) {
            return false;
        }
        float factor1  =  line2VectorX * (startVectorY) - line2VectorY*(startVectorX);
        factor1 /= determinant;
        float factor2 = line1VectorX*(startVectorY) - line1VectorY * (startVectorX);
        factor2 /= determinant;
        
        return factor1 >= 0 && factor1 < 1.0f
            && factor2 >= 0 && factor2 < 1.0f;
    }

    private static boolean checkPointToTheRightOfLine(float pointX, float pointY,
            float lineStartX, float lineStartY, float lineEndX, float lineEndY) {
        float crossProduct = 0;
        float lineTopX, lineTopY, lineBottomX, lineBottomY;
        if (lineStartY < lineEndY) {
            lineTopX = lineEndX;
            lineTopY = lineEndY;
            lineBottomX = lineStartX;
            lineBottomY = lineStartY;
        } else {
            lineTopX = lineStartX;
            lineTopY = lineStartY;
            lineBottomX = lineEndX;
            lineBottomY = lineEndY;
        }
        boolean isInCorrectHeight = (pointY <= lineStartY && pointY > lineEndY)
                || (pointY <= lineEndY && pointY > lineStartY);
        if (isInCorrectHeight) {
            //We calculate two vectors, the line vector and the vector from the origin to the point.
            float vectorX = pointX - lineBottomX;
            float vectorY = pointY - lineBottomY;
            float sideVectorX = lineTopX - lineBottomX;
            float sideVectorY = lineTopY - lineBottomY;
            //We check if the point is to the left or to the right of the point.
            //by taking the cross product.
            crossProduct = vectorX * sideVectorY - vectorY * sideVectorX;
        }
        return crossProduct > 0;
    }

    protected boolean circleIntersect(MovingObject otherObject) {
        float centerDiffX = x - otherObject.x;
        float centerDiffY = y - otherObject.y;
        float requiredRadius = (objectRadius + otherObject.objectRadius);
        if (centerDiffX * centerDiffX + centerDiffY * centerDiffY < requiredRadius) {
            return true;
        }
        return false;
    }

    public boolean intersects(MovingObject otherObject) {
        float centerDiffX = Math.abs(x - otherObject.x);
        float centerDiffY = Math.abs(y - otherObject.y);

        if (centerDiffX < (objectRadius + otherObject.objectRadius)) {
            return detailedIntersectCalculation(otherObject);
        }
        return false;
    }

    private boolean detailedIntersectCalculation(MovingObject otherObject) {
        return true;
    }

    @Override
    public void applyFriction(float frictionFactor) {
        speed = speed * frictionFactor;
        rotationSpeed = rotationSpeed * frictionFactor;
    }

    protected void initializeShapeRadius() {
        float biggestRadius = 0;
        for (int i = 0; i < originalShapeX.length; i++) {
            float radius = (float) Math.sqrt(originalShapeX[i] * originalShapeX[i]
                    + originalShapeY[i] * originalShapeY[i]);
            if (radius > objectRadius) {
                objectRadius = radius;
            }
        }
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
