/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Enemigo Pursoid.
 * @author German David
 */
public class Pursoid extends Creature{

   //Attack range
    private int attackR;
    //Attack timer, AttackCooldown=
    private long lastAttackTimer,attackCooldown=10, attackTimer=attackCooldown;

    /**
     * Constructor de Pursoid.
     * @param handler Handler.
     * @param manager EntityManager.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     */
    public Pursoid(Handler handler,EntityManager manager, float x, float y) {
        super(handler,manager, x, y, 65,21);
        
        speed=(float) 2.5;
        bounds.x=0;
        bounds.y=0;
        bounds.width=65;
        bounds.height=21;
        
                
        
    }
    /**
     * Actualiza el estado de Pursoid.
     */
    @Override
    public void update() {
        
        move();
        checkAttacks();
    }
    /**
     * Reenderiza al enemigo.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics2D g) {
        
            g.drawImage(Assets.pursoid, (int) (x ), (int) (y ), 65, 21, null);
        
    }
    /**
     * Movimiento del enemigo.
     */
    @Override
    public void move(){
       
        movex();
        x += Xmove;
        movey();
        y += Ymove;
        
    }
    
    /**
     * Movimiento en X.
     */
    public void movex(){
        if(x>manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=(float) (-speed * handler.getDeltaTime()); 
        }else if(x<manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=(float) (speed * handler.getDeltaTime());
        }else{
            Xmove=0;
        }
    }
    
    /**
     * Movimiento en Y.
     */
    public void movey(){
        if(y>manager.getPlayer().getY()+ manager.getPlayer().getHeight()/2){
            Ymove=(float) (-speed * handler.getDeltaTime());
        }else if(y<manager.getPlayer().getY()+ manager.getPlayer().getHeight()/2){
            Ymove=(float) (speed * handler.getDeltaTime());
        }else{
            Ymove=0;
        }
    }
    
    /**
     * Revisa si es atacado.
     */
    private void checkAttacks(){
        
        attackTimer+= System.currentTimeMillis()-lastAttackTimer;
        lastAttackTimer= System.currentTimeMillis();
        if(attackTimer <attackCooldown)
            //No hace lo de abajo
            return;
        
        Rectangle cb = getCollisionBounds();
        
        attackTimer=0;
        
        for (Entity e :manager.getEntities()) { 
            
            System.out.println(""+e.getCollisionBounds());
            if(!e.equals(this)){
                 if(e.getCollisionBounds().intersects(cb)){
                    e.hurt(2);
                    return;
                }
           }
        }
    }
    
    /**
     * Lo que ejecuta si muere.
     */
    @Override
    public void die() {
        
    }
    
    
    
}

    

