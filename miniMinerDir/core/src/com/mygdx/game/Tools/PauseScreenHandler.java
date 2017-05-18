package com.mygdx.game.Tools;

import com.badlogic.gdx.Game;
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
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartMenuScreen;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.table;

/**
 * Created by erikstrid on 2017-05-17.
 */


public class PauseScreenHandler {

    private TextButton resumeButton;
    private TextButton menuButton;


    private Game game;

    private boolean newScreen;

    private boolean isPaused = false;

    public boolean isNewScreen() {
        return newScreen;
    }

    public void setNewScreen(boolean newScreen) {
        this.newScreen = newScreen;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public TextButton getResumeButton() {
        return resumeButton;
    }
    public TextButton getMenuButton() {
        return menuButton;
    }


    public void createGameScreen(){
        //game.setScreen(new StartMenuScreen((MiniMiner)game));
        //MiniMiner mm = new MiniMiner();
        //mm.create();
    }

    public PauseScreenHandler(){



        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));


        resumeButton = new TextButton("Resume", storeSkin);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = false;
            }
        });

        menuButton = new TextButton("Main Menu", storeSkin);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newScreen = true;
                //createGameScreen();


            }
        });


    }
}
