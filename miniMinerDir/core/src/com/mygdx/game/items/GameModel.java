package com.mygdx.game.items;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tools.Box2DWorldCreator;
import com.mygdx.game.Tools.MinerDrawOptions;
import com.mygdx.game.Tools.MinerWorldContactListener;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class GameModel {


    //Miner variables
    private Miner miner;


    // Tiledmap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private MapProperties prop;


    // Box2D variables
    private World world;
    private Box2DDebugRenderer b2dr;


    private boolean isFacingRight;

    private Vector2 touchpadLocation;
    private Vector2 minerPos;

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

        this.miner = new Miner(world);

    }

    public void adjustSpeedX(float knobPercentage){
        miner.b2body.setLinearVelocity(new Vector2(5f * knobPercentage, miner.b2body.getLinearVelocity().y));
    }

    public void adjustSpeedY(float knobPercentage){
        miner.b2body.applyForceToCenter(0, 18f * knobPercentage, true);
    }

    public void update(Vector2 vector2){

        minerPos = getMiner().b2body.getPosition();

        touchpadLocation = vector2;

        world.step(1 / 60f, 6, 2);

        adjustSpeedX(touchpadLocation.x);
        adjustSpeedY(touchpadLocation.y);


    }


    private boolean drawRight() {
        //Check touchpad
        if (touchpadLocation.x > 0) {
            //RIGHT
            return true;
        } else if (touchpadLocation.x < 0) {
            //LEFT
            return false;
        }
        //Check velocity
        else if (getMiner().b2body.getLinearVelocity().x > 0) {
            //RIGHT
            setIsFacingRight(true);
            return true;
        } else if (getMiner().b2body.getLinearVelocity().x < 0) {
            //LEFT
            setIsFacingRight(false);
            return false;
        }
        //Check last direction
        else if (getIsFacingRight()) {
            //RIGHT
            return true;
        }
        else {
            return false;
        }
    }

    public MinerDrawOptions decideDirection(){
        if (touchpadLocation.y > 0) {
            //Draw UP
            if (drawRight()) {
                //game.batch.draw(minerSpriteRocket, minerPos.x - 10 / Constants.PPM, minerPos.y - 27 / Constants.PPM,
                 //       12 / Constants.PPM, 15 / Constants.PPM);
                return new MinerDrawOptions("minerSpriteRocket", minerPos.x - 10 / Constants.PPM,
                        minerPos.y - 27 / Constants.PPM, 12 / Constants.PPM, 15 / Constants.PPM);

            } else {
                //game.batch.draw(minerSpriteRocket, minerPos.x - 1 / Constants.PPM, minerPos.y - 27 / Constants.PPM,
                 //       12 / Constants.PPM, 15 / Constants.PPM);

                return new MinerDrawOptions("minerSpriteRocket", minerPos.x - 1 / Constants.PPM,
                        minerPos.y - 27 / Constants.PPM,12 / Constants.PPM, 15 / Constants.PPM);
            }

        }

        if (touchpadLocation.y < 0) {
            //Draw DOWN
            //game.batch.draw(minerSpriteDrillDown, minerPos.x - 12 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
            //        25 / Constants.PPM, 28 / Constants.PPM);
            return new MinerDrawOptions("minerSpriteDrillDown", minerPos.x - 12 / Constants.PPM,
                    minerPos.y - 15 / Constants.PPM,25 / Constants.PPM, 28 / Constants.PPM);
        } else if (drawRight()) {
            //Draw RIGHT
            //game.batch.draw(minerSprite, minerPos.x - 15 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
            //        35 / Constants.PPM, 25 / Constants.PPM);
            return new MinerDrawOptions("minerSprite", minerPos.x - 15 / Constants.PPM,
                    minerPos.y - 15 / Constants.PPM,35 / Constants.PPM, 25 / Constants.PPM);

        } else {
            //Draw LEFT
            //game.batch.draw(minerSprite, minerPos.x + 15 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
            //        -35 / Constants.PPM, 25 / Constants.PPM);

            return new MinerDrawOptions("minerSprite", minerPos.x + 15 / Constants.PPM,
                    minerPos.y - 15 / Constants.PPM,-35 / Constants.PPM, 25 / Constants.PPM);
        }
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

    public Miner getMiner(){
        return this.miner;
    }

    public void dispose(){
        map.dispose();
        world.dispose();
        b2dr.dispose();
    }
}
