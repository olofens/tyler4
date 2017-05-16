package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Shout;

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

        drillButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Listener.BUS.update(new Shout(Shout.Tag.DRILL));
                System.out.println("ran the bus on PRESS");
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Listener.BUS.update(new Shout(Shout.Tag.DRILL));
                System.out.println("ran the bus on RELEASE");
            }
        });
    }

    public ImageButton getdrillButton() {
        return drillButton;
    }


}
