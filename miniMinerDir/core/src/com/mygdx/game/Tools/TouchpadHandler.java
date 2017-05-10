package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-10.
 */


public class TouchpadHandler {

    //TODO fix public
    public Touchpad touchpad;

    public TouchpadHandler(){
        //Touchpad
        //Create a touchpad skin
        Skin touchpadSkin = new Skin();
        //Set background image
        //touchpadSkin.add("touchBackground", new Texture("touchPad/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("touchPad/rsz_1touchknob.png"));
        //Create TouchPad Style
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        //Drawable touchBackground = touchpadSkin.getDrawable("touchBackground");
        Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        //touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)

        touchpad.setBounds(Constants.V_WIDTH - 110, 15, 100, 100);
    }




}
