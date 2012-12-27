/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author Ruffy
 */
public class CustomShape extends MovingObject {
    
    public CustomShape(float startX, float startY, float[] shapeXCoordinates, float[] shapeYCoordinates) 
    {
        super();
        x = startX;
        y = startY;
        
        originalShapeY = new float[shapeYCoordinates.length];
        originalShapeX = new float[shapeYCoordinates.length];
        rotatedShapeX = new float[shapeYCoordinates.length];
        rotatedShapeY = new float[shapeYCoordinates.length];
        for(int i = 0; i < shapeYCoordinates.length; i++) {
            originalShapeY[i] = shapeYCoordinates[i];
            originalShapeX[i] = shapeXCoordinates[i];
        }

        updateRotationMatrix();
        updateRotatedShape();
    }
}
