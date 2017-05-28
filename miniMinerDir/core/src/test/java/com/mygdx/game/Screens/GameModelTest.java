package com.mygdx.game.Screens;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools.MinerDrawOptions;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.model.GameWorld;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-28.
 */

public class GameModelTest extends GameTest{

    @Test
    public void adjustspeedX() throws Exception {

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad in x-axis
        float x = 0.5f;
        float y = 0.0f;
        Vector2 touchpadVector2 = new Vector2(x,y);

        //send touchpad mock into model
        gameModel.update(touchpadVector2);

        float speedX = gameModel.getMinerModel().getMiner().b2body.getLinearVelocity().x;

        System.out.print(speedX);

        //make sure miner is moving in x-axis
        assertTrue(speedX > 0);
    }


    @Test
    public void adjustspeedY() throws Exception {

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad in y-axis
        float x = 0.0f;
        float y = 1f;
        Vector2 touchpadVector2 = new Vector2(x,y);

        //send touchpad mock into model - run update method 3 cycles
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);

        float speedY = gameModel.getMinerModel().getMiner().b2body.getLinearVelocity().y;

        System.out.print(speedY);

        //make sure miner is moving in y-axis
        assertTrue(speedY > 0);
    }


    @Test
    public void testDecideDirectionRight(){

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad to the right
        float x = 0.8f;
        float y = 0.0f;
        Vector2 touchpadVector2 = new Vector2(x,y);

        //send touchpad mock into model
        gameModel.update(touchpadVector2);


        MinerDrawOptions mdo = gameModel.decideDirection();

        //make sure drawoptions data match the appropriate direction data
        assertTrue(mdo.getX1() == (79 / 2) / Constants.PPM);
        assertTrue(mdo.getY1() == (51 / 2) / Constants.PPM);


    }

    @Test
    public void testDecideDirectionLeft(){

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad to the left
        float x = -0.8f;
        float y = 0.0f;
        Vector2 touchpadVector2 = new Vector2(x,y);

        //send touchpad mock into model
        gameModel.update(touchpadVector2);


        MinerDrawOptions mdo = gameModel.decideDirection();

        //make sure drawoptions data match the appropriate direction data
        assertTrue(mdo.getX1() == (-79 / 2) / Constants.PPM);
        assertTrue(mdo.getY1() == (51 / 2) / Constants.PPM);


    }
    @Test
    public void testDecideDirectionUpRight(){

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad up/right
        float x = 0.8f;
        float y = 1.0f;
        Vector2 touchpadVector2 = new Vector2(x,y);


        //send touchpad mock into model - run update method 3 cycles
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);


        MinerDrawOptions mdo = gameModel.decideDirection();

        //make sure drawoptions data match the appropriate direction data
        assertTrue(mdo.getX1() == (79 / 2) / Constants.PPM);
        assertTrue(mdo.getY1() == (82 / 2) / Constants.PPM);


    }
    @Test
    public void testDecideDirectionUpLeft(){

        GameModel gameModel = new GameModel();

        //touchpad mock - move touchpad up/left
        float x = -0.8f;
        float y = 1.0f;
        Vector2 touchpadVector2 = new Vector2(x,y);

        //send touchpad mock into model - run update method 3 cycles
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);
        gameModel.update(touchpadVector2);


        MinerDrawOptions mdo = gameModel.decideDirection();

        //make sure drawoptions data match the appropriate direction data
        assertTrue(mdo.getX1() == (-79 / 2) / Constants.PPM);
        assertTrue(mdo.getY1() == (82 / 2) / Constants.PPM);


    }


}
