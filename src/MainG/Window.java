package MainG;

import Handlers.KeyManager;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Window extends JFrame{

    public KeyManager keyManager;

    public Window(int width, int height) {
        setTitle("VENTANA DEL JUEGO");
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(width, height));
        setResizable(false);
        pack();
        setVisible(true);
        keyManager = new KeyManager();
        addKeyListener(keyManager);
    }
}
