package GameStates;

import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import MainG.Handler;
import MainLevel.WorldGenerator.WorldPlat;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class MainLevel extends GameState{
    
    private Handler handler;
    private MainPlayer mainPlayer;    
    private EntityManager entityManager;
    private World WorldPlat;
    
    
    public MainLevel(GameStateManager gsm, Handler handler, String tag){
        super(gsm);
        this.levelTag = tag;
        entityManager = new EntityManager(handler);
        world = new WorldPlat(handler,entityManager,"null",this);
    }
    
    @Override
    public void init() {

    }

    @Override
    public void update() {
        //world.update();
        System.out.println("Papi queeeee");
    }

    @Override
    public void draw(Graphics2D g) {
       // world.render(g);
    }


    @Override
    public void musicControl() {

    }

    @Override
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
