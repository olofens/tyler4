package com.mygdx.game.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by olofenstrom on 2017-05-20.
 */

public class StoreUpgradeTile extends TileTemplate {

    public StoreUpgradeTile(World world, TiledMap tiledMap, Rectangle constrains) {
        super(world, tiledMap, constrains);
        body.getFixtureList().get(0).setSensor(true);
        fixture.setUserData("storeUpgrade");
    }

}
