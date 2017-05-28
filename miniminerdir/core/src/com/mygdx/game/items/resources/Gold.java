package com.mygdx.game.items.resources;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/16/2017.
 */

public class Gold implements IResource {
    private Resource parent;

    public Gold(World world, TiledMap tiledMap, Rectangle constrains) {
        parent = new Resource(world, tiledMap, constrains, this, Constants.GOLD_BIT);
    }

    @Override
    public void onDrillHit() {
        parent.onDrillHit();
    }
}

