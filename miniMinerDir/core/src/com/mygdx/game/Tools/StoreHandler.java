package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Shout;

import java.io.File;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class StoreHandler {

    private Dialog storePopup;
    private TextButton fillFuel;
    private Table table;
    private TextButton fixHull;

    public StoreHandler(){


        //Store


        //FileHandle fh = new FileHandle("skins/font-title-export.png");
        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"), new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));
        //Window.WindowStyle ws = storeSkin.get(Window.WindowStyle.class);
        fillFuel = new TextButton("Refill fuel", storeSkin);
        fillFuel.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                Listener.BUS.update(new Shout(Shout.Tag.FUEL));
            }

        });

        fixHull = new TextButton("Repair", storeSkin);


        table = new Table(storeSkin);
        table.background(storeSkin.get(Window.WindowStyle.class).background);
        //table.setFillParent(true);
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
