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
import com.mygdx.game.items.resources.Coal;

/**
 * Created by walling on 2017-04-11.
 */

public class TileTemplate {

    protected TiledMap tiledMap;
    protected TiledMapTile tile;
    protected Rectangle constrains;
    protected Body body;

    public Fixture fixture;

    public TileTemplate(World world, TiledMap tiledMap, Rectangle constrains, IResource resource, short bit){


        this.tiledMap = tiledMap;
        this.constrains = constrains;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        PolygonShape pShape = new PolygonShape();

        //Creates body and fixture for box2D
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((constrains.getX() + constrains.getWidth() / 2) / Constants.PPM, (constrains.getY() + constrains.getHeight() / 2) / Constants.PPM);

        body = world.createBody(bodyDef);

        //Sets size with constrains
        pShape.setAsBox(constrains.getWidth() / 2 / Constants.PPM, constrains.getHeight() / 2 / Constants.PPM);
        fixDef.shape = pShape;
        fixture = body.createFixture(fixDef);
        fixture.setFriction(0f);

        fixture.setUserData(resource);
        setCategoryFilter(bit);
    }
    public void setCategoryFilter(short filterBit){
        //Sets the imported bit in a new filter and sets the fixtures filterData
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Diggable");
        return layer.getCell((int)((body.getPosition().x)* Constants.PPM/32),
                (int)((body.getPosition().y)*Constants.PPM/32));
    }
}
