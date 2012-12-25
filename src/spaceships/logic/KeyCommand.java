/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

/**
 *
 * @author johancarlsson
 */
public class KeyCommand {
    int keyCode;
    private int actionId;
    private boolean active;
    
    public KeyCommand(int keyCode, int actionId){
        this.keyCode = keyCode;
        this.actionId = actionId;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the actionId
     */
    public int getActionId() {
        return actionId;
    }
}
