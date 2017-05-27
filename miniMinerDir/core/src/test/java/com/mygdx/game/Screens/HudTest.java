package com.mygdx.game.Screens;

import com.mygdx.game.Tools.PauseButtonController;
import com.mygdx.game.items.GameWorld;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-27.
 */

public class HudTest extends GameTest {

    @Test
    public void PauseButtonCreationTest() throws Exception {

        PauseButtonController pbc = PauseButtonController.getInstance();

        assertTrue(pbc.getPauseButton() != null);


    }

}
