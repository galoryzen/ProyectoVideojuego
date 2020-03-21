package Handlers;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class detectorTeclas{

    static ArrayList<Key> teclas = new ArrayList<Key>();
 
    public detectorTeclas() {
        setKeys();
    }

    public class Key {
        String nombre;
        public boolean esPresionada = false;
        public int tecla;
    }

    public Key UP = new Key();
    public Key DOWN = new Key();
    public Key RIGHT = new Key();
    public Key LEFT = new Key();
    public Key ACTION = new Key();
    public Key MENU = new Key();
    public Key PAUSE = new Key();
    public Key TEST = new Key();

    public void checkKeyPressed(int key, String evento) {
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

    private void setKeys() {
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
        ACTION.tecla = KeyEvent.VK_E;
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

    public void checkKeyDebug() {
        for(Key tecla : teclas) {
            System.out.println("TECLA " + tecla.nombre + " esta presionda: " + tecla.esPresionada);
        }
    }
}