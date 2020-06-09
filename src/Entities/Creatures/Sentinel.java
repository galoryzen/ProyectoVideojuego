/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.EntityManager;
import Tilemaps.Tile;
import MainG.Handler;
import MainG.Handler;
import SecondMinigame.HUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Clase sentinel, enemigo en el LibraryGame.
 * @author German David
 */
public class Sentinel extends Creature{
    private boolean busted;
    private boolean tired;
    private final int placeX;
    private final int placeY;
    private Rectangle vision;
    private String tag;
    /**
     * Constructor de Sentinel.
     * @param handler Handler
     * @param manager EntityManager.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Altura.
     */
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
    }
    
    
    
    /**
     * Lo que se ejecuta cuando muere.
     */
    @Override
    public void die() {
        
    }
    /**
     * Actualiza el estado del sentinela.
     */
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
    /**
     * Reenderiza el sentinela.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect((int)(vision.x-handler.getGameCamara().getxOffset()),(int) (vision.y-handler.getGameCamara().getyOffset()), vision.width, vision.height);
        g.setColor(Color.gray);
        g.fillRect( (int) (x-handler.getGameCamara().getxOffset()),(int) (y-handler.getGameCamara().getyOffset()), width,height);
    }
    /**
     * Movimiento del sentinela.
     */
    @Override
     public void move(){
        moveY();
    }
     
    /**
     * Movimiento en X.
     */ 
    public void moveX(){
        if(Xmove>0){
           
                x+=Xmove;
            
        }else if(Xmove<0){
            
                x+=Xmove;
            
        }
    }
    /**
     * Movimiento en Y.
     */
    public void moveY(){
        if(Ymove<0){
            y+=Ymove;
                    
        }else if(Ymove>0){
            y+=Ymove;
        }
    }
    
    /**
     * Metodo para perseguir al personaje.
     */
    private void searching() {
        if(vision.contains(manager.getJoan().getX()+manager.getJoan().getBounds().getX(),manager.getJoan().getY()+manager.getJoan().getBounds().getY())){
            busted=true;
        }else{
            busted=false;
        }
    }
    
    /**
     * Metodo del rango de vision.
     */
    private void looking() {
        vision.x=(int)x-30;
        vision.y=(int)y;
        vision.width=Tile.TILEWIDTH;
        vision.height=Tile.TILEHEIGHT*3;
    }
    
}
