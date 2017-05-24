package com.mygdx.game.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by olofenstrom on 2017-05-20.
 */

public class StoreUpgradeTile {

    public StoreUpgradeTile(World world, TiledMap tiledMap, Rectangle constrains) {
        TileTemplate parent = new TileTemplate(world, tiledMap, constrains);
        parent.body.getFixtureList().get(0).setSensor(true);
        parent.fixture.setUserData("storeUpgrade");
    }
}
