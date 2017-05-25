package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.drill.DrillData;
import com.mygdx.game.event.drill.DrillListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;
import com.mygdx.game.items.GameModel;
import com.mygdx.game.items.Miner;

/**
 * Created by erikstrid on 2017-05-10.
 */


public class TouchpadHandler {

    
    private Touchpad touchpad;

    private DrillData.DrillDirection prevDirection;
    private DrillData.DrillDirection currDirection;

    public TouchpadHandler() {

        Skin touchpadSkin = new Skin();
        touchpadSkin.add("touchKnob", new Texture("touchPad/rsz_1touchknob.png"));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        Drawable touchKnob = touchpadSkin.getDrawable("touchKnob");

        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(0, touchpadStyle);

        touchpad.setBounds(Constants.V_WIDTH - 110, 15, 100, 100);
    }

    public boolean isTouchingUp() {
        return touchpad.getKnobPercentY() > 0.5;
    }

    public boolean isTouchingRight() {
        return touchpad.getKnobPercentX() > 0;
    }

    public boolean isTouchingLeft() {
        return touchpad.getKnobPercentX() < 0;
    }

    public boolean isTouchingDown() {
        return touchpad.getKnobPercentY() < -0.4;
    }

    public DrillData.DrillDirection getDrillDirection() {
        if (isTouchingLeft() && !isTouchingUp() && !isTouchingDown()) return DrillData.DrillDirection.LEFT;
        else if (isTouchingRight() && !isTouchingUp() && !isTouchingDown()) return DrillData.DrillDirection.RIGHT;
        else return DrillData.DrillDirection.DOWN;
    }

    public Vector2 handleInput() {

        checkDirection();

        //POOR SOLUTION FOR DESKTOP TESTING
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            DrillListener.BUS.update(new DrillData(getDrillDirection(), false));
        }

         Vector2 vector2 = new Vector2(0,0);
        if (touchpad.getKnobPercentY() != 0) {
            vector2.y = touchpad.getKnobPercentY();
        }

        if (touchpad.getKnobPercentX() != 0) {
            vector2.x = touchpad.getKnobPercentX();
        }
        return vector2;
    }

    public Touchpad getTouchpad(){
        return touchpad;
    }

    private void checkDirection() {
        currDirection = getDrillDirection();

        if (prevDirection != currDirection && prevDirection != null) {
            Listener.BUS.update(new Shout(Shout.Tag.NEW_TP_DIR));
        }

        prevDirection = currDirection;
    }

}


