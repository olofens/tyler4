package com.mygdx.game.items;



/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the health of the miner
 */
public class Hull implements IGear {

    /**
     * Variable for the amount of health
     */
    private Integer hull;

    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull() {
        hull = 100;

    }

    public void adjustHull(){

    }


    /**
     * @return hull
     */
    public Integer getHull() {
        return hull;
    }
}
