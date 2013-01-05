/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Graphics;

/**
 *
 * @author Ruffy
 */
public class PlayField implements IGraphicItem {
    private int width, height; 
    
    public PlayField(int width, int height) {
        setWidth(width);
        setHeight(height);
    }
    
    @Override
    public void paint(Graphics graphics, CameraSettings cameraSettings) 
    {
        
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }
}
