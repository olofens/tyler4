package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/15/2017.
 */

public class Redstone extends TileTemplate {
    public Redstone(World world, TiledMap tiledMap, Rectangle constrains) {
        super(world, tiledMap, constrains);
        fixture.setUserData(this);
        setCategoryFilter(MiniMiner.REDSTONE_BIT);
    }

    @Override
    public void onDrillHit() {
        Gdx.app.log("Redstone","Collision");
        setCategoryFilter(MiniMiner.DESTROYED_BIT);
        System.out.println("Redstone added to inventory");
        getCell().setTile(null);
    }


    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Diggable");
        return layer.getCell((int)((body.getPosition().x)* Constants.PPM/32),
                (int)((body.getPosition().y)*Constants.PPM/32));
    }
}
