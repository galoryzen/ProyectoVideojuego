/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.EntityManager;
import FirstMinigame.WorldGenerator.WorldLibrary;
import Tilemaps.Assets;
import MainG.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Clase de los BookPiles del mapa.
 */
public class BookPile extends StaticEntity{
    
    private BookInfo bookinfo;
    private EntityManager entityM;
    long lastAttackTimer = 0;
    long attackTimer = 0;
    private int id;
    private boolean found = false;
    
    /**
     * Constructor de BookPile.
     * @param handler Handler.
     * @param entityM EntityManager.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param bookinfo BookInfo que mostrará una vez encontrado el BookPile.
     */
    public BookPile(Handler handler, EntityManager entityM, float x, float y,BookInfo bookinfo) {
        super(handler, entityM, x, y, 55,55);
        
        this.bookinfo=bookinfo;
        this.entityM=entityM;
        this.id = this.bookinfo.getId();
        bounds.x=5;
        bounds.y=1;
        bounds.width=50;
        bounds.height=45;

    }

    @Override
    public void update(){
        
        
    }
    /**
     * Lo que se ejecuta una vez muera(Sea encontrado).
     */
    @Override
    public void die(){
        WorldLibrary.bookcount+=1;
        entityM.addEntity(bookinfo);
    }
    /**
     * Reenderiza el bookpile.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics2D g){
        g.drawImage(Assets.BookPile, (int) (x-handler.getGameCamara().getxOffset()),(int) (y-handler.getGameCamara().getyOffset()), null);
    }
    /**
     * Metodo de lo que se hace una vez se encuentra el libro.
     */
    public void foundBook(){
        if (!found) {
            WorldLibrary.bookcount += 1;
            found = true;
        }
        entityM.addEntity(bookinfo);
        if (this.bookinfo.getId()!=6) {
            this.move(6150 + this.id*85 , 1330);
        }
    }
    
    public int getId() {
        return id;
    }
    /**
     * Metodo que mueve el libro una vez encontrado.
     * @param x Coordenada en X a la que se moverá.
     * @param y Coordenada en Y a la que se moverá.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
