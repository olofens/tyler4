package com.mygdx.game.items;

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
    //this is the maximum amount of fuel the Miner can have, can be upgraded to a bigger integer
    private int maxFuel;
    private Integer fuel;

    //This is the variable that decreases the fuel by a certain amount every update
    private int decreaseFuel;

    // String that is sent to hud to paint out
    private String fuelString;

    /**
     * Constructor which gives our default fuel ammount and makes our label with a red color and a certain position
     */
    public FuelTank() {
        maxFuel = 100000;
        fuel = maxFuel;
        decreaseFuel = 10;
    }


    /**
     * If the miner is moved by gravity the fuel will not decrease, method will check if the miner has a velocity
     * on the X-axis then on the Y-axis to determine how much fuel to decrease.
     *
     * @param minerVelocityX the velocity of the miner in the X axis
     * @param minerVelocityY the velocity of the miner in the Y axis
     */
    public void adjustFuel(Integer minerVelocityX, Integer minerVelocityY) {

        if (minerVelocityX != 0)
            fuel = fuel - (Math.abs(minerVelocityX) * decreaseFuel);
        if (minerVelocityY > 0) {
            fuel = fuel - (Math.abs(minerVelocityY) * decreaseFuel);
        }
        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        fuelString = strFuel.substring(0, fuelLength - 3) + "%";

    }

    public boolean isEmpty(){
        if(fuel <= 98000){
            return true;
        }
        return false;
    }

    public Integer getFuel() {
        return fuel;
    }


    public int getDecreaseFuel() {
        return decreaseFuel;
    }

    public String getFuelString() {
        return fuelString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade() {
        this.maxFuel += 50000;

        this.decreaseFuel -= 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repair() {
        this.fuel = maxFuel;

    }
}
