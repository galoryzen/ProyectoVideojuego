package FirstMinigame.Tiles;

import FirstMinigame.Tiles.LibraryTile;
import Tilemaps.Tile;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */

//Cada uno de los pedazos en el mundo
public class TileLibrary extends Tile{

    //STATIC STUFF
    //Arreglo para guardar los diferentes tipos de Tile
    public static Tile[] tiles = new Tile[256];
    public static TileLibrary library = new LibraryTile(0);
    public static TileLibrary floor = new FloorTile(1);
    
    //Class
    public TileLibrary(BufferedImage texture, int id) {
        super(texture,id);
        tiles[id] = this;
    }
}
