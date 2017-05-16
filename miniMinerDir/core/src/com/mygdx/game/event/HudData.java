package com.mygdx.game.event;

import java.awt.*;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class HudData {
    private int fuel;
    private float hull;
    private Color color;
    private String string;
    private String tag;

    public HudData(int fuel, float hull, Color color, String string, String tag) {
        this.fuel = fuel;
        this.hull = hull;
        this.color = color;
        this.string = string;
        this.tag = tag;
    }

    public int getFuel() {
        return fuel;
    }

    public float getHull() {
        return hull;
    }

    public Color getColor(){
        return color;
    }

    public String getString(){
        return string;
    }

    public String getTag(){
        return tag;
    }
}
