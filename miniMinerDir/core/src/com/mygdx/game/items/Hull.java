package com.mygdx.game.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Omaroueidat on 03/05/17.
 */

/**
 * The class that dictates the health of the miner
 */
public class Hull implements IGear{

    /**
     * Label for the health
     */
    private Label hullLabel;

    /**
     * Variable for the ammount of health
     */
    private Integer hull;

    /**
     * Constructor which gives our default value for the health and creates a label;
     */
    public Hull(){
        hull = 100;
        hullLabel = new Label(String.format("%03d", hull), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.RED));
    }

    /**
     *
     * @return hullLabel
     */
    public Label getHullLabel() {
        return hullLabel;
    }

    /**
     *
     * @return hull
     */
    public Integer getHull() {
        return hull;
    }
}
