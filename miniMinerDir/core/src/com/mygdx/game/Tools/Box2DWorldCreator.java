package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.items.Coal;
import com.mygdx.game.items.Edge;
import com.mygdx.game.items.Gold;
import com.mygdx.game.items.Iron;
import com.mygdx.game.items.Lapiz;
import com.mygdx.game.items.Redstone;
import com.mygdx.game.items.StoreTile;
import com.mygdx.game.items.Tile;

/**
 * Created by walling on 2017-04-11.
 */

public class Box2DWorldCreator {
    public Box2DWorldCreator(World world, TiledMap map){

        //adding ground layer
        for (MapObject object : map.getLayers().get("Dirt").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Tile(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Coal").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coal(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Iron").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Iron(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Redstone").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Redstone(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Lapiz").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Lapiz(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Gold").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Gold(world, map, rect);
        }

        for (MapObject object : map.getLayers().get("Store").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new StoreTile(world, map, rect);
        }

        Edge.parseTiledObject(world, map.getLayers().get("Edges").getObjects());
    }
}
