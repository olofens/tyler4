package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.ScreenSetter;

/**
 * This is the main class for our game, this is where the magic happens and where our game is generated,
 * basically, the MiniMiner.
 */

public class MiniMiner extends Game {
    
    private ScreenSetter sh;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        sh = new ScreenSetter(this);
        sh.createStartMenuScreen();

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
