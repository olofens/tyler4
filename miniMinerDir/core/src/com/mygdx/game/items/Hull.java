package com.mygdx.game.items;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Omaroueidat on 03/05/17.
 */
public class Hull implements IResource {
    public Label hullLabel;

    public Integer hull;

    public Hull(){
        hull = 100;
        hullLabel = new Label(String.format("%03d", hull), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.GREEN));
    }

    public Label getHullLabel() {
        return hullLabel;
    }

    public Integer getHull() {
        return hull;
    }
}
