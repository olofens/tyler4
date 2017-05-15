package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;

/**
 * This is the main class for our game, this is where the magic happens and where our game is generated,
 * basically, the MiniMiner.
 */

public class MiniMiner extends Game {

    public static final short DEFAULT_BIT = 1;
    public static final short MINER_BIT = 2;
    public static final short TILE_BIT = 4;
    public static final short STORE_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short COAL_BIT = 32;
    public static final short IRON_BIT = 64;
    public static final short REDSTONE_BIT = 128;
    public static final short LAPIZ_BIT = 256;


    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
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
