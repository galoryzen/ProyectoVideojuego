package pruebas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class detectorTeclas implements KeyListener {

    ArrayList<Key> teclas = new ArrayList<Key>();
    
    public class Key{
        
        public boolean presionada;
        
        public Key(){
            teclas.add(this);
        }
    }
    
    public void teclaPresionada(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            System.out.println("SALTA");
        }
    }
    
    Key UP = new Key();
    Key DOWN = new Key();
    Key RIGHT = new Key();
    Key LEFT = new Key();
    Key MENU = new Key();
    Key PASUE = new Key();
    
    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent e){
            teclaPresionada(e);
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}
