package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Utils.Constants;

import javax.swing.text.View;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class HudView {

    private Viewport viewport;
    private Stage stage;
    private Stage stage2;
    private Stage stage3;

    public HudView(SpriteBatch spriteBatch) {
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        stage2 = new Stage(viewport, spriteBatch);
        stage3 = new Stage(viewport, spriteBatch);    }

}
