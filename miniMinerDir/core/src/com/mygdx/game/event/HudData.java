package com.mygdx.game.event;

import java.awt.*;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class HudData {
    private String fuel;
    private String hull;
    private Color color;

    public HudData(String fuel, String hull, Color color) {
        this.fuel = fuel;
        this.hull = hull;
        this.color = color;
    }

    public String getFuel() {
        return fuel;
    }

    public String getHull() {
        return hull;
    }

    public Color getColor(){
        return color;
    }
}
