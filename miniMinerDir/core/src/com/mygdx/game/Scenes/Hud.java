package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;

    public Touchpad touchpad;

    public Integer blockSpeed;

    public Sprite blockSprite;


    public Hud(SpriteBatch spriteBatch){

        viewport = new FitViewport(MiniMiner.V_WIDTH, MiniMiner.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);



        //Create a touchpad skin
        Skin touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("touchPad/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("touchPad/touchKnob.png"));
        //Create TouchPad Style
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
        Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(MiniMiner.V_WIDTH-15-200, 15, 200, 200);

        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);

        //Create block sprite
        Texture blockTexture = new Texture(Gdx.files.internal("touchPad/block.png"));
        blockSprite = new Sprite(blockTexture);
        //Set position to centre of the screen
        blockSprite.setPosition(0, 0);

        blockSpeed = 10;
        score = 0;


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);


    }
}
