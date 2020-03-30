package GameStates;

import Handlers.ThreadPool;
import MainG.Handler;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadPoolExecutor;

public class MainLevel extends GameState{
    
    ThreadPoolExecutor pool;
    Handler handler;
    
    public MainLevel(GameStateManager gsm, ThreadPoolExecutor pool, Handler handler){
        super(gsm);
    }
    
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }


    @Override
    public void musicControl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
