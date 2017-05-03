package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;

/**
 * Created by Omaroueidat on 03/05/17.
 */
public class Hull implements IResource {
    private Label hullLabel;

    private Integer hull;

    public Hull(){
        hull = 100;
        hullLabel = new Label(String.format("%03d", hull));
    }
}
