package com.mygdx.game.items;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools.MinerDrawOptions;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class GameModel {

    //Miner variables
    private MinerModel minerModel;

    // Tiledmap variables
    //private TmxMapLoader mapLoader;
    //private TiledMap map;
    //private MapProperties prop;

    // Box2D variables
    //private World world;

    private GameWorld gameWorld;

    private boolean isFacingRight;

    private Vector2 touchpadLocation;
    private Vector2 minerPos;

    public GameModel() {

        gameWorld = GameWorld.getInstance();

        isFacingRight = true;

        this.minerModel = new MinerModel(gameWorld.getWorld());
    }

    private void adjustSpeedX(float knobPercentage) {
        float speedX = minerModel.getMiner().b2body.getLinearVelocity().x;

        if (knobPercentage != 0) {
            minerModel.getMiner().b2body.setLinearVelocity(new Vector2(5f * knobPercentage, minerModel.getMiner().b2body.getLinearVelocity().y));
        }
        //Opposing force acting instead of "friction"
        if (knobPercentage == 0 && speedX != 0) {
            minerModel.getMiner().b2body.applyLinearImpulse(new Vector2(-0.09f * speedX, 0), minerPos, true);

        }
    }

    private void adjustSpeedY(float knobPercentage) {
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

        gameWorld.update();

        adjustSpeedX(touchpadLocation.x);
        adjustSpeedY(touchpadLocation.y);

        minerModel.update();
    }

    public boolean gameOver() {
        return !minerModel.isAlive();
    }


    private boolean drawRight() {

        if (touchpadLocation.x > 0) {
            //RIGHT
            return true;
        } else if (touchpadLocation.x < 0) {
            //LEFT
            return false;
        }

        else if (minerModel.getMiner().b2body.getLinearVelocity().x > 0) {
            //RIGHT
            setIsFacingRight(true);
            return true;
        } else if (minerModel.getMiner().b2body.getLinearVelocity().x < 0) {
            //LEFT
            setIsFacingRight(false);
            return false;
        }

        return getIsFacingRight();
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

    public MinerModel getMinerModel() {
        return this.minerModel;
    }

    private void setIsFacingRight(boolean val) {
        isFacingRight = val;
    }

    private boolean getIsFacingRight() {
        return isFacingRight;
    }
}
