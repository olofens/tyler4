package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the fuel for the miner
 */
public class FuelTank implements IResource {

    /**
     * The fuel variable
     */
    private Integer fuel;

    /**
     * Fuel label
     */
    private Label fuelLabel;

    /**
     * Constructor which gives our default fuel ammount and makes our label with a red color and a certain position
     */
    public FuelTank(){
        fuel = 100000;

        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();

        fuelLabel = new Label(String.format("%03d", fuel), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.RED));
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");

    }

    public FuelTank(Table table) {
        fuel = 100000;

        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();

        fuelLabel = new Label(String.format("%03d", fuel), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.RED));
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");

        table.add(fuelLabel).expandX().padTop(10);
    }

    /**
     * Method that adjusts the fuel when the miner is moving
     * @param knobPercent
     */
    public void adjustFuel(Integer knobPercent){
        fuel = fuel - Math.abs(3*knobPercent);


        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        strFuel.substring(0,fuelLength-3);
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
    }

    /**
     *
     * @return fuel
     */
    public Integer getFuel() {
        return fuel;
    }

    /**
     * A lable getter
     * @return fuelLabel
     */
    public Label getFuelLabel() {
        return fuelLabel;
    }
}
