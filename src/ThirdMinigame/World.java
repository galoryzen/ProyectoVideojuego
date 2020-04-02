package ThirdMinigame;

import Entities.Creatures.Asteroid;
import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import java.awt.Graphics;

/**
 *
 * @author German David
 */
public class World {

    private EntityManager manager;
    private Handler handler;
    private boolean tutorial;
    private HUD hud;
    
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
                    manager.addEntity(new Asteroid(handler, manager, 800, (float) (Math.random() * 550 + 50), 100, 100,this.hud));
                }
            }
        }
        manager.update();
    }

    public void render(Graphics g) {
        manager.render(g);
    }
    
    public void setHUD(HUD hud){
        this.hud = hud;
    }
}
