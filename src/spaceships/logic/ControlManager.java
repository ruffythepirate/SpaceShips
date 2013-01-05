/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author johancarlsson
 */
public class ControlManager implements KeyListener {

    public ControlManager() {
        commandDictionary = new HashMap<Integer, KeyCommand>();
        allCommands = new ArrayList<KeyCommand>();
    }
    Map<Integer, KeyCommand> commandDictionary;
    List<KeyCommand> allCommands;

    public void addKeyCommand(int keyCode, int commandId) {
        if (!commandDictionary.containsKey(keyCode)) {
            KeyCommand newCommand = new KeyCommand(keyCode, commandId);
            commandDictionary.put(keyCode, newCommand);
            allCommands.add(newCommand);
        }

    }

    public List<KeyCommand> getAllCommands() {
        return allCommands;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (commandDictionary.containsKey(keyCode)) {
            KeyCommand pressedCommand = commandDictionary.get(keyCode);
            pressedCommand.setActive(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (commandDictionary.containsKey(keyCode)) {
            KeyCommand pressedCommand = commandDictionary.get(keyCode);
            pressedCommand.setActive(false);
        }
    }
}
