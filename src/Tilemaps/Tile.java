package Tilemaps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */
//Cada uno de los pedazos en el mundo
public abstract class Tile {

    //Class
    public static int TILEWIDTH = 120, TILEHEIGHT = 120;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
    }

    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    // Es caminable?
    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
    
    public void changeTile(BufferedImage texture){
        this.texture = texture;
    }
}
