package MainLevel.Tiles;

import Tilemaps.Assets;

public class PlatformTile extends TileMainLevel {

    public PlatformTile(int id) {
        super(Assets.platTile, id);
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public void changeTiles() {
        this.texture = Assets.spaceWall2;
    }

}
