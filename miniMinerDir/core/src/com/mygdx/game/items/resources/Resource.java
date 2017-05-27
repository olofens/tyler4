package com.mygdx.game.items.resources;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 2017-05-25.
 */

public class Resource implements IResource {
    private TileTemplate tileTemplate;
    public Resource(World world, TiledMap tiledMap, Rectangle constrains, IResource userData, short bit){
        tileTemplate = new TileTemplate(world, tiledMap, constrains);
        initResource( userData, bit);
    }


    @Override
    public void onDrillHit() {
        tileTemplate.setCategoryFilter(Constants.DESTROYED_BIT);
        tileTemplate.getCell().setTile(null);
    }
    public void initResource(IResource resource, short bit){
        tileTemplate.fixture.setUserData(resource);
        tileTemplate.setCategoryFilter(bit);
    }

}
