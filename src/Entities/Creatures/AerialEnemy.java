/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import SecondMinigame.HUD;
import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */

public class AerialEnemy extends Enemy{

    private long lastAttackTimer,attackCooldown=2000, attackTimer=0,laserTimer=0,laserCooldown=600,lastLaser;
    private boolean shootin=false;
    private HUD hud;
    private Animation anm;
    public AerialEnemy(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height, hud);
        this.hud = hud;
        //Cronometro para saber el tiempo transcurrido antes de su generación
        lastAttackTimer= System.currentTimeMillis();
        this.width=110;
        this.height=99;
        this.setHealth(15);
        speed=1;
        
        bounds.x=0;
        bounds.y=0;
        bounds.width=110;
        bounds.height=99;
        anm= new Animation(300,Assets.aerialEnemy);
    }

    @Override
    public void die() {
        this.setActive(false);
    }

    @Override
    public void update() {
        
        attackTimer+=System.currentTimeMillis()-lastAttackTimer;
        lastAttackTimer=System.currentTimeMillis();
        anm.update();
        if(attackTimer>2000 ){
            shootRay();
        }else{
            movex();
            x+=Xmove;
            if(y<0)
                y+=speed;
        }
        
        
    }

    public void movex(){
        if(x>manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=-speed;
        }else if(x<manager.getPlayer().getX()+ manager.getPlayer().getWidth()/2){
            Xmove=speed;
        }else{
            Xmove=0;
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(getCurrentAnimationFrame(), (int) x,0, null);
        if(shootin){
            g.drawImage(Assets.laser,(int) (this.getX()-10), (int)(this.getY())+94, null);
        }
    }
    
    private void shootRay(){
        
        shootin=true;
        Rectangle laser= new Rectangle((int)this.x, (int) (y+this.getWidth()),40,720);
        
        for (Entity e : manager.getEntities()) {
                if(e.getCollisionBounds().intersects(laser)){
                    e.hurt(1);
                }
            }
        
        if(attackTimer>4000){
            attackTimer=0;
            shootin=false;
           
        }
    }
    
    //Conseguir la animación en cada movimiento
    private BufferedImage getCurrentAnimationFrame(){
        return anm.getCurrentFrame();
    }
}