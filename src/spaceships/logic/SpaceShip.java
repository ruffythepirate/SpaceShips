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
public class SpaceShip extends MovingObject {

    public SpaceShip() {
        //Allocates the rotation matrix.
        rotationMatrix = new float[2][];
        rotationMatrix[0] = new float[2];
        rotationMatrix[1] = new float[2];

        initializeOriginalShape();

        x = y = 50;

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
