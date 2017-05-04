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
import com.mygdx.game.event.Listener;
import com.mygdx.game.items.Miner;
import com.mygdx.game.items.Tile;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by Olof Enström on 2017-04-26.
 */

public class MinerWorldContactListener implements ContactListener {
    Fixture a;
    Fixture b;
    @Override
    public void beginContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();
        if (inContact("miner", "store")) {
            System.out.println("Welcome to the store!");
            Listener.BUS.update();
        }

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
        if (inContact("miner", "store")) {
            Listener.BUS.update();
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
        return ((a.getUserData().equals(stringIDa) && b.getUserData().equals(stringIDb)) ||
                (a.getUserData().equals(stringIDb) && b.getUserData().equals(stringIDa)));
    }

    private void fixtureCheck(String id) {
        Fixture drill; Fixture object;
        if(id.equals("drill")){
            drill  = a.getUserData() == "drill" ? a : b;
            object = drill == a ? b : a;
            if(object.getUserData() != null && Tile.class.isAssignableFrom(object.getUserData().getClass()) && Gdx.input.isKeyPressed(Input.Keys.A)){
                ((Tile) object.getUserData()).onTileHit();
            }
        }
        else if(id.equals("rightWing")){
            drill  = a.getUserData() == "rightWing" ? a : b;
            object = drill == a ? b : a;
            if(object.getUserData() != null && Tile.class.isAssignableFrom(object.getUserData().getClass()) ){
                ((Tile) object.getUserData()).onTileHit();
            }
        }
        else if(id.equals("leftWing")){
            drill  = a.getUserData() == "leftWing" ? a : b;
            object = drill == a ? b : a;
            if(object.getUserData() != null && Tile.class.isAssignableFrom(object.getUserData().getClass()) ){
                ((Tile) object.getUserData()).onTileHit();
            }
        }
    }


}
