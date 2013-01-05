/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Formatter;
import java.util.List;
import javax.swing.JPanel;
import spaceships.SpaceShips;
import spaceships.logic.CameraSettings;
import spaceships.logic.GraphicsWorld;
import spaceships.logic.IGraphicItem;
import spaceships.logic.PlayField;
import spaceships.logic.SpaceShip;

/**
 *
 * @author johancarlsson
 */
public class SpaceShipPanel extends JPanel {

    private static final int SCORE_PADDING_TOP = 10;
    private static final int SCORE_PADDING_LEFT = 10;
    private CameraSettings cameraSettings;
    private boolean gameOver = false;
    private boolean printScore = true;
    GraphicsWorld graphicsWorld;
    private PlayField playField;

    public PlayField getPlayField() {
        return playField;
    }

    public void setPlayField(PlayField playField) {
        this.playField = playField;
    }

    public SpaceShipPanel() {
        super();
        cameraSettings = new CameraSettings();
    }

    public void setGraphicsWorld(GraphicsWorld graphicsWorld) {
        this.graphicsWorld = graphicsWorld;
    }

    @Override
    public void paint(Graphics grphcs) {
        PlayField playField = getPlayField();
        if (playField != null) {
            cameraSettings.setScaleX(getWidth() / (float) playField.getWidth());
            cameraSettings.setScaleY(getHeight() / (float) playField.getHeight());
        }
        grphcs.setColor(Color.BLACK);
        grphcs.fillRect(0, 0, getWidth(), getHeight());

        if (graphicsWorld != null) {
            for (IGraphicItem graphicsItem : graphicsWorld.getGraphicItems()) {
                graphicsItem.paint(grphcs, cameraSettings);
            }
        } else {
            grphcs.drawString("No Graphics World initialized!", 0, 0);
        }
        if (isGameOver()) {
            paintGameOverText(grphcs);
        }
        if (isPrintScore()) {
            printScore(grphcs);
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

    private void paintGameOverText(Graphics grphcs) {
        String gameOverText = "GAME OVER";
        Rectangle2D textBounds = grphcs.getFontMetrics().getStringBounds(gameOverText, grphcs);
        grphcs.setColor(Color.LIGHT_GRAY);
        grphcs.drawString(gameOverText, (getWidth() - (int) textBounds.getWidth()) / 2,
                (getHeight() - (int) textBounds.getHeight()) / 2);
    }

    private void printScore(Graphics graphics) {
        List<SpaceShip> allPlayers = SpaceShips.getInstance().getPlayers();
        int currentX = SCORE_PADDING_LEFT;
        for (SpaceShip spaceShip : allPlayers) {
            String scoreString = String.format("%10d", spaceShip.getScore());
            graphics.setColor(spaceShip.getColor());
            graphics.drawString(scoreString, currentX, SCORE_PADDING_TOP);
            currentX += graphics.getFontMetrics().getStringBounds(scoreString, graphics).getWidth() + SCORE_PADDING_LEFT;
        }
    }

    /**
     * @return the printScore
     */
    public boolean isPrintScore() {
        return printScore;
    }

    /**
     * @param printScore the printScore to set
     */
    public void setPrintScore(boolean printScore) {
        this.printScore = printScore;
    }
}
