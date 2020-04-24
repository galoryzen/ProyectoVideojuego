package MainG;


import Entities.EntityManager;
import FirstMinigame.WorldGenerator.World;
import GameStates.GameCamara;
import GameStates.GameStateManager;
import Handlers.KeyManager;

/**
 *
 * @author German David
 */

public class Handler {
    
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
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
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
    
}
