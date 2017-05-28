package com.mygdx.game.model;

/**
 * Created by Omaroueidat on 03/05/17.
 */

import com.badlogic.gdx.graphics.Color;


public class FuelTank implements IGear {

    private int maxFuel;
    private Integer fuel;
    public final int fuelUpgrade = 50000;

    private int decreaseFuel;

    private String fuelString;

    private final double FUEL_DECREASE_UPGRADE = 0.2;

    private int upgradeLevel = 0;

    public final int MAX_UPGRADE_LEVEL = 5;

    private Integer fuelPercentage;

    /**
     * Constructor which gives our default fuel amount and makes our label with a red color and a certain position
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
            fuel -= (Math.abs(minerVelocityX) * decreaseFuel);
        if (minerVelocityY > 0) {
            fuel -= (Math.abs(minerVelocityY) * decreaseFuel);
        }

        fuelPercentage = (fuel * 100) / maxFuel;
        String strFuel = fuelPercentage.toString();
        fuelString = strFuel + "%";

    }

    public Color fuelColor() {
        if (fuelPercentage >= 60)
            return Color.GREEN;
        else if (fuelPercentage < 60 && fuelPercentage >= 20)
            return Color.ORANGE;
        else
            return Color.RED;
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

    public int getUpgradeLevel() {
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
