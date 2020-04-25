package GameStates;

import Entities.Creatures.Player_Joan;
import Entities.EntityManager;
import FirstMinigame.Level1UpManager;
import FirstMinigame.WorldGenerator.World;
import MainG.Handler;
import FirstMinigame.Level1UpManager;
import java.awt.Graphics2D;

public class Level1State extends GameState implements Runnable {

    Handler handler;
    private World world;
    private EntityManager entityManager;
    private String path = "Resources/Worlds/World1.txt";
    private Player_Joan joan;
    private Level1UpManager levelManager;

    public Level1State(GameStateManager gsm, Handler handler, Level1UpManager manager) {
        super(gsm);
        this.handler = handler;
        this.levelManager = manager;
        entityManager = new EntityManager(handler, joan);
        this.world = new World(this.handler, entityManager, path);
        init();
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void draw(Graphics2D g) {
        world.render(g);
    }

    @Override
    public void musicControl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public World getWorld() {
        return this.world;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
