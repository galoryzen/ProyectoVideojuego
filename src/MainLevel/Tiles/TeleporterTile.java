package MainLevel.Tiles;

import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Graphics;

/**
 *
 * @author Omen
 */
public class TeleporterTile extends TileMainLevel {

    private boolean newTile = false;
    
    public TeleporterTile(int id) {
        super(Assets.enigmaMachineTeleporter, id);
    }

    @Override
    public boolean isInteractive() {
        return !isInteractive;
    }

    @Override
    public void changeTiles() {
        newTile = true;
        this.texture = Assets.spaceTeleporter;
    }
}
