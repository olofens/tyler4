package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Utils.Constants;

import java.awt.geom.RectangularShape;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner extends Sprite {

    /**
     * Define our world and Body for our miner
     */
    public Body b2body;

    /**
     * @param world the world we defined in our miner class
     *              <p>
     *              Takes in a world and calls to our define miner method to create the miner.
     */
    public Miner(World world) {
        defineMiner(world);

    }


    /**
     * Creates a miner with the help of the world we create
     */
    private void defineMiner(World world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(100 / Constants.PPM, 1400 / Constants.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdefine = new FixtureDef();
        fdefine.friction = 15;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / Constants.PPM);
        fdefine.filter.categoryBits = MiniMiner.MINER_BIT;
        fdefine.filter.maskBits = MiniMiner.DEFAULT_BIT | MiniMiner.TILE_BIT;

        fdefine.shape = circleShape;
        b2body.createFixture(fdefine).setUserData("miner");

        EdgeShape drill = new EdgeShape();
        drill.set(new Vector2(-0.5f/Constants.PPM, -11/Constants.PPM), new Vector2(0.5f/Constants.PPM, -11/Constants.PPM));
        fdefine.shape = drill;
        fdefine.isSensor = true;
        b2body.createFixture(fdefine).setUserData("drill");


        EdgeShape rightWing = new EdgeShape();
        rightWing.set(new Vector2(10/Constants.PPM, -1/Constants.PPM), new Vector2(10/Constants.PPM, 0/Constants.PPM));
        fdefine.shape = rightWing;
        fdefine.isSensor = true;
        b2body.createFixture(fdefine).setUserData("rightWing");

        EdgeShape leftWing = new EdgeShape();
        leftWing.set(new Vector2(-10/Constants.PPM, -1/Constants.PPM), new Vector2(-10/Constants.PPM, 0/Constants.PPM));
        fdefine.shape = leftWing;
        fdefine.isSensor = true;
        b2body.createFixture(fdefine).setUserData("leftWing");

    }
}
