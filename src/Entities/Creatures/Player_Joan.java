/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.EntityManager;
import Entities.Items.Bullet;
import Graficos.Animation;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */
public class Player_Joan extends Character {

    public Bullet[] bullets= new Bullet[100];
    public static int bullcount = 0;
    private long clock;
    private boolean punchie=false;
    private int ar1,ar2;
    
    //Attack range
    private int attackR;
    
    //Attack timer, AttackCooldown=
    private long lastAttackTimer,attackCooldown=800, attackTimer=attackCooldown;
    
    //Animations
    private Animation animDown,animUp,animR,animL;
    
    public Player_Joan(Handler handler, EntityManager entityM, float x, float y) {
        super(handler, entityM, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATUR_HEIGHT);
        
        /* Bounds para la chica
        bounds.x=40;
        bounds.y=35;
        bounds.width=35;
        bounds.height=60;
        */
        
        bounds.x=40;
        bounds.y=65;
        bounds.width=52;
        bounds.height=40;
        
        attackR=20;
        
        
        //Animations           Speed en millis()
        animDown= new Animation(100,Assets.playerDown);
        animUp= new Animation(100,Assets.playerUp);
        animR= new Animation(100,Assets.playerRight);
        animL= new Animation(100,Assets.playerLeft);
    }

    @Override
    public void update() {
        //Actualiza los frames
        animDown.update();
        animUp.update();
        animR.update();
        animL.update();
        getInput();
        move();
        //Attack
        checkAttacks();
        handler.getGameCamara().centerOnEntity(this);
    }

    private void checkAttacks(){
        
        attackTimer+= System.currentTimeMillis()-lastAttackTimer;
        lastAttackTimer= System.currentTimeMillis();
        if(attackTimer <attackCooldown)
            //No hace lo de abajo
            return;
        
        Rectangle cb = getCollisionBounds(0f,0f);
        Rectangle ar = new Rectangle();
        
        ar.width=attackR;
        ar.height= attackR;
        if(handler.getKeyManager().up){
            ar.x=cb.x+cb.width/2 - attackR/2;
            ar.y= cb.y-attackR;
        }else if(handler.getKeyManager().down){
            ar.x=cb.x+cb.width/2 - attackR/2;
            ar.y= cb.y+cb.height;
        } else if(handler.getKeyManager().left){
            ar.x=cb.x- attackR;
            ar.y= cb.y+ cb.height/2 - attackR/2;
        } else if(handler.getKeyManager().right){
            ar.x=cb.x + cb.width;
            ar.y= cb.y+ cb.height/2 - attackR/2;
        } else{
            return;
        }
        
        ar1=ar.x;
        ar2=ar.y;
        punchie=true;
        attackTimer=0;
        
//        for (Entity e : ) { 
//            
//            System.out.println(""+e.getCollisionBounds(0,0));
//            if(!e.equals(this)){
//                 if(e.getCollisionBounds(0,0).intersects(ar)){
//                    e.hurt(10);
//                    return;
//                }
//           }
//        }
    }
    
    public void die(){
        System.out.println("You lose");
    }
    
    private void getInput() {
        Xmove = 0;
        Ymove = 0;

        if (handler.getKeyManager().up) {
            Ymove = -speed;
        }

        if (handler.getKeyManager().down) {
            Ymove = speed;
        }

        if (handler.getKeyManager().right) {
            Xmove = speed;
        }
        if (handler.getKeyManager().left) {
            Xmove = -speed;
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamara().getxOffset()), (int) (y - handler.getGameCamara().getyOffset()), width, height, null);
        if(punchie){
            g.fillRect(ar1, ar2, attackR, attackR);
            punchie=false;
        }
        g.setColor(Color.yellow);
        g.fillRect((int) (x + bounds.x - handler.getGameCamara().getxOffset()), 
                (int) (y+bounds.y-handler.getGameCamara().getyOffset()), bounds.width,bounds.height);
        
        for (int i = 0; i < 100; i++) {
           if(bullets[i]!= null){ 
            bullets[i].render(g);
           }
        }
        
    }
    
    
    //Conseguir la animación en cada movimiento
    private BufferedImage getCurrentAnimationFrame(){
        if(Xmove<0){
            return animL.getCurrentFrame();
        }else if (Xmove>0){
            return animR.getCurrentFrame();
        }else if(Ymove<0){
            return animUp.getCurrentFrame();
        }else if (Ymove>0){
            return animDown.getCurrentFrame();
        }else{
            return null; //Assets.playerDown[0];
        }
    }
}
