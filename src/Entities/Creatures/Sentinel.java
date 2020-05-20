/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.EntityManager;
import FirstMinigame.Tiles.Tile;
import MainG.Handler;
import SecondMinigame.HUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author German David
 */
public class Sentinel extends Creature{
    private boolean busted;
    private boolean tired;
    private final int placeX;
    private final int placeY;
    private Rectangle vision;
    private String tag;

    public Sentinel(Handler handler, EntityManager manager, float x, float y, int width, int height) {
        super(handler, manager, x, y, width, height);
        busted=false;
        tired=false;
        
        vision= new Rectangle();
        
        //Puntos iniciales
        placeX=(int)x;
        placeY=(int)y;
        
        //Bounds
        bounds.x=0;
        bounds.y=0;
        bounds.width=0;
        bounds.height=0;
        speed = speed - 2f;
    }
    
    
    
    
    @Override
    public void die() {
        
    }

    @Override
    public void update() {
        searching();
        looking();
        if(!busted){
            if(x>placeX){
                Xmove=-speed;
            }else if(x<placeX){
                Xmove=speed;
            }else{
                Xmove=0;
            }
            
            if(y>placeY){
                Ymove=-speed;
            }else if(y<placeY){
                Ymove=speed;
            }else{
                Ymove=0;
            }
            move();
        }else{
            if(x>manager.getJoan().getX()){
                Xmove=-speed;
            }else if(x<manager.getJoan().getX()){
                Xmove=speed;
            }else{
                Xmove=0;
            }
            
            if(y>manager.getJoan().getY()){
                Ymove=-speed;
            }else if(y<manager.getJoan().getY()){
                Ymove=speed;
            }else{
                Ymove=0;
            }
            move();
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect((int)(vision.x-handler.getGameCamara().getxOffset()),(int) (vision.y-handler.getGameCamara().getyOffset()), vision.width, vision.height);
        g.setColor(Color.gray);
        g.fillRect( (int) (x-handler.getGameCamara().getxOffset()),(int) (y-handler.getGameCamara().getyOffset()), width,height);
    }
    
    @Override
     public void move(){
        moveY();
    }
    public void moveX(){
        if(Xmove>0){
           
                x+=Xmove;
            
        }else if(Xmove<0){
            
                x+=Xmove;
            
        }
    }
    
    public void moveY(){
        if(Ymove<0){
            y+=Ymove;
                    
        }else if(Ymove>0){
            y+=Ymove;
        }
    }
    

    private void searching() {
        if(vision.contains(manager.getJoan().getX(),manager.getJoan().getY())){
            busted=true;
        }else{
            busted=false;
        }
    }

    private void looking() {
        vision.x=(int)x-30;
        vision.y=(int)y;
        vision.width=Tile.TILEWIDTH;
        vision.height=Tile.TILEHEIGHT*3;
    }
/*
    private void caught() {
        if(bounds.intersects(manager.getJoan().getBounds())){
            manager.getJoan().die();
        }
    }
  */  
}
