package com.mygdx.game.Screens;

import com.mygdx.game.model.GameWorld;

import org.junit.Test;

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