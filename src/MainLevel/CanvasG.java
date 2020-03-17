package MainLevel;

import Handlers.detectorTeclas;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JLabel;

public class CanvasG extends Canvas {

    private detectorTeclas teclas;
    public Character personaje;
    
    public Random rnd = new Random();

    public CanvasG() {
        setPreferredSize(new Dimension(1080, 720));
        //teclas = new detectorTeclas();
        //addKeyListener(teclas);
    }

    public void paint(Graphics g) {
        
    }
}
 