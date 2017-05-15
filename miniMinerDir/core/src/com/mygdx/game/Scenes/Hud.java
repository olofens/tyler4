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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
import com.mygdx.game.event.Shout;
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
    //
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

        Skin skin = new Skin();
        Texture drillTexture = new Texture("driller_drill_only.png");

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(drillTexture));

        ImageButton drillButton = new ImageButton(drawable);
        drillButton.setPosition(20, 45);

        drillButton.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Listener.BUS.update(new Shout(Shout.Tag.DRILL));
                System.out.println("ran the bus on PRESS");
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Listener.BUS.update(new Shout(Shout.Tag.DRILL));
                System.out.println("ran the bus on RELEASE");
            }
        });


        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(drillButton);
        stage.addActor(storeHandler.getStorePopup());
        stage.addActor(tpHandler.touchpad);
        Gdx.input.setInputProcessor(stage);

        Hull hull = new Hull();
        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);


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


    }

    //TODO remove sopp
    public void adjustFuelLabel(FuelTank ft) {
        fuel = ft.getFuel();

        if (fuel > 60000) {
            fuelLabel.setColor(Color.GREEN);

        }
        else if(fuel < 60000 && fuel > 20000){
            fuelLabel.setColor(Color.ORANGE);
        }
        else{
            fuelLabel.setColor(Color.RED);
        }
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
}
