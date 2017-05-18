package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
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
    private MinerModel minerModel;


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

    public GameModel() {

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMapV2.tmx");
        prop = map.getProperties();

        //simple instantiation with the only purpose of adding a listener to MinerWorldContactListener
        new MinerWorldContactListener();

        // Create a new world
        world = new World(new Vector2(0, Constants.GRAVITY), true);
        //b2dr = new Box2DDebugRenderer();
        new Box2DWorldCreator(world, map);
        //this.miner = new Miner(world);
        world.setContactListener(new MinerWorldContactListener());
        isFacingRight = true;

        this.minerModel = new MinerModel(world);

    }

    public void adjustSpeedX(float knobPercentage) {
        float speedX = minerModel.getMiner().b2body.getLinearVelocity().x;

        if (knobPercentage != 0) {
            minerModel.getMiner().b2body.setLinearVelocity(new Vector2(5f * knobPercentage, minerModel.getMiner().b2body.getLinearVelocity().y));
        }
        //Opposing force acting instead of "friction"
        if (knobPercentage == 0 && speedX != 0) {
            minerModel.getMiner().b2body.applyLinearImpulse(new Vector2(-0.09f * speedX, 0), minerPos, true);

        }
        //miner.b2body.applyLinearImpulse(new Vector2(0.2f * knobPercentage, 0), minerPos, true);
        //miner.b2body.applyLinearImpulse(new Vector2(-4f * knobPercentage, 0), minerPos, true);
        /*if(miner.b2body.getLinearVelocity().x < 10000){
            miner.b2body.setLinearVelocity(new Vector2(0, miner.b2body.getLinearVelocity().y));
        }
        */
        //System.out.print(miner.b2body.getLinearVelocity().x + "\n");

    }

    public void adjustSpeedY(float knobPercentage) {
        float speedY = minerModel.getMiner().b2body.getLinearVelocity().y;
        if (knobPercentage > 0) {
            minerModel.getMiner().b2body.applyForceToCenter(0, 18f * knobPercentage, true);
        }
        if (knobPercentage < -0.4 && minerModel.getMiner().b2body.getLinearVelocity().y == 0) {
            minerModel.getMiner().b2body.setLinearVelocity(0, 0);
        }

        //max vertical velocity
        if (speedY > 5) {
            minerModel.getMiner().b2body.setLinearVelocity(minerModel.getMiner().b2body.getLinearVelocity().x, 5);
        }
    }

    public void update(Vector2 vector2) {



        minerPos = minerModel.getMiner().b2body.getPosition();

        touchpadLocation = vector2;

        world.step(1 / 60f, 6, 2);

        adjustSpeedX(touchpadLocation.x);
        adjustSpeedY(touchpadLocation.y);

        minerModel.update();
    }

    public boolean gameOver(){
        if(minerModel.isAlive()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean gamePaused(){
        return true;
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
        else if (minerModel.getMiner().b2body.getLinearVelocity().x > 0) {
            //RIGHT
            setIsFacingRight(true);
            return true;
        } else if (minerModel.getMiner().b2body.getLinearVelocity().x < 0) {
            //LEFT
            setIsFacingRight(false);
            return false;
        }
        //Check last direction
        else if (getIsFacingRight()) {
            //RIGHT
            return true;
        } else {
            return false;
        }
    }


    public MinerDrawOptions decideDirection() {
        if (touchpadLocation.y > 0.4) {
            //Draw UP
            if (drawRight()) {
                return new MinerDrawOptions("minerSpriteRocket", minerPos.x - 15 / Constants.PPM,
                        minerPos.y - 30 / Constants.PPM, (79 / 2) / Constants.PPM, (82 / 2) / Constants.PPM);
            } else {
                return new MinerDrawOptions("minerSpriteRocket", minerPos.x + 15 / Constants.PPM,
                        minerPos.y - 30 / Constants.PPM, (-79 / 2) / Constants.PPM, (82 / 2) / Constants.PPM);
            }
        }
        if (touchpadLocation.y < -0.4) {
            //Draw DOWN
            return new MinerDrawOptions("minerSpriteDrillDown", minerPos.x - 12 / Constants.PPM,
                    minerPos.y - 24 / Constants.PPM, (46 / 2) / Constants.PPM, (69 / 2) / Constants.PPM);
        } else if (drawRight()) {
            //Draw RIGHT
            return new MinerDrawOptions("minerSprite", minerPos.x - 15 / Constants.PPM,
                    minerPos.y - 15 / Constants.PPM, (79 / 2) / Constants.PPM, (51 / 2) / Constants.PPM);

        } else {
            //Draw LEFT
            return new MinerDrawOptions("minerSprite", minerPos.x + 15 / Constants.PPM,
                    minerPos.y - 15 / Constants.PPM, (-79 / 2) / Constants.PPM, (51 / 2) / Constants.PPM);
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

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getB2dr() {
        return this.b2dr;
    }

    public boolean getIsFacingRight() {
        return isFacingRight;
    }

    public void setIsFacingRight(boolean value) {
        this.isFacingRight = value;

    }

    public MinerModel getMinerModel() {
        return this.minerModel;
    }

    public void dispose() {
        map.dispose();
        world.dispose();
        //b2dr.dispose();
    }
}
