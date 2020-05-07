/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;

import Entities.Creatures.Boss;
import Entities.Creatures.Player;
import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author German David
 */
public class AutoMissil extends Entity{

    private int bulletSpeed;
    //Puntos de la nave y el jugador
    private int xo,yo,yp,xp, m;
    
    private int Xmove, Ymove;
    
    public AutoMissil(Handler handler, EntityManager manager, float x, float y) {
        super(handler, manager, x, y, 30, 15);
        
        bulletSpeed=1;
        
        xo=(int) this.getX();
        yo=(int) this.getY();
        yp=(int) manager.getPlayer().getY();
        xp=(int) manager.getPlayer().getX();
        m=(int) ((yo-yp)/(xo-xp));
        
        this.setHealth(1);
        
        bounds.x=0;
        bounds.y=0;
        bounds.width=30;
        bounds.height=15;
    }

    @Override
    public void die() {
        
    }

    @Override
    public void update() {
        move();
        checkAttacks();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.bullet, (int)x, (int)y, null);
    }
    
    private void move(){
        movex();
        movey();
        x+=Xmove;
        y+=Ymove;
        
    }
    
    public void movey(){
        if(y>manager.getPlayer().getY()+ manager.getPlayer().getHeight()/2){
            Ymove=-bulletSpeed;
        }else if(y<manager.getPlayer().getY()+ manager.getPlayer().getHeight()/2){
            Ymove=bulletSpeed;
        }else{
            Ymove=0;
        }
    }
    
    public void movex(){
        if(x>manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=-bulletSpeed;
        }else if(x<manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=bulletSpeed;
        }else{
            Xmove=0;
        }
    }
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
        for (Entity e : manager.getEntities()) {
            if (!(e instanceof AutoMissil) && !(e instanceof Boss)) {
                if (e.getCollisionBounds().intersects(cb)) {
                    e.hurt(1);
                    this.setActive(false);
                    System.out.println("Me golpeé con " + e);
                }
            }
        }
    }
    
    
    
}
