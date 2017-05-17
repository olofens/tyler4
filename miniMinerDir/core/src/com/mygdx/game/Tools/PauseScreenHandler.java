package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MiniMiner;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.table;

/**
 * Created by erikstrid on 2017-05-17.
 */


public class PauseScreenHandler {

    private TextButton resumeButton;
    private TextButton menuButton;

    private boolean isResumed = true;




    public TextButton getResumeButton() {
        return resumeButton;
    }
    public TextButton getMenuButton() {
        return menuButton;
    }

    public PauseScreenHandler(){



        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));


        resumeButton = new TextButton("Resume", storeSkin);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //resume game
            }
        });

        menuButton = new TextButton("Main Menu", storeSkin);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


            }
        });


    }
}
