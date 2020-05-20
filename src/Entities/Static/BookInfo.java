/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.Entity;
import Entities.EntityManager;
import Entities.Static.BookPile;
import MainG.Handler;
import MainG.Window;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */
public class BookInfo extends StaticEntity{

    
    private final EntityManager manager;
    private final BufferedImage img;

    public BookInfo(EntityManager manager, BufferedImage img, Handler handler, float x, float y, int width, int height) {
        super(handler, manager, x, y, width, height);
        this.manager = manager;
        this.img = img;
    }

    
    
    
    
    @Override
    public void update(){
        getInput();
    }
    
    void getInput(){
        if(Window.keyManager.enter){
            manager.removeEntity(this);
        }
    }
    
    @Override
    public void render(Graphics2D g){
        g.setColor(Color.blue);
        g.drawString("Se logró", x, y);
        g.drawString("Para salir muevase", x, y+20);
        g.drawImage(img,(int) x,(int) y,100,100,null);
        }

    @Override
    public void die() {
        
    }
}
