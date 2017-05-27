package com.mygdx.game.ctrl;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.view.PauseButtonView;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class PauseButtonController {

    private static PauseButtonController instance;

    private PauseButtonView pbv;
    private PauseScreenController psc;

    private PauseButtonController() {
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

    public static PauseButtonController getInstance() {
        if (instance == null) {
            instance = new PauseButtonController();
        }
        return instance;
    }

    public ImageButton getPauseButton() {
        return pbv.getPauseButton();
    }

}
