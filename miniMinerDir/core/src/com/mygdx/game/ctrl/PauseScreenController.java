package com.mygdx.game.ctrl;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.view.PauseScreenView;

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

    public Table getTable() {
        return psv.getTable();
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
