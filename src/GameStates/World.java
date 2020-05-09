package GameStates;

import Entities.EntityManager;
import FirstMinigame.Tiles.Tile;
import MainG.Handler;

/**
 *
 * @author Omen
 */

public abstract class World {

    private Handler handler;
    private EntityManager entityM;

    public World(Handler handler, EntityManager entityM){
        this.handler = handler;
        this.entityM = entityM;
    }
    
    public World(Handler handler){
        this.handler = handler;
    }
    
    public abstract Tile getTile(int x, int y);
    
    public abstract void update();
    public abstract void render(java.awt.Graphics2D g);
}
