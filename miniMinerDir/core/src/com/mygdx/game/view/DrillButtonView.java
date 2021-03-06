package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class DrillButtonView {

    private ImageButton drillButton;

    public DrillButtonView() {
        Texture drillTexture = new Texture("driller_drill_only.png");

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(drillTexture));

        drillButton = new ImageButton(drawable);
        drillButton.setPosition(20, 45);
    }

    public ImageButton getdrillButton() {
        return drillButton;
    }


}
