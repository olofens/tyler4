package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MiniMiner;

/**
 * Created by Olof Enström on 2017-04-26.
 */

public class StoreTile {

    public StoreTile(World world, TiledMap tiledMap, Rectangle constrains) {
        TileTemplate parent = new TileTemplate(world, tiledMap, constrains);
        parent.body.getFixtureList().get(0).setSensor(true);
        parent.fixture.setUserData("store");
    }
}
