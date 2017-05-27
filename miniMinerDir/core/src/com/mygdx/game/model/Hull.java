package com.mygdx.game.model;


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

    public final float damageFactorUpgrade = 0.1f;

    private int upgradeLevel = 0;

    public final int MAX_UPGRADE_LEVEL = 10;


    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull() {
        maxHull = 100;
        hull = maxHull;
        damageFactor = 3;

    }

    public boolean isEmpty() {
        return hull <= 0;
    }

    public void adjustHull(float minerVelocityY) {
        //explanation for incrementation of hull: minerVelY will be negative when falling
        hull += minerVelocityY * damageFactor;
    }


    public String getHullString() {
        Integer integerHull = (int) hull;
        return integerHull.toString();
    }

    public float getDamageFactor() {
        return damageFactor;
    }

    public int getUpgradeLevel(){
        return this.upgradeLevel;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade() {
        if(upgradeLevel <= MAX_UPGRADE_LEVEL){
        damageFactor -= damageFactorUpgrade;
        upgradeLevel++;}
        if(upgradeLevel == MAX_UPGRADE_LEVEL){
            System.out.println("Hull upgraded to maximum");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repair() {
        hull = maxHull;
    }
}
