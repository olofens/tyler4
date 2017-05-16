package com.mygdx.game.items;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.event.HudData;
import com.mygdx.game.event.HudUpdater;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.event.IListener;
import com.mygdx.game.event.Shout;

import java.awt.*;

/**
 * Created by Omaroueidat on 11/05/17.
 */

/**
 * The class where the logic and calculations of the Miner will occur.
 */
public class MinerModel implements IListener{

    private FuelTank ft;
    private Miner miner;
    private Hull hull;

    private int cash;



    /**
     * Constructor which takes in world in order to create our Miner.
     * @param world
     */
    public MinerModel(World world){
        this.cash = 0;
        this.ft = new FuelTank();
        this.hull = new Hull();
        this.miner = new Miner(world);

    }


    public void update(){

        ft.adjustFuel((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);

        HudUpdater.FUEL.updateHud(new HudData(ft.getFuel(), 0,fuelColor(),ft.getFuelString()));

    }

    private Color fuelColor(){
        if(ft.getFuel() > 60000)
            return Color.GREEN;
        else if (ft.getFuel() < 60000 && ft.getFuel() > 20000)
            return Color.ORANGE;
        else
            return Color.RED;
    }


    public Miner getMiner(){
        return this.miner;
    }


    @Override
    public void update(Shout shout) {
        if(shout.getTag() == Shout.Tag.STORE)
            hull.adjustHull((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);
    }
}
