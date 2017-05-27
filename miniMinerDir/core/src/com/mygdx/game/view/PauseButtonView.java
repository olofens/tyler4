package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class PauseButtonView {
    private ImageButton pauseButton;

    public PauseButtonView() {
        Texture myTexture = new Texture(("Pause-26.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);

        pauseButton = new ImageButton(myTexRegionDrawable);
        pauseButton.scaleBy(0.1f);
        pauseButton.setTransform(true);
    }

    public ImageButton getPauseButton() {
        return pauseButton;
    }
}
