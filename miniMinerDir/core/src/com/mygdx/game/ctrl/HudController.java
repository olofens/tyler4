package com.mygdx.game.ctrl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.mygdx.game.event.drill.DrillData;
import com.mygdx.game.event.drill.DrillListener;
import com.mygdx.game.event.general.IListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;
import com.mygdx.game.event.messages.IMessageListener;
import com.mygdx.game.event.messages.MessageData;
import com.mygdx.game.event.messages.MessageListener;
import com.mygdx.game.view.HudView;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class HudController implements IListener, IMessageListener {

    public HudView hudView;

    public HudController(SpriteBatch spriteBatch) {

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
