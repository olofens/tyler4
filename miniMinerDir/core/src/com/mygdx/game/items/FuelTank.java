package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sun.tools.javac.util.Name;

import java.awt.*;

/**
 * Created by Omaroueidat on 03/05/17.
 */
public class FuelTank implements IResource {

    public Integer fuel;

    private Label fuelLabel;

    public FuelTank(){
        fuel = 100000;
        fuelLabel = new Label(String.format("%03d",  fuel));

    }

}
