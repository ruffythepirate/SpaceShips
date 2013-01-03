/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import spaceships.logic.GraphicsWorld;
import spaceships.logic.IGraphicItem;

/**
 *
 * @author johancarlsson
 */
public class SpaceShipPanel extends JPanel {

    private boolean gameOver = false;
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
        if(isGameOver()) {
            String gameOverText = "GAME OVER";
            Rectangle2D textBounds =  grphcs.getFontMetrics().getStringBounds(gameOverText, grphcs);
            grphcs.drawString(gameOverText, (getWidth() - (int) textBounds.getWidth() ) / 2,
                    (getHeight() - (int) textBounds.getHeight() ) / 2);
        }
        
    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        super.processKeyEvent(ke);
    }

    /**
     * @return the gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * @param gameOver the gameOver to set
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
