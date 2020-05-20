package GameStates;

import Entities.EntityManager;
import Tilemaps.Tile;
import MainG.Handler;
import java.io.Serializable;

/**
 *
 * @author Omen
 */
public abstract class World {

    private Handler handler;
    private EntityManager entityM;
    private int height, width;

    public World(Handler handler, EntityManager entityM) {
        this.handler = handler;
        this.entityM = entityM;
    }

    public World(Handler handler, int height, int width) {
        this.handler = handler;
        this.height = height;
        this.width = width;
    }

    public abstract Tile getTile(int x, int y);

    public abstract void update();

    public abstract void render(java.awt.Graphics2D g);

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
