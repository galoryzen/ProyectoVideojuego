package Entities.Creatures;

import Audio.AudioLoader;
import Entities.Entity;
import Entities.EntityManager;
import Tilemaps.Assets;
import MainG.Handler;
import SecondMinigame.HUD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import tinysound.Music;

/**
 *
 * @author German David
 */

public class Asteroid extends Creature {

    private HUD hud;
    private static Music asteroidDamage;

    public Asteroid(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height);

        this.hud = hud;
        this.setHealth(25);

        this.width = (int) (Math.random() * 70 + 30);
        this.height = this.width;
        Xmove = (int) (Math.random() * 3 + 1);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = this.width;
        bounds.height = this.height;
        asteroidDamage = AudioLoader.damageAsteroid;
    }

    @Override
    public void die() {
        if (!(this.x <= 0)) {
            hud.setPoint(hud.getPoint() + 1);
            asteroidDamage.play(false);
        }
    }

    @Override
    public void update() {
        move();
        checkAttacks();
    }

    @Override
    public void move() {
        this.x -= Xmove;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.asteroids, (int) x, (int) y, width, height, null);
    }

    public void checkAttacks() {

        Rectangle cb = getCollisionBounds();

        for (Entity e : manager.getEntities()) {
            if (!e.equals(this)) {
                if (e.getCollisionBounds().intersects(cb)) {
                    //Por si son asteorides, se destruye el mas pequeño
                    if ((e instanceof Asteroid)) {
                        if (e.getWidth() * e.getHeight() < this.getWidth() * this.getHeight()) {
                            Asteroid asteroid = (Asteroid) e;
                            asteroid.Xmove = Xmove + 0.5f;
                        }
                    } else {
                        if (!(e instanceof Enemy)) {
                             e.hurt(5);
                            hud.setPoint(hud.getPoint() + 1);
                            asteroidDamage.play(false);
                            this.setActive(false);
                        }
                        //Si no es un asteroide, le hace 5 de daño                            
                    }
                }
            }
        }
    }
}