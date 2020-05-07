package GameStates;

import MainG.Handler;

public abstract class GameState{

    protected GameStateManager gsm;
    protected Handler handler;
    protected World world;
    protected String levelTag;
    
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    
    public abstract void init();
  
    public abstract void update();
    
    public abstract void draw(java.awt.Graphics2D g);
      
    public abstract void musicControl();

    public abstract World getWorld();
    
    public String getTag(){
        return levelTag;
    }
}
