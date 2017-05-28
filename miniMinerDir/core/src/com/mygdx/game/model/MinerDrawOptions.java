package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by erikstrid on 2017-05-11.
 */

public class MinerDrawOptions {

    private String strSprite;

    private float x;

    private float y;

    private float x1;

    private float y1;

    public MinerDrawOptions(String strSprite, float x, float y, float x1, float y1){
        this.strSprite = strSprite;
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    public String getStrSprite() {
        return strSprite;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }
}
