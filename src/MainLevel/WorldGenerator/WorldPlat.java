package MainLevel.WorldGenerator;

import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import Tilemaps.Tile;
import FirstMinigame.WorldGenerator.Util;
import GameStates.GameState;
import GameStates.World;
import MainG.Handler;
import MainLevel.Tiles.EmptyTile;
import MainLevel.Tiles.TileMainLevel;
import java.awt.Graphics2D;

/**
 *
 * @author Omen
 */
public class WorldPlat extends World {

    private Handler handler;
    private int width, height;
    private int[][] tiles;
    private int spawnX, spawnY;
    private EntityManager entityM;
    private final int floorHeight = 550;
    private MainPlayer player;

    public WorldPlat(Handler handler, EntityManager entityM, String path, GameState state) {
        super(handler, 9, 60);
        this.entityM = new EntityManager(handler, state);
        this.handler = handler;
        loadWorld(path);
    }

    @Override
    public void update() {
        entityM.update();
    }

    @Override
    public void render(Graphics2D g) {     
        g.clearRect(0, 0, 1080, 720);
        generateScenario(g);
        entityM.render(g);
    }

    public void generateScenario(Graphics2D g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!(getTile(x, y) instanceof EmptyTile)) {
                    getTile(x, y).render(g, (int) (x * TileMainLevel.TILEWIDTH), (int) (y * TileMainLevel.TILEHEIGHT));
                }
            }
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height){
            return null;
        }
        Tile t = TileMainLevel.tiles[tiles[x][y]];
        if (t == null) {
            return TileMainLevel.emptyTile;
        }
        return t;
    }

    public void loadWorld(String path) {
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

}
