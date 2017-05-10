package com.mygdx.game.items;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class GameModel {

    // Tiledmap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MapProperties prop;

    public GameModel(){

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMap.tmx");
        prop = map.getProperties();

    }


    public TmxMapLoader getMapLoader() {
        return mapLoader;
    }

    public TiledMap getMap() {
        return map;
    }

    public MapProperties getProp() {
        return prop;
    }

}
