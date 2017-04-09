package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by walling on 2017-04-09.
 */

public class ScreenSwitcher {

    private Stack<Screen> screens;

    public ScreenSwitcher(){
        screens = new Stack<Screen>();

    }
    public void push(Screen screen){
        screens.push(screen);
    }
    public void pop(){
        screens.pop();
    }
    public void set(Screen screen){
        screens.pop();
        screens.push(screen);
    }
    public void update(){
        screens.peek().show();
    }
    public void render(float dt){
        screens.peek().render(dt);
    }
}
