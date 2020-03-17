package MainG;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    public Window(int width, int height) {
        setTitle("VENTANA DEL JUEGO");
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(width, height));
        setResizable(false);
        pack();
        setVisible(true);
    }
}
