package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.PauseButtonController;
import com.mygdx.game.ctrl.PauseScreenController;
import com.mygdx.game.ctrl.StoreRepairController;
import com.mygdx.game.ctrl.StoreUpgradeController;
import com.mygdx.game.ctrl.TouchpadController;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.ev.general.IListener;
import com.mygdx.game.ev.general.Listener;
import com.mygdx.game.ev.general.Shout;
import com.mygdx.game.ev.hud.HudData;
import com.mygdx.game.ev.hud.HudUpdater;
import com.mygdx.game.ev.hud.IHudUpdater;

/**
 * Created by Olof Enström on 2017-05-25.
 */

public class HudView implements Disposable, IHudUpdater, IListener {

    private static HudView instance;

    private Viewport viewport;
    public Stage stage;
    public Stage stage2;
    public Stage stage3;

    private PauseButtonController pauseButtonController;
    public TouchpadController tpHandler;
    private StoreRepairController storeRepairController;
    private DrillButtonView dbHandler;
    private PauseScreenController psHandler;
    private StoreUpgradeController suHandler;

    private Table table;
    private Table table2;
    private Table table3;

    private Label fuelLabel;
    private Label cashLabel;
    private Label hullLabel;
    private Label msgLabel;

    private Skin storeSkin;

    private HudView(SpriteBatch spriteBatch) {

        HudUpdater.BUS.addListener(this);
        Listener.BUS.addListener(this);

        storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, spriteBatch);
        stage2 = new Stage(viewport, spriteBatch);
        stage3 = new Stage(viewport, spriteBatch);

        pauseButtonController = PauseButtonController.getInstance();
        tpHandler = new TouchpadController();
        storeRepairController = new StoreRepairController();
        dbHandler = new DrillButtonView();
        psHandler = PauseScreenController.getInstance();
        suHandler = new StoreUpgradeController();

        stage = new Stage(viewport, spriteBatch);
        stage.addActor(dbHandler.getdrillButton());
        stage.addActor(storeRepairController.getStorePopup());
        stage.addActor(tpHandler.getTouchpad());
        stage.addActor(pauseButtonController.getPauseButton());
        stage.addActor(suHandler.getStorePopup());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.top();
        table.setFillParent(true);

        fuelLabel = new Label("100%", new Label.LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE));
        cashLabel = new Label("1000", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hullLabel = new Label("100", new Label.LabelStyle(new BitmapFont(), Color.RED));
        msgLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(fuelLabel).expandX().padTop(5);
        table.add(cashLabel).expandX().padTop(5);
        table.add(hullLabel).expandX().padTop(5);
        table.add(pauseButtonController.getPauseButton()).expandX().padTop(10);

        stage.addActor(table);

        stage2.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
        stage2.addActor(psHandler.getTable());
    }

    public static HudView getInstance(SpriteBatch spriteBatch) {
        if (instance == null) {
            instance = new HudView(spriteBatch);
        }
        return instance;
    }

    private void initMessage() {
        table3 = new Table(storeSkin);
        table3.center();
        table3.setBounds(0, 90, Constants.V_WIDTH, 210);
        table3.add(msgLabel);

        stage3.addAction(Actions.alpha(1));
        stage3.addAction(Actions.moveBy(0, 100, 3));
        stage3.addActor(table3);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                stage3.addAction(Actions.sequence(Actions.fadeOut(2)));
            }
        }, 1f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                stage3.addAction(Actions.moveBy(0, -100));
            }
        }, 2f);
    }

    private void adjustFuelLabel(Color color, String fuelString) {
        fuelLabel.setColor(color);
        fuelLabel.setText(fuelString);
    }

    private void adjustHullLabel(String hullString) {
        hullLabel.setText(hullString);

    }

    private void adjustCashLabel(String cashString) {
        cashLabel.setText(cashString);

    }

    private void toggleActorVisibility(Actor actor) {
        boolean visible = actor.isVisible();
        actor.setVisible(!visible);
    }

    public void showMessage(String msg) {
        initMessage();
        msgLabel.setText(msg);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                table3.setVisible(false);
            }
        }, 2f);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public ImageButton getDrillButton() {
        return dbHandler.getdrillButton();
    }

    public TouchpadController getTouchpadController() {
        return tpHandler;
    }

    public PauseScreenController getPsHandler() {
        return psHandler;
    }

    @Override
    public void update(HudData data) {
        adjustFuelLabel(data.getColor(), data.getFuel());
        adjustHullLabel(data.getHull());
        adjustCashLabel(data.getCash());
    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.STORE) {
            toggleActorVisibility(storeRepairController.getStorePopup());
        } else if (shout.getTag() == Shout.Tag.STORE_UPGRADE) {
            toggleActorVisibility(suHandler.getStorePopup());
        }
    }
}
