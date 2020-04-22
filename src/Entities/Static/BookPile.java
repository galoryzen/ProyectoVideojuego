/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.EntityManager;
import Tilemaps.Assets;
import MainG.Handler;
import java.awt.Graphics;

/**
 *
 * @author German David
 */
public class BookPile extends StaticEntity{
    
    public BookPile(Handler handler, EntityManager entityM, float x, float y) {
        super(handler, entityM, x, y, 55,55);
        bounds.x=5;
        bounds.y=1;
        bounds.width=50;
        bounds.height=45;
    }
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void die(){
        
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(Assets.BookPile, (int) (x-handler.getGameCamara().getxOffset()),(int) (y-handler.getGameCamara().getyOffset()), null);
    }
}
