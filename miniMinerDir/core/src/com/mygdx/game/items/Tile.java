package com.mygdx.game.items;

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

/**
 * Created by walling on 2017-04-11.
 */

public class Tile extends TileTemplate implements IResource{
    public Tile(World world, TiledMap tiledMap, Rectangle constrains) {
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
        Gdx.app.log("Tile","Collision");
        setCategoryFilter(Constants.DESTROYED_BIT);
        getCell().setTile(null);
    }

    public TiledMapTileLayer.Cell getCell(){
        //Uses map to find the layer where miner is positioned
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Diggable");
        return layer.getCell((int)((body.getPosition().x)*Constants.PPM/32),
                (int)((body.getPosition().y)*Constants.PPM/32));
    }

}
