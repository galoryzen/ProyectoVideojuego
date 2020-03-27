package Entities.Items;

import Entities.Creatures.Player;
import Entities.Entity;
import Entities.EntityManager;
import Tilemaps.Assets;
import MainG.GamePanel;
import MainG.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author German David
 */
public class Bullet extends Entity {

    private int X;
    private int Y;
    private int BulletSpeed = 8;

    public Bullet(Handler handler, EntityManager manager, float x, float y, int width, int height) {

        super(handler, manager, x, y, width, height);

        bounds.x = 2;
        bounds.y = 10;
        bounds.width = 46;
        bounds.height = 22;

    }

    @Override
    public void update() {

        move();
        checkAttacks();

    }

    public void move() {
        x += BulletSpeed;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) (bounds.x + x), (int) (bounds.y + y), bounds.width, bounds.height);
        g.drawImage(Assets.bullet, (int) x, (int) y, null);
    }

    public int getBulletSpeed() {
        return BulletSpeed;
    }

    public void setBulletSpeed(int BulletSpeed) {
        this.BulletSpeed = BulletSpeed;
    }

    @Override
    public void die() {

    }

    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
        System.out.println("" + cb.x + " " + cb.y + " " + cb.width + " " + cb.height);
        for (Entity e : manager.getEntities()) {
            if (!(e instanceof Bullet) && !(e instanceof Player)) {
                if (e.getCollisionBounds().intersects(cb)) {
                    e.hurt(1);
                    this.setActive(false);
                    System.out.println("Me golpeé con " + e);
                }
            }
        }
    }

}
