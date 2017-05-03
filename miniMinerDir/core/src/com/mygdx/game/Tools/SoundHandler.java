package com.mygdx.game.Tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Olof Enström on 2017-05-03.
 */

public class SoundHandler {
    private static SoundHandler instance = null;
    private AssetManager manager;

    private SoundHandler() {
        manager = new AssetManager();
    }

    public static SoundHandler getInstance() {
        if (instance == null) {
            instance = new SoundHandler();
        }
        return instance;
    }

    private void loadSoundAssets() {
        manager.load("sound/rocket_sound.mp3", Sound.class);
        manager.load("sound/tank_sound.wav", Sound.class);
        manager.finishLoading();
    }
}
