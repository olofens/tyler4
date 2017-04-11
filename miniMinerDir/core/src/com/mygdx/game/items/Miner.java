package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Utils.Constants;

import java.awt.geom.RectangularShape;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner extends Sprite {

    /**
     * Define our world and Body for our miner
     */
    public World world;
    public Body b2body;

    /**
     * The integer that is used for a vector in its y-axis for gravity feel.
     */
    public static final int GRAVITY = -10;

    /**
     * @param world the world we defined in our miner class
     *
     * Takes in a world and calls to our define miner method to create the miner.
     */
    public Miner(World world) {
        this.world = world;
        defineMiner();

    }


    /**
     * Creates a miner with the help of the world we create
     */
    private void defineMiner() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(320 / Constants.PPM, 1400 / Constants.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdefine = new FixtureDef();

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(16 / Constants.PPM);

        fdefine.shape = circleShape;
        b2body.createFixture(fdefine);


    }


  /*  public void update(float dt){



        this.velocity.add(0, GRAVITY,0);
        this.velocity.scl(dt);
        this.position.add(0 , velocity.y, 0);

        velocity.scl(1/dt);

    }
    public void dispose(){
        miner.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getMiner() {
        return miner;
    }*/
}