/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import spaceships.logic.GraphicsWorld;
import spaceships.logic.IGraphicItem;

/**
 *
 * @author johancarlsson
 */
public class SpaceShipPanel extends JPanel {

    GraphicsWorld graphicsWorld;

    public void setGraphicsWorld(GraphicsWorld graphicsWorld) {

        this.graphicsWorld = graphicsWorld;
    }

    @Override
    public void paint(Graphics grphcs) {
        grphcs.setColor(Color.BLACK);
        grphcs.fillRect(0, 0, getWidth(), getHeight());
        
        if (graphicsWorld != null) {
            for (IGraphicItem graphicsItem : graphicsWorld.getGraphicItems()) {
                graphicsItem.paint(grphcs);
            }
        } else {
            grphcs.drawString("No Graphics World initialized!", 0, 0);
        }
        
    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        super.processKeyEvent(ke);
    }
}
