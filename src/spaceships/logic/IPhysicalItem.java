/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author johancarlsson
 */
public interface IPhysicalItem {
    
    void move();
    
    void wrapPosition(int width, int height);
    
    void applyFriction(float frictionFactor);
}
