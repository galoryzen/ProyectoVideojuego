package MainG;


import Entities.EntityManager;
import GameStates.World;
import GameStates.GameCamara;
import GameStates.GameStateManager;
import Handlers.KeyManager;
import java.io.Serializable;

/**
 *
 * @author German David
 */

public class Handler{
    
    private GamePanel game;
    private GameStateManager gsm;
    
    public Handler (GamePanel game){
        this.game=game;
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }

    public GamePanel getGame() {
        return game;
    }

    public void setGame(GamePanel game) {
        this.game = game;
    }

    public GameCamara getGameCamara(){
        return this.game.getGameCamara(); 
    }

    public void setGSM(GameStateManager gsm){
        this.gsm = gsm;
    }
    
    public World getWorld(){
       return gsm.getWorld();
    }
    
    public double getDeltaTime(){
        return gsm.getDeltaTime() * 0.000000001;
    }
}
