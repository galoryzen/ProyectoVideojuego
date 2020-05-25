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
public class SpikeTile extends TileMainLevel {
    
    public SpikeTile(int id){
        super(Assets.spikes,id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
    @Override
    public boolean makeDamage() {
        return true;
    }
}
