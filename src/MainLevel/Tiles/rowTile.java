package MainLevel.Tiles;

import Tilemaps.Assets;

public class rowTile extends TileMainLevel {

    public rowTile(int id) {
        super(Assets.rowTile, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
