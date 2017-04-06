package com.mygdx.game.spirits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner  {

    private Vector3 position;
    private Vector3 velocity;

    private Texture miner;

    private final static int GRAVITY = -15;

    public Miner(int x, int y){
        this.velocity = new Vector3(0, 0 , 0);
        this.position = new Vector3(x,y,0);
        this.miner = new Texture("miniTextureTest.jpg");
    }

    public void dispose(){
        miner.dispose();
    }

    public void update(float dt){
        this.velocity.add(0,GRAVITY,0);
        this.velocity.scl(dt);
    }

}
