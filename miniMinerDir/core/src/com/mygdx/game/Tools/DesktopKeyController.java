package com.mygdx.game.Tools;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;

/**
 * Created by Olof Enström on 2017-05-19.
 */

public class DesktopKeyController implements InputProcessor {


    public DesktopKeyController() {
        System.out.println("setting input processor to DKC");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            System.out.println("starting bus.. wrooom");
            Listener.BUS.update(new Shout(Shout.Tag.KEY_A));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {

            Listener.BUS.update(new Shout(Shout.Tag.KEY_A));
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
