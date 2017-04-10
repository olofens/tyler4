package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Omaroueidat on 03/04/17.
 */

public class StartMenuScreen implements Screen {

    private Texture playButton;
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected ScreenSwitcher ss;

    /**
     * The screen where you will be able to choose to play, edit the options, or read eventual instructions.
     *
     * @param ss
     */
    public StartMenuScreen(ScreenSwitcher ss){
        this.playButton = new Texture("playButton.png");
        this.ss=ss;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }
    public void handleInput(){

    }
    public void update(float dt){

    }
    public void render(SpriteBatch sb){

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
