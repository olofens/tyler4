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
    private Integer hull;

    private float damageFactor;

    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull() {
        maxHull = 100;
        hull = maxHull;
        damageFactor = 0.5f;

    }

    public boolean isEmpty(){
        if(hull <= 0){
            return true;
        }
        return false;
    }

    public void adjustHull(Integer minerVelocityX, Integer minerVelocityY) {
        System.out.println("hull decreased");
    }


    public Integer getHull() {
        return hull;
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
