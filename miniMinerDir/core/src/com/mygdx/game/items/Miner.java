package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner  {

    private Vector3 position;
    private Vector3 velocity;

    private Texture miner;

    private static final int GRAVITY = -30;

    public Miner(int x , int y){
        this.position = new Vector3(x,y,0);
        this.velocity = new Vector3(0, 0, 0);
        this.miner = new Texture("minerTextureTest.jpg");


    }

    public void update(float dt){
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
    }
}
