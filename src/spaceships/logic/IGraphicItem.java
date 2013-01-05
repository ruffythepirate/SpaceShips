/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Graphics;

/**
 *
 * @author ruffy
 */
public interface IGraphicItem {
    
    void paint(Graphics graphics, 
            CameraSettings cameraSettings);
}
