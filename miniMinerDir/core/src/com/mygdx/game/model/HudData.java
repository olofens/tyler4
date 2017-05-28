package com.mygdx.game.model;

import java.awt.*;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class HudData {
    private String fuel;
    private String hull;
    private String cash;
    private Color color;

    public HudData(String fuel, String hull, String cash, Color color) {
        this.fuel = fuel;
        this.hull = hull;
        this.cash = cash;
        this.color = color;
    }

    public String getFuel() {
        return fuel;
    }

    public String getHull() {
        return hull;
    }

    public String getCash() {
        return cash;
    }

    public Color getColor(){
        return color;
    }
}
