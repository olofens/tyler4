package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Omaroueidat on 03/05/17.
 */
public class FuelTank implements IResource {


    public Integer fuel;

    public Label fuelLabel;

    public FuelTank() {
        fuel = 100000;

        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();

        fuelLabel = new Label(String.format("%03d", fuel), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.RED));
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
    }

    public void adjustFuel(Integer knobPercent){
        fuel = fuel - Math.abs(3*knobPercent);


        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        strFuel.substring(0,fuelLength-3);
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
    }

    public Integer getFuel() {
        return fuel;
    }

    public Label getFuelLabel() {
        return fuelLabel;
    }
}
