/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Omen
 */
public class WallTile extends TileMainLevel {

    public WallTile(int id) {
        super(Assets.wallTile, id);
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public void changeTiles() {
        this.texture = Assets.spaceWall;
    }

}
