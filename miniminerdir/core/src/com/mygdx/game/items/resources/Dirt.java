package com.mygdx.game.items.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.IResource;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 2017-04-11.
 */

public class Dirt extends TileTemplate implements IResource {
    public Dirt(World world, TiledMap tiledMap, Rectangle constrains) {
        //Uses TileTemplate to create the fixture and body
        super(world, tiledMap, constrains);
        //Sets userdata for future contact
        fixture.setUserData(this);
        //Sets categoryfilter to enable the miner to collide with tile
        setCategoryFilter(Constants.TILE_BIT);
    }

    @Override
    public void onDrillHit() {
        //Sets categoryfilter to DESTROYED_BIT which makes miner ignore tile
        //Sets cell to null, which removes it
        Gdx.app.log("Dirt","Collision");
        setCategoryFilter(Constants.DESTROYED_BIT);
        getCell().setTile(null);
    }

}
