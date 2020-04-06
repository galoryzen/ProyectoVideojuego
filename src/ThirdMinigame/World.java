package ThirdMinigame;

import Entities.Creatures.AerialEnemy;
import Entities.Creatures.Asteroid;
import Entities.Creatures.Boss;
import Entities.Creatures.DownEnemy;
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
    private DownEnemy enemy1;
    private int enemys = 0;
    
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
                    manager.addEntity(new Asteroid(handler, manager, 540, (float) (Math.random() * 300 + 60), 100, 100,this.hud));
                }
            }
        }
        
        if(hud.getPoint() >= 5 && enemys != 1){
            manager.addEntity(new DownEnemy(handler,manager,450,40,65,21,this.hud));
            enemys = 1;
        }
//        
//        if(hud.getPoint() >= 10){
//        
//        }
//        manager.addEntity(new DownEnemy(handler,manager,450,40,65,21,this.hud));
//        manager.addEntity(new AerialEnemy(handler,manager,(int) Math.random() * 500 + 40,0,60,36,this.hud));
//        manager.addEntity(new Boss(handler,manager,450,40,65,21,this.hud));
      
        manager.update();
    }

    public void render(Graphics g) {
        manager.render(g);
    }
    
    public void setHUD(HUD hud){
        this.hud = hud;
    }
}
