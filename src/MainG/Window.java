package MainG;

import GameStates.screenLoading;
import Handlers.KeyManager;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame {

    public KeyManager keyManager;

    public Window(int width, int height) {
        setTitle("VENTANA DEL JUEGO");
        //setUndecorated(true);
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
