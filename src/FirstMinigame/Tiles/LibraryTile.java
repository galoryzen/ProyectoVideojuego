package FirstMinigame.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author German David
 */
public class LibraryTile extends Tile{

    public LibraryTile( int id) {
        super(Assets.library, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
