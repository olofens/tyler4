package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartMenuScreen;
import com.mygdx.game.Tools.ScreenHandler;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class ScreenHandlerTest {

    @Test
    public void testPlayScreenCreation(){

        Game game = new MiniMiner();
        ScreenHandler sh = new ScreenHandler(game);
        sh.createPlayScreen();

        assertTrue(game.getScreen() instanceof PlayScreen);

    }

    @Test
    public void testStartMenuScreenCreation(){

        Game game = new MiniMiner();
        ScreenHandler sh = new ScreenHandler(game);
        sh.createStartMenuScreen();

        assertTrue(game.getScreen() instanceof StartMenuScreen);

    }

    @Test
    public void testGameOverScreenCreation(){

        Game game = new MiniMiner();
        ScreenHandler sh = new ScreenHandler(game);
        sh.createGameOverScreen();

        assertTrue(game.getScreen() instanceof GameOverScreen);

    }

}
