package ThirdMinigame;

import Entities.Creatures.AerialEnemy;
import Entities.Creatures.Asteroid;
import Entities.Creatures.Boss;
import Entities.Creatures.DownEnemy;
import Entities.Creatures.Player;
import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import java.awt.Graphics;

/**
 *
 * @author German David, Isaac Blanco
 */
public class World {

    private EntityManager manager;
    private Handler handler;
    private HUD hud;
    private Level3UpManager managerL;

    private DownEnemy enemy1;
    private AerialEnemy enemy2;
    private Boss boss;
    private boolean e1 = false, e2 = false, generateEnemys = false, e3 = false;

    public World(EntityManager manager, Handler handler, Level3UpManager managerL) {
        this.manager = manager;
        this.handler = handler;
        this.managerL = managerL;
    }

    public void update() {
        int j = 0;
        if (generateEnemys && managerL.getPhase() != 3) {
            generateAsteroids(j);
            if (managerL.getPhase() == 1 || managerL.getPhase() == 2) {
                generateEnemy1();
                if (managerL.getPhase() == 2) {
                    generateEnemy2();
                }
            }
        } else {
            if (managerL.getPhase() == 3) {
                generateBoss();
            }
        }
        manager.update();
    }

    public void render(Graphics g) {
        manager.render(g);
    }

    public void setHUD(HUD hud) {
        this.hud = hud;
    }

    public void generateAsteroids(int j) {
        for (Entity e : manager.getEntities()) {
            if (e instanceof Asteroid) {
                j++;
            }
        }
        if (j < 4) {
            for (int i = j; i <= 4; i++) {
                manager.addEntity(new Asteroid(handler, manager, 540, (float) (Math.random() * 300 + 60), 100, 100, this.hud));
            }
        }
    }

    public void generateEnemy1() {
        if (!e1) {
            enemy1 = new DownEnemy(handler, manager, 450, 40, 65, 21, this.hud);
            manager.addEntity(enemy1);
            e1 = !e1;
        } else {
            if (enemy1.isActive() == false) {
                e1 = !e1;
            }
        }
    }

    public void generateEnemy2() {
        if (!e2) {
            enemy2 = new AerialEnemy(handler, manager, (int) Math.random() * 500 + 40, 0, 60, 36, this.hud);
            manager.addEntity(enemy2);
            e2 = !e2;
        } else {
            if (enemy2.isActive() == false) {
                e2 = !e2;
            }
        }
    }

    public void generateBoss() {
        if (!e3) {
            boss = new Boss(handler, manager, 450, 40, 65, 21, this.hud);
            manager.addEntity(boss);
            e3 = !e3;
        }
    }

    public void generateEnemys() {
        generateEnemys = !generateEnemys;
    }

    public void setGenerateEnemys(boolean status) {
        generateEnemys = status;
    }

    public void clearScreenEntities() {
        for (Entity e : manager.getEntities()) {
            if (!(e instanceof Player)) {
                e.setActive(false);
            }
        }
    }
}
