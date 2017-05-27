package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.items.GameWorld;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-27.
 */

public class WorldTest extends GameTest {

    @Test
    public void mapTest() throws Exception {

        Constructor<GameWorld> constructor = GameWorld.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        GameWorld gameWorld = constructor.newInstance(new Object[0]);
/*
        TmxMapLoader mapLoader = new TmxMapLoader();
        Map map = mapLoader.load("android/assets/MiniMinerMapV2.tmx");

        assertTrue(map != null);
        */
    }
}