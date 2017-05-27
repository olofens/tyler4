package com.mygdx.game.model;

/**
 * Created by walling on 5/15/2017.
 */

public interface IGear {

    /**
     * Upgrades the gear to increase max value and increase tolerance for higher survivability
     */
    public void upgrade();

    /**
     * Restores the resources to a maximum value
     */
    public void repair();

}
