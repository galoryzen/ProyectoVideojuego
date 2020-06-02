package GameStates;

import Entities.EntityManager;
import SecondMinigame.WorldSpace;

public abstract class LevelUpManager {

    protected int point;
    protected int health;
    protected World world;
    protected DialogueLoader dialogueLoader;
    protected EntityManager entityManager;

    public LevelUpManager(World world, EntityManager entityM) {
        this.world = world;
        this.entityManager = entityM;
    }

    public LevelUpManager(World world){
        this.world = world;
    }
    
    public abstract void levelUpManager();

    public abstract void changeMusic();

    public abstract void finishLevel();

    public void setWorld(WorldSpace world) {
        this.world = world;
    }

    public void setDialogueLoader(DialogueLoader dialogueLoader) {
        this.dialogueLoader = dialogueLoader;
    }

    public World getWorld() {
        return world;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
