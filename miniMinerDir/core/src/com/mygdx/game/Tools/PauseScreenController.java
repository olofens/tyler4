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


public class PauseScreenController {

    private static PauseScreenController instance;
    private PauseScreenView psv;

    private boolean newScreen = false;
    private boolean isPaused = false;

    private PauseScreenController(){

        psv = new PauseScreenView();

        psv.getResumeButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = false;
            }
        });

        psv.getMenuButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newScreen = true;
            }
        });
    }

    public static PauseScreenController getInstance() {
        if (instance == null) {
            instance = new PauseScreenController();
        }
        return instance;
    }

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
        return psv.getResumeButton();
    }
    public TextButton getMenuButton() {
        return psv.getMenuButton();
    }
}
