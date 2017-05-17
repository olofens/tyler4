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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Tools.PauseButtonHandler;
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

public class Hud implements Disposable, IListener, IHudUpdater {


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
    private PauseButtonHandler pHandler;

    /**
     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch) {

        tpHandler = new TouchpadHandler();
        storeHandler = new StoreHandler();
        dbHandler = new DrillButtonHandler();
        pHandler = new PauseButtonHandler();

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);


        //Create a Stage and add TouchPad

        pHandler.getPauseBtn().setTransform(true);

        stage = new Stage(viewport, spriteBatch);
        stage.addActor(dbHandler.getdrillButton());
        stage.addActor(storeHandler.getStorePopup());
        stage.addActor(tpHandler.getTouchpad());
        stage.addActor(pHandler.getPauseBtn());
        Gdx.input.setInputProcessor(stage);

        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        fuelLabel = new Label("100%", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hullLabel = new Label("100", new Label.LabelStyle(new BitmapFont(), Color.RED));

        table.add(fuelLabel).expandX().padTop(5);
        table.add(scoreLabel).expandX().padTop(5);
        table.add(hullLabel).expandX().padTop(5);
        table.add(pHandler.getPauseBtn()).expandX().padTop(10);


        stage.addActor(table);
        Listener.BUS.addListener(this);
        HudUpdater.FUEL.addListener(this);


    }

    private void adjustFuelLabel(Integer fuel, Color color, String fuelString) {
        fuelLabel.setColor(color);
        fuelLabel.setText(fuelString);
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
        adjustFuelLabel(data.getFuel(), data.getColor(), data.getString());
    }
}
