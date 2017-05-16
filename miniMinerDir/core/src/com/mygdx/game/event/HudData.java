package com.mygdx.game.event;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class HudData {
    private int fuel;
    private int hull;

    public HudData(int fuel, int hull) {
        this.fuel = fuel;
        this.hull = hull;
    }

    public int getFuel() {
        return fuel;
    }

    public int getHull() {
        return hull;
    }
}
