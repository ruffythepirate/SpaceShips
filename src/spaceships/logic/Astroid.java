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
public class Astroid extends MovingObject {

    private int astroidLevel;
    
    public static Astroid createAstroid(int astroidLevel) {
        Astroid returnedAstroid = null;
        switch(astroidLevel) {
            case 1:
                returnedAstroid = new Astroid(1, 5, 5.0f);
                break;
            case 2:
                returnedAstroid = new Astroid(2, 7, 7.0f);
                break;
            case 3: 
                returnedAstroid = new Astroid(3, 9, 9.0f);
                break;
            case 4:
                returnedAstroid = new Astroid(4, 11, 11.0f);
                break;
            case 5:
                returnedAstroid = new Astroid(5, 13, 13.0f);
                break;
            default:
        }
        return returnedAstroid;
    }
    
    private Astroid(int astroidLevel, int numberOfCorners, float radius) {
        super();

        this.astroidLevel = astroidLevel;
        initializeOriginalShape(numberOfCorners, radius);

        x = y = 50;
        initializeShapeRadius();
    }
    
    public static Astroid createRandomAstroid(int astroidLevel, float maxSpeed, float maxRotation)
    {
        Astroid astroid = createAstroid(astroidLevel);
        astroid.setSpeedAndDirection((float) Math.random() * maxSpeed, (float)(Math.PI * 2 * Math.random()));
        
        astroid.setRotation((float)Math.random() * maxRotation);
        return astroid;
    }
    
    public void explodeAstroid(MovingObject destroyingShape) {
        if(astroidLevel > 1) {
            int newAstroidLevel = astroidLevel - 1;
            for(int i = 0; i < 2; i++) 
            {
                Astroid firstAstroid = createRandomAstroid(newAstroidLevel, 2.0f, 1.0f);
                firstAstroid.setPosition(x, y);
                SpaceShips.getInstance().addAstroidToWorld(firstAstroid);
            }
        }
        
        SpaceShips.getInstance().removeAstroidFromWorld(this);

    }
    
    public void setSpeedAndDirection(float speed, float angle)
    {
        this.speed = speed;
        directionX = (float) Math.cos(angle);
        directionY = (float) -Math.sin(angle);
    }
    
    public void setRotation(float rotation)
    {
        rotationSpeed = rotation;
    }

    private void initializeOriginalShape(int numberOfCorners, float radius) {

        originalShapeY = new float[numberOfCorners];
        originalShapeX = new float[numberOfCorners];
        rotatedShapeX = new float[numberOfCorners];
        rotatedShapeY = new float[numberOfCorners];

        for(int i = 0; i < numberOfCorners; i++){
            float angle = (float)((2 * Math.PI * (float)i) / (float)numberOfCorners);
            originalShapeX[i] = (float)(radius * Math.cos(angle));
            originalShapeY[i] = (float)(radius * -Math.sin(angle));
            //Adds some random roughness to the astroid.
            originalShapeX[i] += (Math.random() - 0.5) * 0.1f * radius;
            originalShapeY[i] += (Math.random() - 0.5) * 0.1f * radius;
        }

        updateRotationMatrix();
        updateRotatedShape();
    }
}
