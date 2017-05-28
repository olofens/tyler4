package com.mygdx.game.ctrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.view.TouchpadView;
import com.mygdx.game.event.drill.DrillData;
import com.mygdx.game.event.drill.DrillListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.general.Shout;

/**
 * Created by erikstrid on 2017-05-10.
 */


public class TouchpadController {


    private Touchpad touchpad;

    private DrillData.DrillDirection prevDirection;

    public TouchpadController() {
        TouchpadView tpv = new TouchpadView();
        touchpad = tpv.getTouchpad();
    }

    private boolean isTouchingUp() {
        return touchpad.getKnobPercentY() > 0.5;
    }

    private boolean isTouchingRight() {
        return touchpad.getKnobPercentX() > 0;
    }

    private boolean isTouchingLeft() {
        return touchpad.getKnobPercentX() < 0;
    }

    private boolean isTouchingDown() {
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
        DrillData.DrillDirection currDirection = getDrillDirection();

        if (prevDirection != currDirection && prevDirection != null) {
            Listener.BUS.update(new Shout(Shout.Tag.NEW_TP_DIR));
        }

        prevDirection = currDirection;
    }
}


