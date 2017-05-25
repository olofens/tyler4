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

public class Dirt implements IResource {

    private Resource parent;
    public Dirt(World world, TiledMap tiledMap, Rectangle constrains) {
        //Uses Resource/TileTemplate to create the fixture and body
        parent = new Resource(world,tiledMap,constrains, this,  Constants.DIRT_BIT);

    }

    @Override
    public void onDrillHit() {
        parent.onDrillHit();
    }

}
