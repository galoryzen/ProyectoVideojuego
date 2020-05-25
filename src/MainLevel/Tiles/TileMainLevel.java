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

    public boolean isSolid = false;
    public boolean isVisible = true;
    public boolean isInteractive = false;

    //Arreglo para guardar los diferentes tipos de Tile
    public static Tile[] tiles = new Tile[256];
    public static Tile emptyTile = new EmptyTile(0);
    public static Tile retroFloor = new FloorTile(1);
    public static Tile rowTile = new RowTile(2);
    public static Tile platformTile = new PlatformTile(3);
    public static Tile chest = new Chest(4);
    public static Tile lapTile = new LapTile(5);
    public static Tile elevatorTile = new InElevator(6);
    public static Tile buttonElevatorTile = new ButtonElevator(7);
    public static Tile spikeTile = new SpikeTile(8);

    //Class
    public TileMainLevel(BufferedImage texture, int id) {
        super(texture, id);
        tiles[id] = this;
    }

    public boolean makeDamage() {
        return false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public boolean isSolid() {
        return isSolid;
    }

    public void buttonPressed() {
        this.isVisible = false;
        this.isSolid = true;
    }
    
    public void resetElevator(){
        this.isSolid = false;
        this.isVisible = true;
    }
    
    
    // Interaccionas?
    public boolean isInteractive() {
        return isInteractive;
    }
   
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, this.TILEWIDTH, this.TILEHEIGHT, null);
    }

    public void changeInteraction() {
        this.isInteractive = true;
    }
    
    public void resetInteraction(){
        this.isInteractive = false;
    }
}
