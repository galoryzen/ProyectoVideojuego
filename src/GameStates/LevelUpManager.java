package GameStates;

import SecondMinigame.DialogueLoader;
import SecondMinigame.WorldSpace;

public abstract class LevelUpManager {
   
    protected int point;
    protected int health;
    protected WorldSpace world;
    protected DialogueLoader dialogueLoader;
    
    public abstract void levelUpManager();
    public abstract void changeMusic();
    public abstract void finishLevel();
    
    public void setWorld(WorldSpace world){
        this.world = world;
    }
    
    public void setDialogueLoader(DialogueLoader dialogueLoader){
        this.dialogueLoader = dialogueLoader;
    }
}
