package com.mygdx.game.items;


/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the health of the miner
 */
public class Hull implements IGear {


    private Integer maxHull;
    /**
     * Variable for the amount of health
     */
    private float hull;

    private float damageFactor;

    private String hullString;

    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull() {
        maxHull = 100;
        hull = maxHull;
        damageFactor = 3;

    }

    public boolean isEmpty(){
        return hull <= 0;
    }

    public void adjustHull(float minerVelocityY) {
        //explanation for incrementation of hull: minerVelY will be negative when falling
        hull += minerVelocityY * damageFactor;
    }


    public float getHull() {
        return hull;
    }

    public String getHullString(){
        Integer integerHull = (int) hull;
        hullString = integerHull.toString();
        return hullString;
    }

    public float getDamageFactor() {
        return damageFactor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade() {
        damageFactor -= 0.05;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repair() {
        hull = maxHull;
    }
}
