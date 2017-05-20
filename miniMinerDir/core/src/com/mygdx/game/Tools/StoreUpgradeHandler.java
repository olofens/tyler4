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

public class StoreUpgradeHandler {

    private TextButton fillFuel;
    private Table table;
    private TextButton fixHull;

    public StoreUpgradeHandler(){

        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        fillFuel = new TextButton("Upgrade fuel", storeSkin);
        fillFuel.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.FUELUPGRADE));
            }

        });

        fixHull = new TextButton("Upgrade hull", storeSkin);
        fixHull.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.HULLUPGRADE));
            }
        });


        table = new Table(storeSkin);
        table.background(storeSkin.get(Window.WindowStyle.class).background);
        table.center();
        table.setBounds(5, 80, 230, 290);
        table.add(fillFuel).width(150).height(100).pad(30, 0, 0, 0);
        table.row();
        table.add(fixHull).width(150).height(100);
        table.setVisible(false);
    }

    public Table getStorePopup(){
        return table;
    }
}
