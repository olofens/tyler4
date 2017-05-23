package com.mygdx.game.items.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.IResource;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 5/15/2017.
 */

public class Redstone implements IResource {
    private TileTemplate parent;

    public Redstone(World world, TiledMap tiledMap, Rectangle constrains) {
        //Se Dirt-class for commenting
        parent = new TileTemplate(world, tiledMap, constrains);
        parent.setUserData(this);
        parent.setCategoryFilter(Constants.REDSTONE_BIT);
    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Redstone","Collision");
        parent.setCategoryFilter(Constants.DESTROYED_BIT);
        System.out.println("Redstone added to inventory");
        parent.getCell().setTile(null);
    }

}
