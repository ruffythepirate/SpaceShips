/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Color;

/**
 *
 * @author Ruffy
 */
public class ExplosionDebri {

    private float x, y;
    private float speedX, speedY;
    private float startRadius;
    private float endRadius;
    private float currentRadius;
    private static Color startColor = Color.RED;
    private static Color endColor = Color.BLACK;
    private Color currentColor = startColor;
    private int currentIteration = 0;
    private int iterationLimit = 100;
    private boolean alive = true;

    private ExplosionDebri(float x, float y, float speedX, float speedY,
            float startRadius, float endRadius) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.startRadius = startRadius;
        this.endRadius = endRadius;
    }

    public static ExplosionDebri generateDebri(float x, float y, float baseSpeedX, float baseSpeedY) {
        float speedX = baseSpeedX + 6.0f * (float) (Math.random()-0.5);
        float speedY = baseSpeedY + 6.0f * (float) (Math.random() - 0.5);
        ExplosionDebri debri = new ExplosionDebri(x, y, speedX, speedY, 3.0f, 9.0f);
        return debri;
    }

    public void update() {
        currentIteration++;
        float deteriorationFactor = currentIteration / (float) iterationLimit;
        x += speedX;
        y += speedY;
        currentColor = getColor(deteriorationFactor);
        currentRadius = getRadius(deteriorationFactor);
        alive = currentIteration < iterationLimit;
    }

    private float getRadius(float deterioration) {
        return startRadius + (endRadius - startRadius) * deterioration;
    }

    private static Color getColor(float deterioration) {
        if (deterioration > 1.0) {
            deterioration = 1.0f;
        }
        int red = (int) (startColor.getRed() * (1.0 - deterioration) + endColor.getRed() * deterioration);
        int green = (int) (startColor.getGreen() * (1.0 - deterioration) + endColor.getGreen() * deterioration);
        int blue = (int) (startColor.getBlue() * (1.0 - deterioration) + endColor.getBlue() * deterioration);
        return new Color(red, green, blue);
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @return the currentRadius
     */
    public float getCurrentRadius() {
        return currentRadius;
    }

    /**
     * @return the currentColor
     */
    public Color getCurrentColor() {
        return currentColor;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }
}
