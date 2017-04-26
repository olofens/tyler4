package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.items.Edge;
import com.mygdx.game.items.StoreTile;
import com.mygdx.game.items.Tile;

/**
 * Created by walling on 2017-04-11.
 */

public class Box2DWorldCreator {
    public Box2DWorldCreator(World world, TiledMap map){

        //adding ground layer
        for (MapObject object : map.getLayers().get("GroundLayer").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Tile(world, map, rect);
        }

        for (MapObject object : map.getLayers().get("Store").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new StoreTile(world, map, rect);
        }

        Edge.parseTiledObject(world, map.getLayers().get("Edges").getObjects());
    }
}
