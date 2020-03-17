package GameStates;

import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    
    private final int MENUSTATE = 0;
    private final int MAINLEVELTATE = 1;
    private final int LEVEL1STATE = 2;
    private final int LEVEL2STATE = 3;
    private final int LEVEL3STATE = 4;
    
    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
    }
    
    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void update(){
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }
    
    public int inGameState(){
        return currentState;
    }
}
