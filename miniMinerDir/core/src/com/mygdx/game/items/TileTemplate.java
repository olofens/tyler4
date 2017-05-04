package com.mygdx.game.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 2017-04-11.
 */

public abstract class TileTemplate {
    protected World world;
    protected TiledMap tiledMap;
    protected TiledMapTile tile;
    protected Rectangle constrains;
    protected Body body;

    protected Fixture fixture;

    public TileTemplate(World world, TiledMap tiledMap, Rectangle constrains){
        this.world = world;
        this.tiledMap = tiledMap;
        this.constrains = constrains;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        PolygonShape pShape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((constrains.getX() + constrains.getWidth() / 2) / Constants.PPM, (constrains.getY() + constrains.getHeight() / 2) / Constants.PPM);

        body = world.createBody(bodyDef);

        pShape.setAsBox(constrains.getWidth() / 2 / Constants.PPM, constrains.getHeight() / 2 / Constants.PPM);
        fixDef.shape = pShape;
        fixture = body.createFixture(fixDef);
    }
    public abstract void  onDrillHit();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

}
