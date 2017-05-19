package com.mygdx.game.event.drill;

/**
 * Created by Olof Enström on 2017-05-18.
 */

public class DrillData {
    public enum DrillDirection {
        LEFT,
        DOWN,
        RIGHT,
    }

    private DrillDirection drillDirection;
    private boolean newDirection;

    public DrillData(DrillDirection direction, boolean newDirection) {
        this.drillDirection = direction;
        this.newDirection = newDirection;
    }

    public DrillDirection getDrillDirection() {
        return drillDirection;
    }
    public boolean getNewDirection() { return newDirection; }

}
