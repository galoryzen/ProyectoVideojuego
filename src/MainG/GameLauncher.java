package MainG;

public class GameLauncher {
    
    private final static int widthG = 1080;
    private final static int heightG = 720;    
    
    public GameLauncher(){
        new Window(widthG,heightG);
    }
    
    public static void main(String[] args) {        
        new GameLauncher();      
    }
    
}
