package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.event.HudUpdater;
import com.mygdx.game.event.IHudUpdater;

/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the fuel for the miner
 */
public class FuelTank implements IGear {

    /**
     * The fuel variable
     */
    private int MAX_FUEL;
    private Integer fuel;

    private int decreaseFuel;

    private String strFuel;
    private Integer fuelLength;

    /**
     * Constructor which gives our default fuel ammount and makes our label with a red color and a certain position
     */
    public FuelTank() {
        MAX_FUEL = 100000;
        fuel = MAX_FUEL;
        decreaseFuel = 10;

        strFuel = fuel.toString();
        fuelLength = fuel.toString().length();
    }


    /**
     * If the miner is moved by gravity the fuel will not decrease, method will check if the miner has a velocity
     * on the X-axis then on the Y-axis to determine how much fuel to decrease.
     * @param minerVelocityX the velocity of the miner in the X axis
     * @param minerVelocityY the velocity of the miner in the Y axis
     */
    public void adjustFuel(Integer minerVelocityX, Integer minerVelocityY) {

        if (minerVelocityX != 0)
        fuel = fuel - (Math.abs(minerVelocityX) * decreaseFuel);
        if(minerVelocityY > 0)
            fuel = fuel - (Math.abs(minerVelocityY) * decreaseFuel);

    }

    /**
     * @return fuel
     */
    public Integer getFuel() {
        return fuel;
    }

    public String getStrFuel() {
        return strFuel;
    }

    public Integer getFuelLength() {
        return fuelLength;
    }

    public int getDecreaseFuel(){
        return decreaseFuel;
    }


    @Override
    public void upgrade() {
        this.MAX_FUEL += 50000;

        this.decreaseFuel -= 1;
    }

    @Override
    public void repair() {
        this.fuel = MAX_FUEL;

    }
}
