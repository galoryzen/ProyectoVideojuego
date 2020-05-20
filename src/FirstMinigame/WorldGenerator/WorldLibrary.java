package FirstMinigame.WorldGenerator;

import Entities.Static.BookInfo;
import Entities.Creatures.Player_Joan;
import Entities.Creatures.Sentinel;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Static.BookPile;
import FirstMinigame.Tiles.TileLibrary;
import Tilemaps.Tile;
import GameStates.GameState;
import GameStates.World;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
    private ArrayList<BookPile> bookpiles;
    private EntityManager entityM;
    public static int bookcount=0;
    

    public WorldLibrary(Handler handler, String path, GameState state) {
        super(handler);
        this.handler = handler;
        entityM = new EntityManager(handler, state);
        entityM.addEntity(new BookPile(handler, entityM, 100, 60, new BookInfo(entityM,Assets.vida,handler,500,100,100,100)));
        entityM.addEntity(new BookPile(handler, entityM, 500, 150,new BookInfo(entityM,Assets.vida,handler,500,100,100,100)));
        entityM.addEntity(new BookPile(handler, entityM, 50, 20,new BookInfo(entityM,Assets.vida,handler,500,100,100,100)));
        entityM.addEntity(new BookPile(handler, entityM, 0, 0,new BookInfo(entityM,Assets.vida,handler,500,100,100,100)));
        entityM.addEntity(new Sentinel(handler, entityM,150,100,40,40));
        
        
        loadWorld(path);
        entityM.getJoan().setX(spawnX);
        entityM.getJoan().setY(spawnY);
    }

    public void update() {
        entityM.update();
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
        entityM.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        Tile t = TileLibrary.tiles[tiles[x][y]];
        if (t == null) {
            return TileLibrary.floor;
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
        return entityM;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityM = entityManager;
    }

}
