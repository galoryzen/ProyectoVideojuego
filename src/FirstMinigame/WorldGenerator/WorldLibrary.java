package FirstMinigame.WorldGenerator;

import Entities.Creatures.Player_Joan;
import Entities.EntityManager;
import Entities.Static.BookPile;
import FirstMinigame.Tiles.Tile;
import GameStates.GameState;
import GameStates.World;
import MainG.Handler;
import java.awt.Graphics2D;

/**
 *
 * @author German David
 */
public class WorldLibrary extends World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles = new int[100][100];
    //Entities
    private EntityManager entityManager;
    private Player_Joan player;
    

    public WorldLibrary(Handler handler, EntityManager entityM, String path, GameState state) {
        super(handler);
        this.handler = handler;
        entityManager = new EntityManager(handler, state);
        entityManager.addEntity(new BookPile(handler, entityM, 0, 0));
        entityManager.addEntity(new BookPile(handler, entityM, 0, 0));
        entityManager.addEntity(new BookPile(handler, entityM, 0, 0));
        entityManager.addEntity(new BookPile(handler, entityM, 0, 0));
        loadWorld(path);
        entityManager.getJoan().setX(spawnX);
        entityManager.getJoan().setY(spawnY);
    }

    public void update() {
        entityManager.update();
    }

    public void render(Graphics2D g) {
        //Varibales para crear las tiles visibles
        int Xstart = (int) Math.max(0, handler.getGameCamara().getxOffset() / Tile.TILEWIDTH);
        int Xend = (int) Math.min(width, (handler.getGameCamara().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int Ystart = (int) Math.max(0, handler.getGameCamara().getyOffset() / Tile.TILEHEIGHT);
        int Yend = (int) Math.min(height, (handler.getGameCamara().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        //g.drawImage(Assets.background, 0, 0, 800, 600, null);
        for (int y = Ystart; y < Yend; y++) {
            for (int x = Xstart; x < Xend; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamara().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamara().getyOffset()));
            }
        }
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.floor;
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Util.loadFileAsString(path);
        //\\s+ es cada espacio en blanco
        String[] tokens = file.split("\\s+");
        width = Util.parseInt(tokens[0]);
        height = Util.parseInt(tokens[1]);
        spawnX = Util.parseInt(tokens[2]);
        spawnY = Util.parseInt(tokens[3]);
        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Util.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    
    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
