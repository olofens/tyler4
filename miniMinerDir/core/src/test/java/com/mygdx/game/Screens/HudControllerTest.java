package com.mygdx.game.Screens;

import com.mygdx.game.ctrl.PauseButtonController;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-27.
 */

public class HudControllerTest extends GameTest {

    @Test
    public void PauseButtonCreationTest() throws Exception {

        PauseButtonController pbc = PauseButtonController.getInstance();

        assertTrue(pbc.getPauseButton() != null);


    }

}
