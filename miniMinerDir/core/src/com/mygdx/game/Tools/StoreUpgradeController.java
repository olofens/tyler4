package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;

/**
 * Created by olofenstrom on 2017-05-20.
 */

public class StoreUpgradeController {

    private StoreView storeView;

    public StoreUpgradeController(){

        storeView = new StoreView("Upgrade fuel", "Upgrade hull");

        storeView.getTopButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.FUELUPGRADE));
            }
        });

        storeView.getBottomButton().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.HULLUPGRADE));
            }
        });
    }

    public Table getStorePopup(){
        return storeView.getTable();
    }
}
