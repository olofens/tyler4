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

        world = new World(new Vector2(0, Constants.GRAVITY), true);
        new Box2DWorldCreator(world, map);
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

    public boolean gameOver() {
        return !minerModel.isAlive();
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
            isFacingRight = true;
            return true;
        } else if (minerModel.getMiner().b2body.getLinearVelocity().x < 0) {
            //LEFT
            isFacingRight = false;
            return false;
        }
        //Check last direction
        return isFacingRight;
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

    public TiledMap getMap() {
        return map;
    }


    public World getWorld() {
        return world;
    }


    public MinerModel getMinerModel() {
        return this.minerModel;
    }

    public void dispose() {
        map.dispose();
        world.dispose();
    }
}
