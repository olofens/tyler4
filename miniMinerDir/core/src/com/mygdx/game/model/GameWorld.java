package com.mygdx.game.model;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class GameWorld {

    private static GameWorld instance;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MapProperties prop;

    private World world;

    private GameWorld() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMapV2.tmx");
        prop = map.getProperties();

        new MinerWorldContactListener();

        world = new World(new Vector2(0, Constants.GRAVITY), true);

        new Box2DWorldCreator(world, map);

        world.setContactListener(new MinerWorldContactListener());
    }

    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getMapPixelWidth() {
        int mapWidth = prop.get("width", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        return mapWidth * tilePixelWidth;
    }

    public int getMapPixelHeight() {
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        return mapHeight * tilePixelHeight;
    }

    public void dispose() {
        map.dispose();
        world.dispose();
    }
}
