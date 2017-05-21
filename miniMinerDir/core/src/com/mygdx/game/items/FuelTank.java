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
    public final int fuelUpgrade = 50000;

    //This is the variable that decreases the fuel by a certain amount every update
    private int decreaseFuel;

    // String that is sent to hud to paint out
    private String fuelString;

    public final double FUEL_DECREASE_UPGRADE = 0.2;

    private int upgradeLevel = 0;

    public final int MAX_UPGRADE_LEVEL = 5;

    /**
     * Constructor which gives our default fuel ammount and makes our label with a red color and a certain position
     */
    public FuelTank() {
        maxFuel = 100000;
        fuel = maxFuel;
        decreaseFuel = 20;
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
            fuel -= (Math.abs(minerVelocityX) * decreaseFuel);
        if (minerVelocityY > 0) {
            fuel -= (Math.abs(minerVelocityY) * decreaseFuel);
        }

        Integer percentage = (fuel*100)/maxFuel;
        String strFuel = percentage.toString();
        Integer fuelLength = fuel.toString().length();
        //fuelString = strFuel.substring(0, fuelLength - 3) + "%";
        fuelString = strFuel + "%";

    }

    public boolean isEmpty() {
        return fuel <= 0;
    }

    public Integer getFuel() {
        return fuel;
    }


    public String getFuelString() {
        return fuelString;
    }

    public int getUpgradeLevel(){
        return this.upgradeLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgrade() {

        if (upgradeLevel <= MAX_UPGRADE_LEVEL) {
            this.maxFuel += fuelUpgrade;
            this.decreaseFuel -= FUEL_DECREASE_UPGRADE;
            upgradeLevel++;
        }
        if (upgradeLevel == MAX_UPGRADE_LEVEL) {
            System.out.println("Fuel fully upgraded");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void repair() {
        this.fuel = maxFuel;
    }
}
