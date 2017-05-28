package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.event.drill.DrillData;
import com.mygdx.game.event.drill.DrillListener;
import com.mygdx.game.event.drill.IDrillListener;
import com.mygdx.game.event.general.IListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.ore.Ore;
import com.mygdx.game.event.ore.OreListener;
import com.mygdx.game.event.general.Shout;
import com.mygdx.game.items.resources.IResource;

/**
 * Created by Olof Enström on 2017-04-26.
 */

public class MinerWorldContactListener implements ContactListener, IListener, IDrillListener {
    private Fixture a;
    private Fixture b;
    private boolean minerButtonPressed;
    private DrillData.DrillDirection lastDirection;

    private Fixture rightTile;
    private Fixture bottomTile;
    private Fixture leftTile;

    private Fixture prevBottomTile;

    private Fixture miner;

    public MinerWorldContactListener() {
        minerButtonPressed = false;
        Listener.BUS.addListener(this);
        DrillListener.BUS.addListener(this);
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
        } else if (inContact("miner", "storeUpgrade")) {
            System.out.println("Welcome to upgrades!");
            Listener.BUS.update(new Shout(Shout.Tag.STORE_UPGRADE));
        }

        //NEW
        if(a.getUserData()=="drill"||b.getUserData()=="drill") {
            setBottomTile(fixtureCheckObject("drill"));
            miner = fixtureCheckSensor("drill");
        } else if (a.getUserData() == "rightWing" || b.getUserData() == "rightWing") {
            rightTile = fixtureCheckObject("rightWing");
            miner = fixtureCheckSensor("rightWing");
        } else if (a.getUserData() == "leftWing" || b.getUserData() == "leftWing") {
            leftTile = fixtureCheckObject("leftWing");
            miner = fixtureCheckSensor("leftWing");
        }

        if (minerButtonPressed || Gdx.input.isKeyPressed(Input.Keys.A)) {
            drill(lastDirection);
        }
/*
        //OLD
        //Fixturechecks for all three sensors, object sent to resolve contact, which follows upp with drilling etc
        if(a.getUserData()=="drill"||b.getUserData()=="drill"){
            resolveContact(fixtureCheckObject("drill"));
        }
        else if (a.getUserData() == "rightWing" || b.getUserData() == "rightWing") {
            resolveContact(fixtureCheckObject("rightWing"));
        }
        else if (a.getUserData() == "leftWing" || b.getUserData() == "leftWing") {
            resolveContact(fixtureCheckObject("leftWing"));
        }*/
    }

    @Override
    public void endContact(Contact contact) {

        a = contact.getFixtureA();
        b = contact.getFixtureB();
        //Checkout for the store by end of contact (temp?)
        if (inContact("miner", "store")) {
            Listener.BUS.update(new Shout(Shout.Tag.STORE));
            System.out.println("Hope to see you soon!");
        } else if (inContact("miner", "storeUpgrade")) {
            Listener.BUS.update(new Shout(Shout.Tag.STORE_UPGRADE));
        }

        if(a.getUserData()=="drill"||b.getUserData()=="drill") {
            nullBottomTile();
        } else if (a.getUserData() == "rightWing" || b.getUserData() == "rightWing") {
            rightTile = null;
            System.out.println("nulled righttile");
        } else if (a.getUserData() == "leftWing" || b.getUserData() == "leftWing") {
            leftTile = null;
            System.out.println("nulled lefttile");
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void setBottomTile(Fixture newBottomTile) {
        prevBottomTile = bottomTile;
        bottomTile = newBottomTile;
        System.out.println("Set bottom tile");
    }

    private void nullBottomTile() {
        if (prevBottomTile == null) {
            bottomTile = null;
            System.out.println("nulled current tile");
        } else {
            prevBottomTile = null;
            System.out.println("nulled prev tile");
        }
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
        if(object.getUserData() instanceof IResource && minerButtonPressed){
            ((IResource) object.getUserData()).onDrillHit();
            OreListener.ORE.update((IResource)object.getUserData());
        }
    }

    private void drill(DrillData.DrillDirection direction) {
        if (direction == DrillData.DrillDirection.RIGHT && rightTile != null) {
            resolveContact(rightTile);
            rightTile = null;
        } else if (direction == DrillData.DrillDirection.LEFT && leftTile != null) {
            resolveContact(leftTile);
            leftTile = null;
        } else if (direction == DrillData.DrillDirection.DOWN && bottomTile != null) {
            resolveContact(bottomTile);
            bottomTile = null;
            System.out.println("ran drilldown");
        }
    }


    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.DRILL) {
            //minerButtonPressed = !minerButtonPressed;
        }
    }

    @Override
    public void update(DrillData drillData) {
        if (!drillData.getNewDirection()) {
            lastDirection = drillData.getDrillDirection();
            minerButtonPressed = !minerButtonPressed;
            if (minerButtonPressed || Gdx.input.isKeyPressed(Input.Keys.A)) {
                drill(drillData.getDrillDirection());
            } else {
                lastDirection = null;
            }
        } else {
          if (minerButtonPressed) {
              //drill(drillData.getDrillDirection());
              lastDirection = drillData.getDrillDirection();
          }
        }

    }
}
