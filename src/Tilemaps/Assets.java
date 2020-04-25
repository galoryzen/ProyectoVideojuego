package Tilemaps;

import java.awt.image.BufferedImage;

public class Assets implements Runnable{

    public static BufferedImage astronautTalker, pursoid, fondoMenu, fondoSpaceInvaders,
            spriteNina, naveOff, naveOn, naveSemiOff, asteroids, bullet, laser, LaserAlien, enemy,
            vida, floor, library, BookPile;

    public static BufferedImage playerDown[] = new BufferedImage[9];
    public static BufferedImage playerUp[] = new BufferedImage[9];
    public static BufferedImage playerRight[] = new BufferedImage[9];
    public static BufferedImage playerLeft[] = new BufferedImage[9];

    private static final int WIDHT = 131;
    private static final int HEIGHT = 110;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/Sheet.png"));
        spriteNina = sheet.crop(0, 0, WIDHT, HEIGHT);
        fondoMenu = ImageLoader.loadImage("/Backgrounds/menu_gif.gif");
        fondoSpaceInvaders = ImageLoader.loadImage("/Backgrounds/spaceInvaders.png");
        naveOff = ImageLoader.loadImage("/Player/naveOff.png");
        naveOn = ImageLoader.loadImage("/Player/naveOn.png");
        astronautTalker = ImageLoader.loadImage("/HUD/HUD_DIALOGUE.png");
        enemy = ImageLoader.loadImage(("/Tilesets/Pursoid.png"));
        pursoid = ImageLoader.loadImage("/Tilesets/Pursoid.png");
        LaserAlien = ImageLoader.loadImage("/Tilesets/LaserAlien.png");
        laser = ImageLoader.loadImage("/Tilesets/laser.png");
        SpriteSheet sheetAsteroids = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/Sheetasteroids.png"));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                asteroids = sheetAsteroids.crop(126 * j, 91 * i, 126, 91);
            }
        }
        SpriteSheet sheetBullets = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/Sheetbullets.png"));
        for (int i = 0; i < 3; i++) {
            bullet = sheetBullets.crop(0, 30 * i, 46, 30);
        }
        SpriteSheet sheetVida = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/heatlhBar.png"));
        SpriteSheet playerM = new SpriteSheet(ImageLoader.loadImage("/Tilesets/DudeSprite.png"));
        cargarJoan(playerM);
        vida = sheetVida.crop(0, 0, 125, 201);
        floor = ImageLoader.loadImage("/Testers/Floor.png");
        library = ImageLoader.loadImage("/Testers/library.png");
        naveSemiOff = ImageLoader.loadImage("/Player/naveSemi.png");
        BookPile = ImageLoader.loadImage("/Testers/BookPile.png");
    }

    public static void cargarJoan(SpriteSheet sheet) {
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 9; k++) {
                switch (i) {
                    case 0:
                        playerUp[k] = sheet.crop(64 * k, 64 * i, 64, 64);
                        break;
                    case 1:
                        playerLeft[k] = sheet.crop(64 * k, 64 * i, 64, 64);
                        break;
                    case 2:
                        playerDown[k] = sheet.crop(64 * k, 64 * i, 64, 64);
                    case 3:
                        playerRight[k] = sheet.crop(64 * k, 64 * i, 46, 64);
                        break;
                }

            }
        }
    }
    
    @Override
    public void run(){
        init();
    }
}
