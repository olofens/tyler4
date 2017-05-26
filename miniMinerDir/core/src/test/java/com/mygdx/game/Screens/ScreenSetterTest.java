package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Tools.ScreenSetter;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class ScreenSetterTest {

    @Test
    public void testPlayScreenCreation(){

        Game game = new MiniMiner();
        ScreenSetter sh = new ScreenSetter(game);
        sh.createPlayScreen();

        assertTrue(game.getScreen() instanceof PlayScreen);

    }

    @Test
    public void testStartMenuScreenCreation(){

        Game game = new MiniMiner();
        ScreenSetter sh = new ScreenSetter(game);
        sh.createStartMenuScreen();

        assertTrue(game.getScreen() instanceof StartMenuScreen);

    }

    @Test
    public void testGameOverScreenCreation(){

        Game game = new MiniMiner();
        ScreenSetter sh = new ScreenSetter(game);
        sh.createGameOverScreen();

        assertTrue(game.getScreen() instanceof GameOverScreen);

    }

}
