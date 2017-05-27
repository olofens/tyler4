package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Scenes.HudView;
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

        GameWorld gameWorld = GameWorld.getInstance();


        assertTrue(gameWorld.getMap() != null);

    }
}