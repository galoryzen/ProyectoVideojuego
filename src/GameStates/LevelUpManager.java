package GameStates;

import ThirdMinigame.DialogueLoader;
import ThirdMinigame.World;

public abstract class LevelUpManager {
   
    protected int point;
    protected int health;
    protected World world;
    protected DialogueLoader dialogueLoader;
    
    public abstract void levelUpManager();
    public abstract void changeMusic();
    public abstract void finishLevel();
    
    public void setWorld(World world){
        this.world = world;
    }
    public void setDialogueLoader(DialogueLoader dialogueLoader){
        this.dialogueLoader = dialogueLoader;
    }
}
