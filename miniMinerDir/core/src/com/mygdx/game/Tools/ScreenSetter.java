package com.mygdx.game.Tools;

import com.badlogic.gdx.Game;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartMenuScreen;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class ScreenSetter {

    private Game game;

    public ScreenSetter(Game game){
        this.game = game;
    }

    public void createPlayScreen(){
        game.setScreen(new PlayScreen((MiniMiner)game));
    }

    public void createStartMenuScreen(){
        game.setScreen(new StartMenuScreen(game));
    }

    public void createGameOverScreen(){
        game.setScreen(new GameOverScreen(game));
    }


}

