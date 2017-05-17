package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Shout;

/**
 * Created by erikstrid on 2017-05-16.
 */

public class PauseButtonHandler {

    private ImageButton pauseBtn;



    public ImageButton getPauseBtn() {
        return pauseBtn;
    }


    public PauseButtonHandler(){

        Texture myTexture = new Texture(("pause-26.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);

        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        pauseBtn = new ImageButton(myTexRegionDrawable);
        pauseBtn.scaleBy(0.1f);


        pauseBtn.addListener(new ClickListener() {


            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("CLICK.");
                //pause game
                return true;
            }

        });
    }

}
