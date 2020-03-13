package pruebas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private CanvasG lienzo = new CanvasG();
    private detectorTeclas teclas = new detectorTeclas();

    private static int UPS = 0;
    private static int FPS = 0;

    // Hilo principal del juego
    private Thread hiloPrinicipal;
    // Volatile permite solo ser usadara por un Hilo, no puede ser modificad simultaneamente por dos hilos.
    private volatile boolean running = false;

    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        add(lienzo);
        addKeyListener(teclas);
        requestFocus();

    }

    // Funcion que se llama una vez que se cree el panel, para poder iniciar el juego
    public void addNotify() {
        super.addNotify();
        gameStart();
    }

    private void gameStart() {
        if (running == false) {
            running = true;
            hiloPrinicipal = new Thread(this, "GameThread");
            hiloPrinicipal.start();
        }
    }

    // Volatile pero para funciones
    private synchronized void gameOver() {
        running = false;
    }

    public synchronized void init() {
        final int NANO_POR_SEG = 1000000000; // Equivalencia de segundos en nanosegundos  
        final int PREFERED_UPS = 60; // Actualizacion por segundos deseads
        final double NANO_PER_UPS = NANO_POR_SEG / PREFERED_UPS; // Nanosegundos por actualizacion

        // Contador para las actualizaciones
        long referenceUpdate = System.nanoTime();
        //  Contador par los FPS 
        long referencerTimer = System.nanoTime();

        double timePassed; // Tiempo trasncurrido por cuadro
        double delta = 0; // Cantidad de tiempo hasta actualizacion

        while (running) {
            final long beginLoop = System.nanoTime(); // Cronometro que inicia el juego

            // tiempo desde el ultimo cuadro cargado
            timePassed = beginLoop - referenceUpdate;
            referenceUpdate = beginLoop;

            // Segundos que se le añaden al delta para sumar 1 seg 
            delta += timePassed / NANO_PER_UPS;

            // Cunado se completa un segundo se actualiza el juego y se resta delta a 0
            while (delta >= 1) {
                gameUpdate(); // Se llama cada cuadro
                delta--;
            }

            gameRepaint();
            System.out.println("UPS: " + UPS + " || " + " FPS: " + FPS);

            //Contador de FPS y UPS ( FPS: FRAMES PER SECONDS - UPS: UPDATES PER SECOND)
            if (System.nanoTime() - referencerTimer > NANO_POR_SEG) {
                UPS = 0;
                FPS = 0;
                referencerTimer = System.nanoTime();
            }
        }
        try {
            // Acaba el hilo progresivamente
            hiloPrinicipal.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
    }

    public void gameUpdate() {
        UPS++;
    }

    public void gameRender() {
    }

    public void gameRepaint() {
        FPS++;
    }
}
