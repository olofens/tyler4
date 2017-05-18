package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.event.general.IListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.ore.Ore;
import com.mygdx.game.event.ore.OreListener;
import com.mygdx.game.event.general.Shout;
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
            //Calling Ore-bus to make minerModel sell minerals
            OreListener.ORE.update(new Ore(Ore.OreSort.SELL));
        }

        //Fixturechecks for all three sensors, object sent to resolve contact, which follows upp with drilling etc
        if(a.getUserData()=="drill"||b.getUserData()=="drill"){
            resolveContact(fixtureCheckObject("drill"));
        }
        else if (a.getUserData() == "rightWing" || b.getUserData() == "rightWing") {
            resolveContact(fixtureCheckObject("rightWing"));
        }
        else if (a.getUserData() == "leftWing" || b.getUserData() == "leftWing") {
            resolveContact(fixtureCheckObject("leftWing"));
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

    private Fixture fixtureCheckObject(String id) {
        Fixture minerSensor; Fixture object;
        //Statements sets minerSensor and object to the fixtures in contact,
        //returns object
        if(id != null){
            minerSensor  = a.getUserData() == id ? a : b;
            object = minerSensor == a ? b : a;
            return object;
        }
        return null;
    }
    private Fixture fixtureCheckSensor(String id) {
        Fixture minerSensor;
        //Statements sets minerSensor to the fixture in contact,
        //returns sensor
        if(id != null){
            minerSensor  = a.getUserData() == id ? a : b;
            return minerSensor;
        }
        return null;
    }
    private void resolveContact(Fixture object){
        //Checks if object isnt null and if specific diggingbuttons are pressed.
        //Calls object for drilling and updates inventory via Ore-bus
        if(object.getUserData() instanceof TileTemplate && (minerButtonPressed ||  Gdx.input.isKeyPressed(Input.Keys.A)) ){
            ((TileTemplate) object.getUserData()).onDrillHit();
            OreListener.ORE.update((TileTemplate)object.getUserData());
        }
    }


    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.DRILL) {
            minerButtonPressed = !minerButtonPressed;
        }
    }
}
