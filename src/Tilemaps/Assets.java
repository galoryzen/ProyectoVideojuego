package Tilemaps;

import java.awt.image.BufferedImage;

public class Assets {
    
    public static BufferedImage fondoMenu,fondoSpaceInvaders,spriteNina, fondoSpaceInvadersGif;
    
    private static final int WIDHT = 131;
    private static final int HEIGHT = 110;
    
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Tilesets/Sheet.png"));  
        spriteNina = sheet.crop(0, 0, WIDHT, HEIGHT);
        fondoMenu = ImageLoader.loadImage("/Backgrounds/menu_gif.gif"); 
        fondoSpaceInvaders = ImageLoader.loadImage("/Backgrounds/spaceInvaders1.png");
    }
}
