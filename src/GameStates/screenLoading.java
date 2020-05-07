package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;

/**
 *
 * @author Omen
 */
public class screenLoading extends JDialog implements Runnable {

    private volatile boolean running = true;
    private long Time;
    private Graphics2D g;
    private BufferedImage image;

    @Override
    public void run() {
        init();
        Time = System.currentTimeMillis();
        while (running) {
            draw(g);
            drawToScreen();
            timePassed(Time);
        }
        this.setVisible(false);
    }

    public void init() {
        this.setVisible(true);
        this.setSize(1080,720);
        image = new BufferedImage(1080, 720, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
    }

    public void draw(Graphics2D g) {
        g.clearRect(0,0,1080, 720);
        g.setColor(Color.yellow);
        g.drawString("CARGANDO...", 540, 360);
    }

    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, 1080, 720, null);
        g2.dispose();
    }

    private void timePassed(long time) {
        long deltaTime = System.currentTimeMillis() - time;
        if (deltaTime >= 1100) {
            running = false;
        }
    }
    
    public boolean isFinished(){
        return running;
    }
}
