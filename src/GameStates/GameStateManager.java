package GameStates;

import java.util.ArrayList;
import Handlers.detectorTeclas;

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;
    public detectorTeclas teclas = new detectorTeclas();

    private final int MENUSTATE = 0;
    private final int MAINLEVELSTATE = 1;
    private final int LEVEL1STATE = 2;
    private final int LEVEL2STATE = 3;
    private final int LEVEL3STATE = 4;

    public GameStateManager() {
        gameStates = new GameState[5];
        currentState = MENUSTATE;
        loadState(MENUSTATE);
    }

    public void setState(int state) {
        currentState = state;
        loadState(currentState);
    }

    // Funcion encargada de cargar los State, controlador de niveles
    private void loadState(int state) {
        switch (state) {
            case MENUSTATE:
                gameStates[state] = new MenuState(this);
                break;
            case MAINLEVELSTATE:
                gameStates[state] = new MainLevel(this);
                break;
            case LEVEL1STATE:
                gameStates[state] = new Level1State(this);
                break;
            case LEVEL2STATE:
                gameStates[state] = new Level2State(this);
                break;
            default:
                gameStates[state] = new Level3State(this);
                break;
        }
    }

    public void update() {
        gameStates[currentState].update();
        if(teclas.ACTION.esPresionada){
            setState(4);
        }
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates[currentState].draw(g);
    }

    public int inGameState() {
        return currentState;
    }
}
