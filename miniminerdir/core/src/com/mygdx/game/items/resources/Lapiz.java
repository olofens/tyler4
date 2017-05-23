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
 * Created by walling on 5/15/2017.
 */

public class Lapiz implements IResource {
    private TileTemplate parent;

    public Lapiz(World world, TiledMap tiledMap, Rectangle constrains) {
        //Se Dirt-class for commenting
        parent = new TileTemplate(world, tiledMap,constrains, this, Constants.LAPIZ_BIT);

    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Lapiz","Collision");
        parent.setCategoryFilter(Constants.DESTROYED_BIT);
        System.out.println("Lapiz added to inventory");
        parent.getCell().setTile(null);
    }

}
