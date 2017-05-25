package com.mygdx.game.Tools;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class PauseButtonController {

    private PauseButtonView pbv;

    public PauseButtonController() {
        pbv = new PauseButtonView();
        pbv.getPauseButton().addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("CLICK.");
                psHandler.setPaused(true);
                return true;
            }
        });
    }
}
