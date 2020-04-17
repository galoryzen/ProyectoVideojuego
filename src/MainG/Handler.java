package MainG;


import Entities.EntityManager;
import GameStates.GameCamara;
import Handlers.KeyManager;
import FirstMinigame.WorldGenerator.World;
import GameStates.GameState;
import GameStates.GameStateManager;

/**
 *
 * @author German David
 */

public class Handler {
    
    private EntityManager manager;
    private GamePanel game;
    
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
        return game.getGameCamara();
    }
    
}
