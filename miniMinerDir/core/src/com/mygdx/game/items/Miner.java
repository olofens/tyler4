package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MiniMiner;

import java.awt.geom.RectangularShape;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner extends Sprite {

    public World world;
    public Body b2body;
    private Vector3 position;
    private Vector3 velocity;

    private Texture miner;

    private static final int GRAVITY = -30;

    public Miner(World world) {
        this.world = world;
        defineMiner();

    }


    public void defineMiner() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(320, 1400);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdefine = new FixtureDef();

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(50);

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
