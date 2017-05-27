package com.mygdx.game.ctrl;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.view.StoreView;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class StoreRepairController {

    private StoreView storeView;

    public StoreRepairController(){

        storeView = new StoreView("Refill fuel", "Repair");
        storeView.getTopButton().addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               Listener.BUS.update(new Shout(Shout.Tag.FUELREPAIR));
           }
        });

        storeView.getBottomButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.HULLREPAIR));
            }
        });

    }

    public Table getStorePopup(){
        return storeView.getTable();
    }
}
