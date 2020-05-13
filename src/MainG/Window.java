package MainG;

import GameStates.screenLoading;
import Handlers.KeyManager;
import Handlers.MouseManager;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * Window es la ventana del juego
 * @version 1.0
 */
public class Window extends JFrame {

    public static KeyManager keyManager;
    public static MouseManager mouse;
    
    /**
     * En el constructor de la ventana se le pasan las dimensiones como parametro
     * Se le proporciana un titulo
     * Se evita que el usuario modifique las dimensiones mediante setResizable(false)
     * Se crea un KeyManager
     * @param width Anchura de la ventana
     * @param height Altura de la ventana
     */  
    public Window(int width, int height) {
        //Se le pone un titulo y las dimensiones
        setTitle("VENTANA DEL JUEGO");
        setPreferredSize(new Dimension(width, height));
        
        //Se define lo que sucede si presiona la x en la esquina superior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(width, height));
        
        //Evita que el usuario modifique las dimensiones
        setResizable(false);
        pack();
        
        //Se hace visible
        setVisible(true);
        
        //Se crea un keyManager para usarlo en el juego
        keyManager = new KeyManager();
        addKeyListener(keyManager);
        mouse= new MouseManager();
        addKeyListener(keyManager);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
}