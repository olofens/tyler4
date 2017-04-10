package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by walling on 2017-04-09.
 */

public class ScreenSwitcher {


    private Stack<Screen> screens;

    /**
     * The screenswitcher class is a way of stacking screens over eachother so we can start the game with a StartMenuScreen
     * and choose to play the game or perhaps edit options in the game.
     */
    public ScreenSwitcher(){
        screens = new Stack<Screen>();

    }

    /**
     * Puts a screen on top of the stack and is what is shown.
     *
     * @param screen
     */
    public void push(Screen screen){
        screens.push(screen);
    }

    /**
     * Removes the screen on top of the stack and resumes to the previous screen that was shown
     */
    public void pop(){
        screens.pop();
    }

    /**
     * Sets our top screen as the main screen.
     * @param screen
     */
    public void set(Screen screen){
        screens.pop();
        screens.push(screen);
    }

    /**
     * Shows the main screen we want to use and interact with.
     */
    public void update(){
        screens.peek().show();
    }
    public void render(float dt){
        screens.peek().render(dt);
    }
}
