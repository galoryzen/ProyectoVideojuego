package Handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import GameStates.GameStateManager;

public class detectorTeclas implements KeyListener {

    ArrayList<Key> teclas = new ArrayList<Key>();
    GameStateManager gsm;
    
    public detectorTeclas(GameStateManager gsm) {
        this.gsm = gsm;
        setKeys();
    }

    public class Key {
        String nombre;
        public boolean esPresionada = false;
        public int tecla;
    }

    Key UP = new Key();
    Key DOWN = new Key();
    Key RIGHT = new Key();
    Key LEFT = new Key();
    Key ACTION = new Key();
    Key MENU = new Key();
    Key PAUSE = new Key();
    Key TEST = new Key();

    void checkKeyPressed(int key, String evento) {
        boolean isPressed = false;
        if (evento.equals("pressed")) {
            isPressed = !isPressed;
        }
        for (Key tecla : teclas) {
            if (tecla.tecla == key) {
                tecla.esPresionada = isPressed;
            }
        }
    }

    void setKeys() {
        UP.tecla = KeyEvent.VK_W;
        UP.nombre = "ARRIBA";
        teclas.add(UP);
        DOWN.tecla = KeyEvent.VK_S;
        DOWN.nombre = "ABAJO";
        teclas.add(DOWN);
        RIGHT.tecla = KeyEvent.VK_D;
        RIGHT.nombre = "DERECHA";
        teclas.add(RIGHT);
        LEFT.tecla = KeyEvent.VK_A;
        LEFT.nombre = "IZQUIERDA";
        teclas.add(LEFT);
        ACTION.nombre = "INTERACTUAR";
        teclas.add(ACTION);
        MENU.tecla = KeyEvent.VK_ESCAPE;
        MENU.nombre = "MENU";
        teclas.add(MENU);
        PAUSE.tecla = KeyEvent.VK_P;
        PAUSE.nombre = "PAUSA";
        teclas.add(PAUSE);
        TEST.tecla = KeyEvent.VK_Q;
        TEST.nombre = "TEST";
        teclas.add(TEST);
    }

    void checkKeyDebug() {
        for (Key tecla : teclas) {
            System.out.println("TECLA " + tecla.nombre + " esta presionda: " + tecla.esPresionada);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        checkKeyPressed(key, "relased");
    }
}
