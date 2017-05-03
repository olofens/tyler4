package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
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
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.IListener;
import com.mygdx.game.event.Listener;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable, IListener {
    public Stage stage;
    private Viewport viewport;
    private Dialog storePopup;

    public Integer score;
    public Integer fuel;
    public Integer hull;

    private Label scoreLabel;
    private Label fuelLabel;
    private Label hullLabel;

    public Touchpad touchpad;



    /**
     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch){

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
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
        storePopup.setBounds((Constants.V_WIDTH - storepopupImage.getWidth()) / 2,
                (Constants.V_HEIGHT - storepopupImage.getHeight() - 10),
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

        touchpad.setBounds(Constants.V_WIDTH-110, 15, 100, 100);

        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(storePopup);
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);

        fuel = 100000;
        score = 0;
        hull = 100;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();

        fuelLabel = new Label(String.format("%03d",  fuel), new Label.LabelStyle(new BitmapFont(), Color.RED));
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hullLabel = new Label(String.format("%03d", hull), new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        table.add(scoreLabel).expandX().padTop(10);
        table.add(fuelLabel).expandX().padTop(10);
        table.add(hullLabel).expandX().padTop(10);


        stage.addActor(table);
        Listener.BUS.addListener(this);


    }

    public void adjustFuel(Integer knobPercent){
        fuel = fuel - Math.abs(3*knobPercent);

        System.out.print(knobPercent.toString() + "\n");

        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        strFuel.substring(0,fuelLength-3);
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
    }

    public boolean isTouchingUp(){
        if(touchpad.getKnobPercentY() > 0.5){
            return true;
        }
        else{
            return false;
        }
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

    public void update() {
        toggleStoreVisibility();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
