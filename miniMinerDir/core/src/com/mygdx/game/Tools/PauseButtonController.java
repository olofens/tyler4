package com.mygdx.game.Tools;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class PauseButtonController {

    private PauseButtonView pbv;
    private PauseScreenController psc;

    public PauseButtonController() {
        psc = PauseScreenController.getInstance();
        pbv = new PauseButtonView();
        pbv.getPauseButton().addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("CLICK.");
                psc.setPaused(true);
                return true;
            }
        });
    }

    public ImageButton getPauseButton() {
        return pbv.getPauseButton();
    }

}
