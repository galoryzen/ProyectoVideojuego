package MainG;
/**
 * Es el main del programa
 * @version 1.0
 */
public class GameLauncher {
    
    private final static int widthG = 1080;
    private final static int heightG = 720; 
    public static Window window;
    /**
     * En el constructor se crea una instancia de ventana de dimension 1080x720
     */
    public GameLauncher(){
        this.window = new Window(widthG,heightG);
    }
    /**
     * El main llama a su constructor
     * @param args Estandar de java
     */
    public static void main(String[] args) {        
        new GameLauncher();      
    }
    
}
