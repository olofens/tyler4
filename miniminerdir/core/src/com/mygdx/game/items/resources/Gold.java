package com.mygdx.game.items.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.IResource;
import com.mygdx.game.items.Inventory;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 5/16/2017.
 */

public class Gold implements IResource {
    private TileTemplate parent;
    public Gold(World world, TiledMap tiledMap, Rectangle constrains) {
        //Se Dirt-class for commenting
        parent = new TileTemplate(world, tiledMap, constrains, this, Constants.GOLD_BIT);

    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Gold", "Collision");
        parent.setCategoryFilter(Constants.DESTROYED_BIT);
        System.out.println("Gold added to inventory");
        parent.getCell().setTile(null);
    }
}

