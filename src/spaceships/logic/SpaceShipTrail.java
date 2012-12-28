/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ruffy
 */
public class SpaceShipTrail implements IGraphicItem{

    private final int showTrailInterval = 10;
    
    List<TrailDebri> allDebri;
    
    final int reloadLimit = 7;
    int currentReloadCount = 10;
    
    public SpaceShipTrail() {
        allDebri = new LinkedList<TrailDebri>();
    }
 
    public void registerAccelerateAt(float x, float y) 
    {
        if( currentReloadCount >= reloadLimit) {
            placeDebri((int) x, (int) y);
            currentReloadCount = 0;
        } else {
            currentReloadCount++;
        }
    }
    
    private void placeDebri(int x, int y) {
        allDebri.add(new TrailDebri(x, y));
    }
    
    @Override
    public void paint(Graphics graphics) {
        for(TrailDebri debri : allDebri) {
            debri.paint(graphics);
        }
    }
    
    public void update()
    {
        boolean needsToCleanList = false;
        for(TrailDebri debri : allDebri) {
            debri.update();
            needsToCleanList = needsToCleanList || !debri.isStillActive();
        }
        
        if(needsToCleanList) {
            cleanDebriList();
        }
    }
    
    private void cleanDebriList() {
        LinkedList<TrailDebri> newList = new LinkedList<TrailDebri>();
        for(TrailDebri debri : allDebri) {
            if(debri.isStillActive())
            {
                newList.push(debri);
            }
        }
        allDebri = newList;
    }
    
}
