package MainLevel.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author Omen
 */
public class FloorTile extends TileMainLevel {

    public FloorTile(int id) {
        super(Assets.retroFloor, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
