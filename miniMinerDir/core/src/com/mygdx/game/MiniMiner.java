package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartMenuScreen;
import com.mygdx.game.Tools.ScreenHandler;

/**
 * This is the main class for our game, this is where the magic happens and where our game is generated,
 * basically, the MiniMiner.
 */

public class MiniMiner extends Game {
    
    public ScreenHandler sh;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        sh = new ScreenHandler(this);
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
