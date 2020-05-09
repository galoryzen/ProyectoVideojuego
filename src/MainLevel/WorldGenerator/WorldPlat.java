package MainLevel.WorldGenerator;

import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import FirstMinigame.Tiles.Tile;
import FirstMinigame.WorldGenerator.Util;
import GameStates.GameState;
import GameStates.World;
import MainG.Handler;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Omen
 */
public class WorldPlat extends World {

    private Handler handler;
    private EntityManager entityM;
    private final int floorHeight = 550;
    private MainPlayer player;
    
    public WorldPlat(Handler handler, EntityManager entityM, String path, GameState state) {
        super(handler);
        this.entityM = new EntityManager(handler,state);
        this.handler = handler;
    }

    @Override
    public void update() {
        entityM.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.clearRect(0, 0, 1080, 720);
        g.setColor(Color.red);
        g.fillRect(0, floorHeight, 1080, 300);
        entityM.render(g);
    }

    public float getHeight() {
        return 450;
    }

    public void generateScenario(String path) {
        String file = Util.loadFileAsString(path);
    }

    @Override
    public Tile getTile(int x, int y) {
        return null;
    }
}
