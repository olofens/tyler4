package com.mygdx.game.Tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Olof Enström on 2017-05-03.
 */

public class SoundHandler {
    private static SoundHandler instance = null;
    private AssetManager manager;
    private Sound tank;
    private Sound rocket;

    private SoundHandler() {
        manager = new AssetManager();
        loadSoundAssets();
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
        tank = manager.get("sound/tank_sound.wav");
        rocket = manager.get("sound/rocket_sound.mp3");
    }

    public void playTankSound() {
        tank.play();
    }

    public void playRocketSound() {
        System.out.println("played");
        rocket.play(1.0f);
    }

    public void stopTankSound() {
        tank.pause();
    }

    public void stopRocketSound() {
        rocket.pause();
    }
}
