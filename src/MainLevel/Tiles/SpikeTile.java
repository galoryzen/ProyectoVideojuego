/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Graphics;

/**
 *
 * @author Omen
 */
public class SpikeTile extends TileMainLevel {

    private Animation animationSpace = new Animation(150, Assets.spaceSpikes);
    private boolean newTile = false;
    
    
    @Override
    public void render(Graphics g, int x, int y) {
        if (newTile) {
            g.drawImage(animationSpace.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
            animationSpace.update();
        } else {
            g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
        }
    }

    public SpikeTile(int id) {
        super(Assets.spikes, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean makeDamage() {
        return true;
    }

    @Override
    public void changeTiles() {
        newTile = true;
    }
}
