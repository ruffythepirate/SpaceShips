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
    private float deteriorationFactor = 0.012f;
    
    final int radius = 2;
    
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
    
    public void paint(Graphics graphics) {
        
        Color currentColor = getColor(lifeIterations * deteriorationFactor);
        graphics.setColor(currentColor);
        graphics.drawOval(x, y, radius, radius);
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
