package GameStates;

import Entities.Creatures.Player_Joan;
import FirstMinigame.WorldGenerator.WorldLibrary;
import MainG.Handler;
import FirstMinigame.Level1UpManager;
import MainG.GameLauncher;
import MainG.Window;
import static MainG.Window.mouse;
import Tilemaps.Assets;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

public class Level1State extends GameState implements Runnable {

    Handler handler;
    private World world;
    private String path = "Resources/Worlds/World1.txt";
    private Player_Joan joan;
    private Level1UpManager levelManager;
    private DialogueLoader dialogueLoader;
    // Level1UpManager manager
    public Level1State(GameStateManager gsm, Handler handler, String tag) {
        super(gsm);
        this.levelTag = tag;
        this.handler = handler;
        world = new WorldLibrary(this.handler, path, this);
        dialogueLoader = new DialogueLoader(handler);
        dialogueLoader.setGameTag(this.levelTag);
        this.levelManager = new Level1UpManager(this, world, dialogueLoader);
        init();
    }

    @Override
    public void init() {
        System.out.println("QUE");
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
