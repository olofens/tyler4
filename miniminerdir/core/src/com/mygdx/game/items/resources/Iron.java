package com.mygdx.game.items.resources;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/15/2017.
 */

public class Iron implements IResource{
    private Resource parent;
    public Iron(World world, TiledMap tiledMap, Rectangle constrains) {
        //Se Dirt-class for commenting
        parent = new Resource(world, tiledMap, constrains,this, Constants.IRON_BIT);

    }

    @Override
    public void onDrillHit() {
       parent.onDrillHit();
    }
}
