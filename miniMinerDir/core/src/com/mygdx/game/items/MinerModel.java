package com.mygdx.game.items;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.event.HudData;
import com.mygdx.game.event.HudUpdater;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.event.IListener;
import com.mygdx.game.event.IOreListener;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Ore;
import com.mygdx.game.event.OreListener;
import com.mygdx.game.event.Shout;
import com.mygdx.game.items.resources.Coal;
import com.mygdx.game.items.resources.Diamond;
import com.mygdx.game.items.resources.Gold;
import com.mygdx.game.items.resources.Iron;
import com.mygdx.game.items.resources.Lapiz;
import com.mygdx.game.items.resources.Redstone;

import java.awt.*;

/**
 * Created by Omaroueidat on 11/05/17.
 */

/**
 * The class where the logic and calculations of the Miner will occur.
 */
public class MinerModel implements IListener, IOreListener {

    private FuelTank ft;
    private Miner miner;
    private Hull hull;
    private Inventory inventory;

    private int cash;


    /**
     * Constructor which takes in world in order to create our Miner.
     *
     * @param world
     */
    public MinerModel(World world) {
        this.cash = 0;
        this.ft = new FuelTank();
        this.hull = new Hull();
        this.miner = new Miner(world);
        this.inventory = new Inventory();
        Listener.BUS.addListener(this);
        OreListener.ORE.addListener(this);
    }

    /**
     * Getter for our miner
     *
     * @return tnis.miner
     */

    public void update() {
        ft.adjustFuel((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);
        HudUpdater.BUS.updateHud(new HudData(ft.getFuelString(), hull.getHullString(), fuelColor()));

    }


    public boolean isAlive(){
        if(ft.isEmpty()){
            return false;
        }
        if (ft.isEmpty()){
            return false;
        }
        return true;
    }





    private Color fuelColor(){
        if(ft.getFuel() > 60000)
            return Color.GREEN;
        else if (ft.getFuel() < 60000 && ft.getFuel() > 20000)
            return Color.ORANGE;
        else
            return Color.RED;
    }

    public Miner getMiner() {
        return this.miner;
    }

    public FuelTank getFuelTank() {
        return ft;
    }

    public Hull getHull() {
        return hull;
    }

    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.FUELREPAIR) ft.repair();
        else if (shout.getTag() == Shout.Tag.HULLREPAIR) {
            hull.repair();
        }
        else if (shout.getTag() == Shout.Tag.HULLDAMAGE) hull.adjustHull(
                (int) miner.b2body.getLinearVelocity().x,
                (int) miner.b2body.getLinearVelocity().y);

    }

    @Override
    public void update(TileTemplate tileTemplate) {
        if(tileTemplate instanceof Coal){
            inventory.setCoal(inventory.getCoal()+1);
        }
        else if(tileTemplate instanceof Iron){
            inventory.setIron(inventory.getIron()+1);
        }
        else if(tileTemplate instanceof Gold){
            inventory.setGold(inventory.getGold()+1);
        }
        else if(tileTemplate instanceof Lapiz){
            inventory.setLapiz(inventory.getLapiz()+1);
        }
        else if(tileTemplate instanceof Redstone){
            inventory.setRedstone(inventory.getRedstone()+1);
        }
        else if(tileTemplate instanceof Diamond){
            inventory.setDiamond(inventory.getDiamond()+1);
        }

    }

    @Override
    public void update(Ore ore) {
        if(ore.getSort()==Ore.OreSort.SELL){
            inventory.sellInventory();
            System.out.println(inventory.getCash());
        }
    }
}
