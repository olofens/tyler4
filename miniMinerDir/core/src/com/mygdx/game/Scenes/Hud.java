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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Dialog storePopup;

    private Integer score;

    private Label scoreLabel;

    public Touchpad touchpad;

    //public Integer blockSpeed;

    //public Sprite blockSprite;

    /**
     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch){

        viewport = new FitViewport(MiniMiner.V_WIDTH, MiniMiner.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        //Store

        Skin storeSkin = new Skin();
        Window.WindowStyle ws = new Window.WindowStyle();
        ws.titleFont = new BitmapFont();
        storeSkin.add("default", ws);

        Texture storepopupImage = new Texture("storepopup.png");
        storeSkin.add("popUpImage", storepopupImage);

        ws.background = storeSkin.getDrawable("popUpImage");

        storePopup = new Dialog("Store", storeSkin);
        storePopup.setBounds((MiniMiner.V_WIDTH - storepopupImage.getWidth()) / 2,
                (MiniMiner.V_HEIGHT - storepopupImage.getHeight() - 10),
                storepopupImage.getWidth(), storepopupImage.getHeight());

        storePopup.setVisible(false);

        //Touchpad
        //Create a touchpad skin
        Skin touchpadSkin = new Skin();
        //Set background image
        //touchpadSkin.add("touchBackground", new Texture("touchPad/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("touchPad/rsz_1touchknob.png"));
        //Create TouchPad Style
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        //Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
        Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        //touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)

        touchpad.setBounds(MiniMiner.V_WIDTH-110, 15, 100, 100);

        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(storePopup);
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);

        score = 0;


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);


    }

    public boolean isTouchingRight(){
        if(touchpad.getKnobPercentX() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isTouchingLeft(){
        if(touchpad.getKnobPercentX() < 0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isTouchingDown(){
        if(touchpad.getKnobPercentY() < -0.4){
            return true;
        }
        else{
            return false;
        }
    }

    public void toggleStoreVisibility() {
        storePopup.setVisible(!storePopup.isVisible());
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
