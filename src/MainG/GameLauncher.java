package MainG;

public class GameLauncher {
    
    private final int widthG = 1080;
    private final int heightG = 720;
    
    
    public GameLauncher(){
        new Window(widthG,heightG);
    }
    
    public static void main(String[] args) {
        new GameLauncher();
    }
    
}
