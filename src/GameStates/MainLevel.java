package GameStates;

import Audio.AudioLoader;
import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import MainG.Handler;
import MainLevel.MainLevelUpManager;
import MainLevel.WorldGenerator.WorldPlat;
import java.awt.Graphics2D;

public class MainLevel extends GameState {

    private Handler handler;
    private MainPlayer mainPlayer;
    private EntityManager entityManager;
    private World world;
    private MainLevelUpManager levelManager;
        
    private String path = "Resources/Worlds/WorldThematic1.txt";

    public MainLevel(GameStateManager gsm, Handler handler, String tag) {
        super(gsm);
        this.levelTag = tag;
        entityManager = new EntityManager(handler);
        this.world = new WorldPlat(handler, entityManager, path, this);
        this.levelManager = new MainLevelUpManager(world,entityManager,this);
        init();
    }

    @Override
    public void init() {
        levelManager.setMusic(AudioLoader.musicPlayListMainLevel);
    }

    @Override
    public void update() {
        world.update();
        levelManager.levelUpManager();
    }

    @Override
    public void draw(Graphics2D g) {
        world.render(g);
    }

    @Override
    public void musicControl() {

    }

    @Override
    public World getWorld() {
        return world;
    }

    public String pathString(int Stage){
        switch(Stage){
            case 1: 
                return "Resources/Worlds/WorldThematic1.txt";
            case 2:
                return "Resources/Worlds/WorldThematic2.txt";
            default:
                return "ERROR";
        }
    }
    
    @Override
    public void getInsertData() {

    }

    @Override
    public void getLoadData() {

    }

}
