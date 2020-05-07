package Handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author German David
 */
public class KeyManager implements KeyListener {

    private static boolean[] keys;
    public boolean up, down, left, right, space, test, enter;
    public long pressed, realTime,valorTime = 0;

    public KeyManager() {
        keys = new boolean[256];

    }

    public void update() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];
        space = keys[KeyEvent.VK_Q];
        test = keys[KeyEvent.VK_E];
        enter = keys[KeyEvent.VK_ENTER];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }

    public long getTime() {
        return valorTime;
    }
}
