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
import com.mygdx.game.Tools.PauseButtonController;
import com.mygdx.game.Tools.PauseButtonView;
import com.mygdx.game.Tools.PauseScreenController;
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

public class Hud implements IListener, IMessageListener {

    public HudView hudView;

    public Hud(SpriteBatch spriteBatch) {

        hudView = HudView.getInstance(spriteBatch);

        Listener.BUS.addListener(this);
        MessageListener.BUS.addListener(this);

        initDrillButtonListener();
    }

    private void initDrillButtonListener() {
        hudView.getDrillButton().addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DrillListener.BUS.update(
                        new DrillData(hudView.getTouchpadController().getDrillDirection(), false));
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                DrillListener.BUS.update(
                        new DrillData(hudView.getTouchpadController().getDrillDirection(), false));
            }
        });
    }

    public boolean isPaused(){
        return hudView.getPsHandler().isPaused();
    }

    public boolean isNewScreen(){
        return hudView.getPsHandler().isNewScreen();
    }

    public void setIsNewScreen(boolean val){
        hudView.getPsHandler().setNewScreen(val);
    }

    public Table getTable2() {
        return hudView.getPsHandler().getTable();
    }



    private void displayMessage(String msg){
        hudView.showMessage(msg);

    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.NEW_TP_DIR) {
            DrillData.DrillDirection dir = hudView.getTouchpadController().getDrillDirection();

            DrillListener.BUS.update(new DrillData(dir, true));
        }

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
