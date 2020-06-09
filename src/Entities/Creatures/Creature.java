package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import java.awt.Graphics;

/**
 * Clase Creature de donde heredan Character y Enemy.
 * @author Raul
 */
public abstract class Creature extends Entity{
    
    protected float Xmove=0,Ymove=0;
    protected int health;
    protected float speed;
    
    public static final int DEFAULT_HEALTH=10;
    public static final float DEFAULT_SPEED=5.0f;
    public static final int DEFAULT_CREATURE_WIDTH=131;
    public static final int DEFAULT_CREATUR_HEIGHT=110;
    
    /**
     * Constructor de Creature.
     * @param handler Handler.
     * @param manager Entity manager al que pertenece.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Anchura.
     * @param height Altura.
     */
    public Creature(Handler handler,EntityManager manager,float x, float y,int width, int height) {
        super(handler,manager,x,y,width,height);
        speed=DEFAULT_SPEED;
    }
    
    /**
     * Mueve a Creature.
     */
    public void move(){
        x +=Xmove;
        y+=Ymove;
    }
//Getters and setters
    public float getXmove() {
        return Xmove;
    }

    public void setXmove(float Xmove) {
        this.Xmove = Xmove;
    }

    public float getYmove() {
        return Ymove;
    }

    public void setYmove(float Ymove) {
        this.Ymove = Ymove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    
}
