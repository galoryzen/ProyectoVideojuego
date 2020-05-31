package MainLevel.WorldGenerator;

import Entities.EntityManager;
import Tilemaps.Tile;
import FirstMinigame.WorldGenerator.Util;
import GameStates.GameState;
import GameStates.World;
import MainG.Handler;
import MainLevel.Tiles.Chest;
import MainLevel.Tiles.TileMainLevel;
import MainLevel.Tiles.ElevatorTile;
import MainLevel.Tiles.TeleporterTile;
import java.awt.Graphics2D;

/**
 *
 * @author Omen
 */
public class WorldPlat extends World {

    private int width, height;
    private int[][] tiles;
    private int spawnX, spawnY;
    private EntityManager entityM;
    private String path;

    public WorldPlat(Handler handler, EntityManager entityM, String path, GameState state) {
        super(handler);
        this.entityM = new EntityManager(handler, state);
        loadWorld(path);
        this.path = path;
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
                TileMainLevel auxT = (TileMainLevel) getTile(x, y);
                if (auxT.isVisible()) {
                    getTile(x, y).render(g, (int) (x * TileMainLevel.TILEWIDTH), (int) (y * TileMainLevel.TILEHEIGHT));
                }
            }
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
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

    public void switchElevators(int switched) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileMainLevel auxT = (TileMainLevel) getTile(x, y);
                if (auxT instanceof ElevatorTile) {
                    if (switched % 2 != 0) {
                        auxT.buttonPressed();
                    } else {
                        auxT.buttonRelased();
                    }
                }
            }
        }
    }

    public void changeMap(String pathString) {
        this.path = pathString;
        loadWorld(pathString);
    }

    public int[] getPositionMap() {
        String file = Util.loadFileAsString(this.path);
        //\\s+ es cada espacio en blanco
        String[] tokens = file.split("\\s+");
        spawnX = Util.parseInt(tokens[2]);
        spawnY = Util.parseInt(tokens[3]);
        int[] position = new int[2];
        position[0] = spawnX;
        position[1] = spawnY;
        return position;
    }

    public void restoreElevators() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileMainLevel auxT = (TileMainLevel) getTile(x, y);
                if (auxT instanceof ElevatorTile) {
                    auxT.resetElevator();
                } else if (auxT instanceof Chest) {
                    auxT.resetInteraction();
                }
            }
        }
    }

    public void switchAllTheTiles() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TileMainLevel tile = (TileMainLevel) getTile(x, y);
                tile.changeTiles();
            }
        }
    }

}
