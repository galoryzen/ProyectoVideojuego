/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author Omen
 */
public class Chest extends TileMainLevel {

    public Chest(int id) {
        super(Assets.chest, id);
        isSolid = false;
        isInteractive = false;
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public boolean isInteractive() {
        return !isInteractive;
    }

    @Override
    public void changeTiles() {
        this.texture = Assets.spaceCrate;
    }

}
