package MainG;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import Handlers.detectorTeclas;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends JFrame implements KeyListener{
    
    public detectorTeclas teclas;
    
    public Window(int width, int height) {
        setTitle("VENTANA DEL JUEGO");
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(width, height));
        setResizable(false);
        pack();
        setVisible(true);
        teclas = new detectorTeclas();
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void keyPressed(KeyEvent key) {
        int key_p = key.getKeyCode();
        teclas.checkKeyPressed(key_p, "pressed");
    }

    @Override
    public void keyReleased(KeyEvent key) {
        int key_p = key.getKeyCode();
        teclas.checkKeyPressed(key_p, "relased");
    }
}
