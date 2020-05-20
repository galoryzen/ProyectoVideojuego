package FirstMinigame.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author German David
 */
public class LibraryTile extends TileLibrary{

    public LibraryTile( int id) {
        super(Assets.library, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
