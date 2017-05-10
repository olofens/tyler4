package com.mygdx.game.items;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tools.Box2DWorldCreator;
import com.mygdx.game.Tools.MinerWorldContactListener;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class GameModel {


    // Tiledmap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MapProperties prop;


    // Box2D variables
    private World world;
    private Box2DDebugRenderer b2dr;


    private boolean isFacingRight;

    public GameModel(){

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMap.tmx");
        prop = map.getProperties();



        // Create a new world
        world = new World(new Vector2(0, Constants.GRAVITY), true);
        b2dr = new Box2DDebugRenderer();
        new Box2DWorldCreator(world, map);
        //this.miner = new Miner(world);
        world.setContactListener(new MinerWorldContactListener());
        isFacingRight = true;

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

    public World getWorld(){
        return world;
    }

    public Box2DDebugRenderer getB2dr(){
        return this.b2dr;
    }

    public boolean getIsFacingRight(){
        return isFacingRight;
    }

    public void setIsFacingRight(boolean value){
        this.isFacingRight = value;

    }
}
