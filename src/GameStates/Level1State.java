package GameStates;

import Handlers.ThreadPool;
import MainG.Handler;
import java.awt.Graphics2D;

public class Level1State extends GameState{

    ThreadPool pool;
    Handler handler;
    
    public Level1State(GameStateManager gsm, ThreadPool pool, Handler handler){
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
