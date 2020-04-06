package GameStates;

public abstract class LevelUpManager {
   
    protected int point;
    protected int health;
    
    public abstract void levelUpManager();
    public abstract void changeMusic();
    public abstract void finishLevel();
}
