package GameStates;

import MainG.Handler;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class GameState{

    protected GameStateManager gsm;
    protected Handler handler;
    
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    
    public abstract void init();
  
    public abstract void update();
    
    public abstract void draw(java.awt.Graphics2D g);
      
    public abstract void musicControl();
}
