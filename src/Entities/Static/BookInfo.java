/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.Entity;
import Entities.EntityManager;
import Entities.Static.BookPile;
import FirstMinigame.WorldGenerator.WorldLibrary;
import MainG.Handler;
import MainG.Window;
import Tilemaps.Animation;
import java.awt.Color;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Clase que muestra la información de los libros.
 */
public class BookInfo extends StaticEntity {

    private final EntityManager manager;
    private final BufferedImage img;
    private final int id;
    private Graphics2D g;
    private static int contIni;
    private boolean sw = true;
    Font textFont = new Font("pixelart", Font.PLAIN, 20);
    private Animation openning;
    
    /**
     * Constructor de BookInfo.
     * @param manager EntityManager.
     * @param img Imagen que se mostrará.
     * @param handler Handler.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Alto.
     * @param id id del libro.
     */
    public BookInfo(EntityManager manager, BufferedImage img, Handler handler, float x, float y, int width, int height, int id) {
        super(handler, manager, x, y, width, height);
        this.manager = manager;
        this.img = img;
        this.id = id;
        openning = new Animation(100, Assets.BookOpenning);
    }
    
    /**
     * Actualiza el estado del BookInfo.
     */
    @Override
    public void update() {
        getInput();
        openning.update();
    }
    
    /**
     * Obtiene los inputs del usuario.
     */
    void getInput() {
        if (this.id != 6) {
            if (Window.keyManager.enter) {
                manager.removeEntity(this);//Quitar despues, cambiar por moverse a otra posicion
                this.manager.getJoan().setCanMove(true);

            }
        } else {
            if (Window.keyManager.esc) {
                manager.removeEntity(this);//Quitar despues, cambiar por moverse a otra posicion
                this.manager.getJoan().setCanMove(true);
            }

            if (Window.keyManager.enter) {
                this.manager.verifyEnd();
            }
        }
    }
    /**
     * Reenderiza el bookinfo.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics2D g) {
        this.manager.getJoan().setCanMove(false);
        g.drawImage(getCurrentFrame(), 200, 200, null);
    }
    
    /**
     * Lo que se ejecuta una vez muere.
     */
    @Override
    public void die() {

    }

    public int getId() {
        return id;
    }
    
    /**
     * Reenderiza la imagen.
     * @return Retorna el frame que se va a mostrar.
     */
    private Image getCurrentFrame() {

        if (sw == false) {
            if (WorldLibrary.bookcount < 6) {
                return Assets.Books[WorldLibrary.bookcount - 1];
            } else {
                if (this.id == 6) {
                    return Assets.QuizAsk;
                } else {
                    return Assets.Books[this.id];
                }
            }
        } else {
            if (openning.getCurrentFrame().equals(Assets.BookOpenning[5])) {
                sw = !sw;
            }
            return openning.getCurrentFrame();
        }

    }

}
