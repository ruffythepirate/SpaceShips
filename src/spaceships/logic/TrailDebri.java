/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ruffy
 */
class TrailDebri {

    private static Color startColor = Color.RED;
    private static Color endColor = Color.BLACK;
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    private float deteriorationFactor = 0.012f;
    private float startRadius = 2.0f;
    private float endRadius = 15.0f;
    
    int lifeIterations;
    final int lifeIterationLimit;
    
    public TrailDebri(int x, int y) {
        lifeIterationLimit = (int)(1.0f / deteriorationFactor);
        this.x = x;
        this.y = y;
    }
    
    public void update()
    {
        lifeIterations++;
    }
    
    public boolean isStillActive() {
        return lifeIterations < lifeIterationLimit;
    }
    
    public void paint(Graphics graphics, CameraSettings cameraSettings) {
        
        Color currentColor = getColor(lifeIterations * deteriorationFactor);
        graphics.setColor(currentColor);
        
        float radius = startRadius + (endRadius - startRadius) * (lifeIterations / (float) lifeIterationLimit);
        int diameterX = (int)( 2*radius * cameraSettings.scaleX);
        int diameterY = (int)( 2*radius * cameraSettings.scaleY);

        graphics.drawOval((int)getX()- cameraSettings.translationX,
                (int) getY() - cameraSettings.translationX, diameterX, diameterY);
        graphics.drawOval(x, y, diameterX, diameterY);
    }
    
    private static Color getColor(float deterioration) {
        if(deterioration > 1.0) {
            deterioration = 1.0f;
        }
        int red = (int)(startColor.getRed() * (1.0 - deterioration) + endColor.getRed()*deterioration);
        int green = (int)(startColor.getGreen() * (1.0 - deterioration) + endColor.getGreen()*deterioration);
        int blue = (int)(startColor.getBlue() * (1.0 - deterioration) + endColor.getBlue()*deterioration);
        return new Color(red, green, blue);
    }
    
}
