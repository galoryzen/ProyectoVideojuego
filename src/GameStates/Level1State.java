package GameStates;

import Entities.Creatures.Player_Joan;
import Entities.EntityManager;
import FirstMinigame.WorldGenerator.WorldLibrary;
import MainG.Handler;
import FirstMinigame.Level1UpManager;
import MainG.Window;
import java.awt.Graphics2D;

public class Level1State extends GameState {

    Handler handler;
    private WorldLibrary world;
    private String path = "Resources/Worlds/World1.txt";
    private Player_Joan joan;
    private Level1UpManager levelManager;
    boolean showedTutorial = false;
    private EntityManager entityManager;
    private boolean doingQuiz = false;

    public Level1State(GameStateManager gsm, Handler handler, String tag) {
        super(gsm);
        this.levelTag = tag;
        this.handler = handler;
        world = new WorldLibrary(this.handler, path, this);
        this.levelManager = new Level1UpManager(this, world, world.entityM);
        init();
    }

    @Override
    public void init() {
        System.out.println("QUE");
    }

    @Override
    public void update() {
        if (Window.keyManager.debug) {
            setGameFinished();
        }
        world.update();
        levelManager.update();
    }

    public void setGameFinished() {
        this.gsm.setState(5);
    }

    @Override
    public void draw(Graphics2D g) {
        world.render(g);
    }

    @Override
    public void musicControl() {

    }

    public World getWorld() {
        return world;
    }

    @Override
    public void getInsertData() {

    }

    @Override
    public void getLoadData() {
    }
}
