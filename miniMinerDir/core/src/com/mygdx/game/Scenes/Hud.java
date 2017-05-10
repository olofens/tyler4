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
import com.mygdx.game.Tools.StoreHandler;
import com.mygdx.game.Tools.TouchpadHandler;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.IListener;
import com.mygdx.game.event.Listener;
import com.mygdx.game.items.FuelTank;
import com.mygdx.game.items.Hull;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable, IListener {
    public Stage stage;
    private Viewport viewport;




    public Integer score;
    public Integer fuel;
    public Integer hull;

    private Label scoreLabel;
    private Label fuelLabel;
    private Label hullLabel;

    //TODO fix public
    public TouchpadHandler tpHandler;
    private StoreHandler storeHandler;
    /**
     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch) {

        tpHandler = new TouchpadHandler();
        storeHandler = new StoreHandler();

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);



        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(storeHandler.getStorePopup());
        stage.addActor(tpHandler.touchpad);
        Gdx.input.setInputProcessor(stage);

        Hull hull = new Hull();
        fuel = 100000;
        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        FuelTank ft = new FuelTank(table);
//        String strFuel = fuel.toString();
        //      Integer fuelLength = fuel.toString().length();

        //ft.fuelLabel = new Label(String.format("%03d",  fuel), new Label.LabelStyle(new BitmapFont(), Color.RED));
        //fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //hull.hullLabel = new Label(String.format("%03d", hull), new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        table.add(scoreLabel).expandX().padTop(10);
        // table.add(ft.getFuelLabel()).expandX().padTop(10);
        table.add(hull.getHullLabel()).expandX().padTop(10);


        stage.addActor(table);
        Listener.BUS.addListener(this);


    }
    //TODO remove sopp
  /*  public void adjustFuel(Integer knobPercent){
        fuel = fuel - Math.abs(3*knobPercent);


        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        strFuel.substring(0,fuelLength-3);
        fuelLabel.setText(strFuel.substring(0,fuelLength-3) + "%");
    }*/



    public void toggleStoreVisibility() {
        storeHandler.getStorePopup().setVisible(!storeHandler.getStorePopup().isVisible());
    }

    public void update() {
        toggleStoreVisibility();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
