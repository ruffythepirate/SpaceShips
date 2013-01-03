/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Graphics;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ruffy
 */
public class Explosion implements IGraphicItem{

    List<ExplosionDebri> allDebri;
    private boolean alive = true;;
    
    public Explosion(float x, float y, float baseSpeedX, float baseSpeedY, int numberOfDebri) {
        allDebri = new ArrayList<ExplosionDebri>(numberOfDebri);
        for(int i = 0; i < numberOfDebri; i++) {
            ExplosionDebri debri = ExplosionDebri.generateDebri(x, y, baseSpeedX, baseSpeedY);
            allDebri.add(debri);
        }
    }
     
    
    @Override
    public void paint(Graphics graphics) {
        for(ExplosionDebri debri : allDebri) {
            paintDebri( graphics, debri);
        }
    }
    
    public void update()
    {
        for(ExplosionDebri debri : allDebri) {
            debri.update();
            alive |= debri.isAlive();
        }
    }

    private void paintDebri(Graphics graphics, ExplosionDebri debri) {
        graphics.setColor(debri.getCurrentColor());
        int diameter = (int)( 2*debri.getCurrentRadius());
        graphics.drawOval((int)debri.getX(), (int) debri.getY(), diameter, diameter);
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }
}
