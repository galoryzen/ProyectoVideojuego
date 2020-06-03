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
public abstract class ElevatorTile extends TileMainLevel {

    public ElevatorTile(int id) {
        super(Assets.elevatorTile, id);
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public void changeTiles() {
        this.texture = Assets.spaceBlocker;
    }

}
