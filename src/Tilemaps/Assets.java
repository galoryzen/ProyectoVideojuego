package Tilemaps;

import java.awt.image.BufferedImage;

public class Assets implements Runnable {

    public static BufferedImage astronautTalker, pursoid, fondoMenu, fondoSpaceInvaders,
            spriteNina, naveOff, naveOn, naveSemiOff, asteroids, bullet, laser, LaserAlien, enemy,
            vida, floor, library, BookPile, AutoMissil, charge, pursoidBullet, halfLife, playerStand,
            retroFloor, rowTile, platTile, empty,chest;

    public static BufferedImage playerDown[] = new BufferedImage[2];
    public static BufferedImage playerUp[] = new BufferedImage[3];
    public static BufferedImage playerRight[] = new BufferedImage[2];
    public static BufferedImage playerLeft[] = new BufferedImage[2];

    public static BufferedImage aerialEnemy[] = new BufferedImage[4];
    public static BufferedImage downEnemy[] = new BufferedImage[4];

    public static BufferedImage Boss[] = new BufferedImage[2];

    public static BufferedImage backgroundMenu[] = new BufferedImage[60];

    private static final int WIDHT = 131;
    private static final int HEIGHT = 110;

    /**
     * El metodo init() de la clase assets carga todas las imagenes que necesitaremos para los minijuegos
     */
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/Sheet.png"));
        SpriteSheet DownEnemy = new SpriteSheet(ImageLoader.loadImage("/Tilesets/pursoidSprite.png"));
        SpriteSheet AerialEnemy = new SpriteSheet(ImageLoader.loadImage("/Tilesets/LaserAlienSprite.png"));
        SpriteSheet boss = new SpriteSheet(ImageLoader.loadImage("/Tilesets/BossSpriteH.png"));
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
            switch (i) {
                case 0:
                    AutoMissil = sheetBullets.crop(0, 30 * i, 46, 30);
                    break;
                case 1:
                    bullet = sheetBullets.crop(0, 30 * i, 46, 30);
                    break;
                case 2:
                    pursoidBullet = sheetBullets.crop(0, 30 * i, 46, 30);
                    break;
            }
        }

        for (int i = 1; i <= 54; i++) {
            backgroundMenu[i] = ImageLoader.loadImage("/Backgrounds/frame-" + (i + 1) + ".gif");
        }

        for (int i = 0; i < 4; i++) {
            aerialEnemy[i] = AerialEnemy.crop(i * 110, 0, 110, 99);
        }

        for (int i = 0; i < 4; i++) {
            downEnemy[i] = DownEnemy.crop(i * 102, 0, 102, 110);
        }

        for (int i = 0; i < 2; i++) {
            Boss[i] = boss.crop(i * 125, 0, 125, 120);
        }
        charge = boss.crop(250, 0, 125, 120);

        SpriteSheet sheetVida = new SpriteSheet(ImageLoader.loadImage("/Sprites/Tilesets/heatlhBar.png"));
        SpriteSheet playerM = new SpriteSheet(ImageLoader.loadImage("/Sprites/Player/JoanSprite.png"));
        cargarJoan(playerM);
        vida = sheetVida.crop(0, 0, 125, 201);
        halfLife = sheetVida.crop(266, 0, 125, 201);
        floor = ImageLoader.loadImage("/Testers/Floor.png");
        library = ImageLoader.loadImage("/Testers/library.png");
        naveSemiOff = ImageLoader.loadImage("/Player/naveSemi.png");
        BookPile = ImageLoader.loadImage("/Testers/BookPile.png");
        retroFloor = ImageLoader.loadImage("/SpritesMainLevel/GameboyAssets/Tile_1.png");
        rowTile = ImageLoader.loadImage("/SpritesMainLevel/GameboyAssets/tile_12.png");
        platTile = ImageLoader.loadImage("/SpritesMainLevel/GameboyAssets/tile_15.png");
        empty = ImageLoader.loadImage("/Tilesets/empty.png");
        chest = ImageLoader.loadImage("/SpritesMainLevel/GameboyAssets/Chest_1.png");
    }

    /**
     * Metodo para cargar el gif de Joan Clarke.
     *
     * @param sheet Sprite Sheet de Joan Clarke.
     */
    public static void cargarJoan(SpriteSheet sheet) {
        int j = 0;
        for (int i = 0; i < 12; i++) {
            if (i < 3) {
                playerUp[i] = sheet.crop(i * 36, 0, 36, 36);
            } else if (i < 5) {
                playerDown[j] = sheet.crop(i * 36, 0, 36, 36);
                j++;
            } else if (i < 10 && i > 7) {
                playerLeft[j - 2] = sheet.crop(i * 36, 0, 36, 36);
                j++;
            } else if (i > 9) {

                playerRight[j - 4] = sheet.crop(i * 36, 0, 36, 36);
                j++;
            } else {
                switch (i) {
                    case 5:
                        playerStand = sheet.crop(i * 36, 0, 36, 36);
                }
            }
        }
    }

    public static void cargarMainPlayer(SpriteSheet sheet) {
        int a = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                // CODIGO
            }
        }
    }

    @Override
    public void run() {
        init();
    }
}
