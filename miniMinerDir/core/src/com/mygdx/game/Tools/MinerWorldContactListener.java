package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.event.IListener;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Shout;
import com.mygdx.game.items.Miner;
import com.mygdx.game.items.Tile;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by Olof Enström on 2017-04-26.
 */

public class MinerWorldContactListener implements ContactListener, IListener {
    private Fixture a;
    private Fixture b;
    private boolean minerButtonPressed;

    public MinerWorldContactListener() {
        minerButtonPressed = false;
        Listener.BUS.addListener(this);
    }

    @Override
    public void beginContact(Contact contact) {
        //Sets the two fixtures to whats in contact
        a = contact.getFixtureA();
        b = contact.getFixtureB();
        //Checks the popup for store with the BUS listener
        if (inContact("miner", "store")) {
            System.out.println("Welcome to the store!");
            Listener.BUS.update(new Shout(Shout.Tag.STORE));
        }

        //Fixturechecks for all three sensors
        if(a.getUserData()=="drill"||b.getUserData()=="drill"){
            fixtureCheck("drill");
            }
        if(a.getUserData()=="rightWing"||b.getUserData()=="rightWing"){
            fixtureCheck("rightWing");
        }
        if(a.getUserData()=="leftWing"||b.getUserData()=="leftWing"){
            fixtureCheck("leftWing");
        }

    }

    @Override
    public void endContact(Contact contact) {

        a = contact.getFixtureA();
        b = contact.getFixtureB();
        //Checkout for the store by end of contact (temp?)
        if (inContact("miner", "store")) {
            Listener.BUS.update(new Shout(Shout.Tag.STORE));
            System.out.println("Hope to see you soon!");
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean inContact(String stringIDa, String stringIDb) {
        //Gets the userdata and checks if either a or b matches for the case
        return ((a.getUserData().equals(stringIDa) && b.getUserData().equals(stringIDb)) ||
                (a.getUserData().equals(stringIDb) && b.getUserData().equals(stringIDa)));
    }

    private void fixtureCheck(String id) {
        Fixture minerSensor; Fixture object;
        //Statements sets minerSensor and object to the fixtures in contact,
        //Checks the buttons pressed for drilling and calls the drilling method
        if(id.equals("drill")){
            minerSensor  = a.getUserData() == "drill" ? a : b;
            object = minerSensor == a ? b : a;
            if(object.getUserData() instanceof TileTemplate && (minerButtonPressed ||  Gdx.input.isKeyPressed(Input.Keys.A))){
                ((TileTemplate) object.getUserData()).onDrillHit();
            }
        }
        else if(id.equals("rightWing")){
            minerSensor  = a.getUserData() == "rightWing" ? a : b;
            object = minerSensor == a ? b : a;
            if(object.getUserData() instanceof TileTemplate && (minerButtonPressed ||  Gdx.input.isKeyPressed(Input.Keys.A)) ){
                ((TileTemplate) object.getUserData()).onDrillHit();
            }
        }
        else if(id.equals("leftWing")){
            minerSensor  = a.getUserData() == "leftWing" ? a : b;
            object = minerSensor == a ? b : a;
            if(object.getUserData() instanceof TileTemplate && (minerButtonPressed ||  Gdx.input.isKeyPressed(Input.Keys.A)) ){
                ((TileTemplate) object.getUserData()).onDrillHit();
            }
        }
    }


    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.DRILL) {
            minerButtonPressed = !minerButtonPressed;
        }
    }
}
