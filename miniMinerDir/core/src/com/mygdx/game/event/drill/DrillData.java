package com.mygdx.game.event.drill;

/**
 * Created by Olof Enström on 2017-05-18.
 */

public class DrillData {
    public enum DrillDirection {
        LEFT,
        DOWN,
        RIGHT;
    }

    private DrillDirection drillDirection;

    public DrillData(DrillDirection direction) {
        this.drillDirection = direction;
    }

    public DrillDirection getDrillDirection() {
        return drillDirection;
    }

}
