package com.mygdx.game.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.items.StoreUpgradeTile;
import com.mygdx.game.items.resources.Dirt;
import com.mygdx.game.items.resources.Coal;
import com.mygdx.game.items.resources.Diamond;
import com.mygdx.game.items.Edge;
import com.mygdx.game.items.resources.Gold;
import com.mygdx.game.items.resources.Iron;
import com.mygdx.game.items.resources.Lapiz;
import com.mygdx.game.items.resources.Redstone;
import com.mygdx.game.items.resources.Stone;
import com.mygdx.game.items.StoreTile;

/**
 * Created by walling on 2017-04-11.
 */

public class Box2DWorldCreator {
    public Box2DWorldCreator(World world, TiledMap map){

        //adding ground layer
        //Uses one for loop for each different tile, uses the map in Tiled
        //to import the different layers marked by a string, ex "Dirt"


        for (MapObject object : map.getLayers().get("Dirt").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Dirt(world, map, rect);
        }
        for (MapObject object : map.getLayers().get("Stone").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Stone(world, map, rect);
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
        for (MapObject object : map.getLayers().get("Diamond").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Diamond(world, map, rect);
        }

        for (MapObject object : map.getLayers().get("Store").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new StoreTile(world, map, rect);
        }

        for (MapObject object : map.getLayers().get("StoreUpgrade").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new StoreUpgradeTile(world, map, rect);
        }

        Edge.parseTiledObject(world, map.getLayers().get("Edges").getObjects());
    }
}
