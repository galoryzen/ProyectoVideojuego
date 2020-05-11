package Entities;


import MainG.GamePanel;
import MainG.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Clase abstracta entidad.
 * Es la clase de donde heredan todas las entidades de el juego.
 */
public abstract class Entity{
    
    protected Handler handler;
    public static final int DEFAULTHEALTH=5;
    
    //Identificador del nivel
    private String levelTag;
    //Posición
    protected float x,y;
    
    //Hitbox
    protected int width,height;
    protected Rectangle bounds;
    
    //Vida
    protected int health;
    protected boolean active=true;
    
    //EntityManager
    protected EntityManager manager; 
    
    /**
     * Constructor de la entidad.
     * @param handler El handler del juego.
     * @param manager El entity manager al que está vinculado.
     * @param x Coordenada X de la entidad.
     * @param y Coordenada en Y de la entidad.
     * @param width Ancho de la entidad.
     * @param height Altura de la entidad.
     */
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
    
    /**
     * Metodo que disminuye la vida de la entidad.
     * @param amt la cantidad de vida que se le va a restar a la entidad.
     */
    public void hurt(int amt){
        health -= amt;
        if(health<= 0){
            active=false;
            die();
        }
    }
    
    /**
     * Metodo para que la entidad muera.
     */
    public abstract void die();
    
    /**
     * Metodo para actualizar la entidad.
     */
    public abstract void update();
    
    /**
     * Metodo para dibujar o reenderizar la entidad.
     * @param g Los graficos.
     */
    public abstract void render(Graphics2D g);
    
    /**
     * Metodo para crear el hitbox de la entidad.
     * @return El hitbox de la entidad
     */
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
