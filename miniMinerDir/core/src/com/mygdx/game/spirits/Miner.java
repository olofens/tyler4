package com.mygdx.game.spirits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Omaroueidat on 06/04/17.
 */

public class Miner  {

    private Texture miner;
    private Vector3 gravity;
    private final static int GRAVITY = -15;

    public Miner(){
        this.gravity = new Vector3(0, GRAVITY , 0);

    }

    public void dispose(){
        miner.dispose();
    }

}
