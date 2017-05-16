package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Tools.StoreHandler;
import com.mygdx.game.Tools.TouchpadHandler;
import com.mygdx.game.Tools.DrillButtonHandler;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.*;
import com.mygdx.game.items.FuelTank;
import com.mygdx.game.items.Hull;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable, IListener,IHudUpdater {


    public Stage stage;
    private Viewport viewport;


    public Integer score;

    private Label scoreLabel;
    private Label fuelLabel;
    private Label hullLabel;

    //TODO fix public
    public TouchpadHandler tpHandler;
    private StoreHandler storeHandler;
    private DrillButtonHandler dbHandler;

    /**
     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch) {

        tpHandler = new TouchpadHandler();
        storeHandler = new StoreHandler();
        dbHandler = new DrillButtonHandler();

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);



        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(dbHandler.getdrillButton());
        stage.addActor(storeHandler.getStorePopup());
        stage.addActor(tpHandler.touchpad);
        Gdx.input.setInputProcessor(stage);

        Hull hull = new Hull();
        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        //TODO Find a way to access strFuel and FuelLength in a better way...
        FuelTank ft = new FuelTank();

        fuelLabel = new Label(String.format("%03d", ft.getFuel()), new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));

        fuelLabel.setText(ft.getStrFuel().substring(0, ft.getFuelLength() - 3) + "%");


        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        hullLabel = new Label(String.format("%03d", hull.getHull()), new Label.LabelStyle(new BitmapFont(), Color.RED));


        table.add(fuelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.add(hullLabel).expandX().padTop(10);


        stage.addActor(table);
        Listener.BUS.addListener(this);
        HudUpdater.FUEL.addListener(this);


    }

    private void adjustFuelLabel(Integer fuel, Color color) {


        fuelLabel.setColor(color);
        String strFuel = fuel.toString();
        Integer fuelLength = fuel.toString().length();
        strFuel.substring(0, fuelLength - 3);
        fuelLabel.setText(strFuel.substring(0, fuelLength - 3) + "%");
    }


    public void toggleStoreVisibility() {
        storeHandler.getStorePopup().setVisible(!storeHandler.getStorePopup().isVisible());
    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.STORE) {
            toggleStoreVisibility();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void update(HudData data) {
        adjustFuelLabel(data.getFuel(),data.getColor());
    }
}
