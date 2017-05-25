package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Tools.PauseScreenHandler;
import com.mygdx.game.Tools.StoreRepairController;
import com.mygdx.game.Tools.StoreUpgradeController;
import com.mygdx.game.Tools.TouchpadController;
import com.mygdx.game.Tools.DrillButtonView;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.drill.DrillData;
import com.mygdx.game.event.drill.DrillListener;
import com.mygdx.game.event.general.IListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;
import com.mygdx.game.event.hud.HudData;
import com.mygdx.game.event.hud.HudUpdater;
import com.mygdx.game.event.hud.IHudUpdater;
import com.mygdx.game.event.messages.IMessageListener;
import com.mygdx.game.event.messages.MessageData;
import com.mygdx.game.event.messages.MessageListener;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class Hud implements Disposable, IListener, IHudUpdater, IMessageListener {


    public Stage stage;
    public Stage stage2;
    public Stage stage3;

    public Table table;
    public Table table2;
    public Table table3;

    private Viewport viewport;


    public Integer score;

    private Label cashLabel;
    private Label fuelLabel;
    private Label hullLabel;
    private Label msgLabel;

    private ImageButton pauseBtn;

    private Skin storeSkin;


    public TouchpadController tpHandler;
    private StoreRepairController storeRepairController;
    private DrillButtonView dbHandler;
    private PauseScreenHandler psHandler;
    private StoreUpgradeController suHandler;


    /**

     * Creates the HUD for the framework of the game
     *
     * @param spriteBatch
     */
    public Hud(SpriteBatch spriteBatch) {

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        stage2 = new Stage(viewport, spriteBatch);
        stage3 = new Stage(viewport, spriteBatch);

        tpHandler = new TouchpadController();
        storeRepairController = new StoreRepairController();
        dbHandler = new DrillButtonView();
        psHandler = new PauseScreenHandler();
        suHandler = new StoreUpgradeController();
        initDrillButtonListener();


        Texture myTexture = new Texture(("Pause-26.png"));
        TextureRegion myTextureRegion = new TextureRegion(myTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);

        pauseBtn = new ImageButton(myTexRegionDrawable);
        pauseBtn.scaleBy(0.1f);
        pauseBtn.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("CLICK.");
                psHandler.setPaused(true);
                return true;
            }
        });
        pauseBtn.setTransform(true);


        storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        //Create a Stage and add TouchPad
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(dbHandler.getdrillButton());
        stage.addActor(storeRepairController.getStorePopup());
        stage.addActor(tpHandler.getTouchpad());
        stage.addActor(pauseBtn);
        stage.addActor(suHandler.getStorePopup());
        Gdx.input.setInputProcessor(stage);

        score = 0;

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
        table.add(pauseBtn).expandX().padTop(10);

        stage.addActor(table);

        Listener.BUS.addListener(this);
        HudUpdater.BUS.addListener(this);
        MessageListener.BUS.addListener(this);


        table2 = new Table(storeSkin);
        table2.center();
        table2.setBounds(10, 90, 190, 210);
        table2.add(psHandler.getResumeButton());
        table2.row();
        table2.add(psHandler.getMenuButton());

        stage2.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
        stage2.addActor(table2);
    }

    private void initDrillButtonListener() {
        dbHandler.getdrillButton().addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DrillListener.BUS.update(new DrillData(tpHandler.getDrillDirection(), false));
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                DrillListener.BUS.update(new DrillData(tpHandler.getDrillDirection(), false));
            }
        });
    }

    private void initMessage(){

        table3 = new Table(storeSkin);
        table3.center();
        table3.setBounds(0, 90, Constants.V_WIDTH, 210);
        table3.add(msgLabel);

        stage3.addAction(Actions.alpha(1));
        stage3.addAction(Actions.moveBy(0,100,3));
        stage3.addActor(table3);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                stage3.addAction(Actions.sequence(Actions.fadeOut(2)));
            }
        }, 1f);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                stage3.addAction(Actions.moveBy(0, -100));
            }
        }, 2f);
    }

    public boolean isPaused(){
        return psHandler.isPaused();
    }

    public boolean isNewScreen(){
        return psHandler.isNewScreen();
    }

    public void setIsNewScreen(boolean val){
        psHandler.setNewScreen(val);
    }

    private void adjustFuelLabel(Color color, String fuelString)
    {
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



    private void displayMessage(String msg){
        initMessage();
        msgLabel.setText(msg);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                table3.setVisible(false);
            }
        }, 2f);

    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.STORE) {
            toggleActorVisibility(storeRepairController.getStorePopup());
        } else if (shout.getTag() == Shout.Tag.STORE_UPGRADE) {
           toggleActorVisibility(suHandler.getStorePopup());
        } else if (shout.getTag() == Shout.Tag.NEW_TP_DIR) {
            DrillData.DrillDirection dir = tpHandler.getDrillDirection();

            DrillListener.BUS.update(new DrillData(dir, true));
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void update(HudData data) {
        adjustFuelLabel(data.getColor(), data.getFuel());
        adjustHullLabel(data.getHull());
        adjustCashLabel(data.getCash());

    }

    @Override
    public void update(MessageData messageData){
        if(messageData.getMessageType() == MessageData.MessageType.ERROR){
            displayMessage("Purchase failed: Insufficient funds");
            System.out.print("error");
        }
        else if(messageData.getMessageType() == MessageData.MessageType.FUELREPAIR){
            displayMessage("Fuel replenished: -" + messageData.getCash() );
            System.out.print("fuel");

        }
        else if(messageData.getMessageType() == MessageData.MessageType.HULLREPAIR){
            displayMessage("Hull repaired: -" + messageData.getCash() );
            System.out.print("hull");

        }
        else if(messageData.getMessageType() == MessageData.MessageType.FUELUPGRADE){
            displayMessage("Fuel tank upgraded: -" + messageData.getCash() );
            System.out.print("fuel upgrade");

        }
        else if(messageData.getMessageType() == MessageData.MessageType.HULLUPGRADE){
            displayMessage("Hull upgraded: -" + messageData.getCash() );
            System.out.print("hull upgrade");

        }
    }


}
