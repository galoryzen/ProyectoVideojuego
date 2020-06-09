package Entities.Items;

import Entities.Creatures.Asteroid;
import Entities.Creatures.Creature;
import Entities.Creatures.DownEnemy;
import Entities.Creatures.Enemy;
import Entities.Creatures.Player;
import Entities.Entity;
import Entities.EntityManager;
import Tilemaps.Assets;
import MainG.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Balas del usuario en el Space Invaders.
 * @author German David
 */
public class Bullet extends Entity {

    private int X;
    private int Y;
    private int BulletSpeed = 500;
    private Creature creature;
    
    /**
     * Constructor de Bullet.
     * @param handler Handler.
     * @param manager EntityManager.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Alto.
     * @param creature Creatura que dispara la bala.
     */
    public Bullet(Handler handler, EntityManager manager, float x, float y, int width, int height, Creature creature) {
        super(handler, manager, x, y, width, height);

        bounds.x = 2;
        bounds.y = 10;
        bounds.width = 46;
        bounds.height = 22;
        this.creature = creature;

    }
    /**
     * Actualiza el estado de la bala.
     */
    @Override
    public void update() {
        move();
        checkAttacks();

    }
    
    /**
     * Movimiento de la bala.
     */
    public void move() {
        if (this.creature instanceof Player) {
            x += BulletSpeed*handler.getDeltaTime();
        } else {
            x -= BulletSpeed*handler.getDeltaTime();
        }

    }
    
    /**
     * Reenderiza la bala.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics2D g) {
        if(creature instanceof Player)
         g.drawImage(Assets.bullet, (int) x, (int) y, null);
        else if(creature instanceof DownEnemy)
            g.drawImage(Assets.pursoidBullet, (int) x, (int) y, null);
    }

    public int getBulletSpeed() {
        return BulletSpeed;
    }

    public void setBulletSpeed(int BulletSpeed) {
        this.BulletSpeed = BulletSpeed;
    }
    
    /**
     * Metodo que se ejecuta cuando muere.
     */
    @Override
    public void die() {

    }
    
    /**
     * Revisa con quien se golpeó.
     */
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
        for (Entity e : manager.getEntities()) {
            if (!(e instanceof Bullet) && !(e.equals(this.creature))) {
                if (e.getCollisionBounds().intersects(cb)) {
                    if (this.creature instanceof Player) {
                        if (e instanceof Asteroid || e instanceof Enemy) {
                            e.hurt(5);
                            this.setActive(false);
                        }
                    } else if (this.creature instanceof Enemy) {
                        if (!(e instanceof Asteroid)) {
                            e.hurt(5);
                            this.setActive(false);
                        }
                    }
                }
            }
        }
    }
    
}
