package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

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
        }
        if (inContact("drill", "tile")){
            System.out.println("Initiate drill contact");
        }
    }

    @Override
    public void endContact(Contact contact) {
        a = contact.getFixtureA();
        b = contact.getFixtureB();
        if (inContact("miner", "store")) {
            System.out.println("Hope to see you soon!");
        }
        if (inContact("drill", "tile")){
            System.out.println("End drill contact");
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
}
