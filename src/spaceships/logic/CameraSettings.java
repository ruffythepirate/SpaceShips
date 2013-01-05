/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author Ruffy
 */
public class CameraSettings {
    int translationX, translationY;
    float scaleX, scaleY;

    public CameraSettings() {
        translationX = translationY = 0;
        scaleX = scaleY = 1.0f;
    }
    
    public int getTranslationX() {
        return translationX;
    }

    public void setTranslationX(int translationX) {
        this.translationX = translationX;
    }

    public int getTranslationY() {
        return translationY;
    }

    public void setTranslationY(int translationY) {
        this.translationY = translationY;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }
}
