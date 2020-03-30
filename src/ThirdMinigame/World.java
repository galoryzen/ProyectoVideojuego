package ThirdMinigame;

import Entities.Creatures.Asteroid;
import Entities.Entity;
import Entities.EntityManager;
import GameStates.Level3State;
import Tilemaps.Assets;
import MainG.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author German David
 */
public class World {

    private EntityManager manager;
    private Handler handler;
    private boolean tutorial;

    public World(EntityManager manager, Handler handler) {
        this.manager = manager;
        this.handler = handler;
    }

    public void update(boolean tutorial) {
        int j = 0;
        if (!tutorial) {
            for (Entity e : manager.getEntities()) {
                if (e instanceof Asteroid) {
                    j++;
                }
            }
            if (j < 8) {
                for (int i = j; i <= 8; i++) {
                    manager.addEntity(new Asteroid(handler, manager, 800, (float) (Math.random() * 550 + 50), 100, 100));
                }
            }
        }
        manager.update();
    }

    public void render(Graphics g) {
        manager.render(g);
    }
}
