package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Utils.Constants;

/**
 * Created by olofenstrom on 2017-05-25.
 */

public class TouchpadView {

    private Touchpad tp;

    public TouchpadView() {
        Skin touchpadSkin = new Skin();
        touchpadSkin.add("touchKnob", new Texture("touchPad/rsz_1touchknob.png"));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();

        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");
        tp = new Touchpad(0, touchpadStyle);

        tp.setBounds(Constants.V_WIDTH - 110, 15, 100, 100);
    }

    public Touchpad getTouchpad() {
        return tp;
    }
}
