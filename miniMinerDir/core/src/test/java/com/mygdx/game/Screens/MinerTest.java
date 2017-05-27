package com.mygdx.game.Screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.MinerModel;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class MinerTest extends GameTest {

    @Test
    public void minerCreationTest(){


        MinerModel minerModel = new MinerModel(new World(new Vector2(0,0),false));

        //Make sure miner is alive upon creation
        assertTrue(minerModel.isAlive());

        //Make sure miner is not moving upon creation
        assertTrue(minerModel.getMiner().b2body.getLinearVelocity().x == 0);
        assertTrue(minerModel.getMiner().b2body.getLinearVelocity().y == 0);
    }


}
