package GameStates;

import Handlers.ThreadPool;
import java.util.ArrayList;
import Handlers.KeyManager;
import MainG.Handler;
import java.util.concurrent.ThreadPoolExecutor;

public class GameStateManager {
    
    public ThreadPoolExecutor pool;
    public Handler handler;
    
    public static final int NUMGAMESTATE = 5;
    private static GameState[] gameStates;
    private int currentState;
    
    private final int MENUSTATE = 0;
    private final int MAINLEVELSTATE = 1;
    private final int LEVEL1STATE = 2;
    private final int LEVEL2STATE = 3;
    private final int LEVEL3STATE = 4;

    public GameStateManager(ThreadPoolExecutor pool, Handler handler) {
        this.pool = pool;
        this.handler = handler;
        gameStates = new GameState[NUMGAMESTATE];
        currentState = MENUSTATE;
        loadState(currentState);
    }

    public void setState(int state) {
        unloadState(state);
        currentState = state;
        loadState(currentState);
    }
    
    public void unloadState(int state){
        gameStates[state] = null;
    }

    // Funcion encargada de cargar los State, controlador de niveles
    private void loadState(int state) {
        switch (state) {
            case MENUSTATE:
                gameStates[state] = new MenuState(this, this.pool, this.handler);
                break;
            case MAINLEVELSTATE:
                gameStates[state] = new MainLevel(this, this.pool, this.handler);
                break;
            case LEVEL1STATE:
                gameStates[state] = new Level1State(this, this.pool, this.handler);
                break;
            case LEVEL2STATE:
                gameStates[state] = new Level2State(this, this.pool, this.handler);
                break;
            case LEVEL3STATE:
                gameStates[state] = new Level3State(this, this.pool, this.handler);
                break;
        }
    }

    public void update() {
        gameStates[currentState].update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates[currentState].draw(g);
    }

    public int inGameState() {
        return currentState;
    }
}
