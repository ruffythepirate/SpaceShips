/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author johancarlsson
 */
public class Astroid extends MovingObject {

    public Astroid(int numberOfCorners, float radius) {
        super();

        initializeOriginalShape(numberOfCorners, radius);

        x = y = 50;
        initializeShapeRadius();
    }
    
    public static Astroid createRandomAstroid(int numberOfCorners, float radius, float maxSpeed, float maxRotation)
    {
        Astroid astroid = new Astroid(numberOfCorners, radius);
        astroid.setSpeedAndDirection((float) Math.random() * maxSpeed, (float)(Math.PI * 2 * Math.random()));
        
        astroid.setRotation((float)Math.random() * maxRotation);
        return astroid;
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
