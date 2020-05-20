package MainLevel.Tiles;

import Tilemaps.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Omen
 */
public class TileMainLevel extends Tile {

    //STATIC STUFF
    public static int TILEWIDTH = 36, TILEHEIGHT = 36;
    
    //Arreglo para guardar los diferentes tipos de Tile
    public static Tile[] tiles = new Tile[256];
    public static Tile emptyTile = new EmptyTile(0);
    public static Tile retroFloor = new FloorTile(1);
    public static Tile rowTile = new rowTile(2);
    public static Tile platformTile = new PlatformTile(3);
    public static Tile chest = new Chest(4);
    
    
    //Class
    public TileMainLevel(BufferedImage texture, int id) {
        super(texture, id);
        tiles[id] = this;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, this.TILEWIDTH, this.TILEHEIGHT, null);
    }
    
}
