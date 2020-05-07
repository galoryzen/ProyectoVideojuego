package GameStates;

import Entities.Creatures.Player_Joan;
import Entities.EntityManager;
import FirstMinigame.Level1UpManager;
import FirstMinigame.WorldGenerator.WorldLibrary;
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

    // Level1UpManager manager
    public Level1State(GameStateManager gsm, Handler handler, String tag) {
        super(gsm);
        this.levelTag = tag;
        this.handler = handler;
        this.levelManager = new Level1UpManager();
        entityManager = new EntityManager(handler);
        world = new WorldLibrary(this.handler, entityManager, path,this);
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

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public World getWorld(){
        return world;
    }
}
