package com.mygdx.game.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Olof Enström on 2017-04-26.
 */

public class StoreTile extends TileTemplate {
    public StoreTile(World world, TiledMap tiledMap, Rectangle constrains) {
        super(world, tiledMap, constrains);
        body.getFixtureList().get(0).setSensor(true);
        body.getFixtureList().get(0).setUserData("store");
    }
}
