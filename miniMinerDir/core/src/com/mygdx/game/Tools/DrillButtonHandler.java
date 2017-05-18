package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;

/**
 * Created by Olof Enström on 2017-05-16.
 */

public class DrillButtonHandler {

    private ImageButton drillButton;

    public DrillButtonHandler() {
        Texture drillTexture = new Texture("driller_drill_only.png");

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(drillTexture));

        drillButton = new ImageButton(drawable);
        drillButton.setPosition(20, 45);
    }

    public ImageButton getdrillButton() {
        return drillButton;
    }


}
