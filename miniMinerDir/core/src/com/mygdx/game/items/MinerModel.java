package com.mygdx.game.items;


import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Omaroueidat on 11/05/17.
 */

/**
 * The class where the logic and calculations of the Miner will occur.
 */
public class MinerModel {

    private FuelTank ft;
    private Miner miner;
    private Hull hull;


    /**
     * Constructor which takes in world in order to create our Miner.
     * @param world
     */
    public MinerModel(World world){

        ft = new FuelTank();
        hull = new Hull();
        miner = new Miner(world);

    }

    /**
     * Getter for our miner
     * @return tnis.miner
     */
    public Miner getMiner(){
        return this.miner;
    }



}
