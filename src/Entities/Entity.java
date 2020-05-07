package Entities;


import MainG.GamePanel;
import MainG.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author German David
 */
public abstract class Entity{
    
    protected Handler handler;
    public static final int DEFAULTHEALTH=5;
    private String levelTag;
    
    //posición
    protected float x,y;
    protected int width,height;
    protected Rectangle bounds;
    protected int health;
    protected boolean active=true;
    protected EntityManager manager;
    
    public Entity(Handler handler,EntityManager manager,float x, float y,int width, int height) {
        this.handler = handler;
        this.manager = manager;
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        
        health=DEFAULTHEALTH;
        
        bounds= new Rectangle();
    }
    
    public void hurt(int amt){
        health -= amt;
        if(health<= 0){
            active=false;
            die();
        }
    }
    
    public abstract void die();
    
    public abstract void update();
    
    public abstract void render(Graphics2D g);
    
    public Rectangle getCollisionBounds(){
        return new Rectangle ((int)(x+bounds.x),(int)(y+bounds.y),bounds.width,bounds.height);
    }
    
    public Rectangle getCollisionBounds(float xo, float yo){
        return new Rectangle ((int)(x+bounds.x+xo),(int)(y+bounds.y+yo),bounds.width,bounds.height);
    }
    
    //GETTERS AND SETTERS
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    
    public String getTag(){
        return levelTag;
    }
    
    
    
    
}
