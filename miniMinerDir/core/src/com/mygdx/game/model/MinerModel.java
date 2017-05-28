package com.mygdx.game.model;


import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.event.general.Shout;
import com.mygdx.game.event.general.IListener;
import com.mygdx.game.event.general.Listener;
import com.mygdx.game.event.hud.HudData;
import com.mygdx.game.event.hud.HudUpdater;
import com.mygdx.game.event.messages.MessageData;
import com.mygdx.game.event.messages.MessageListener;
import com.mygdx.game.event.ore.IOreListener;
import com.mygdx.game.event.ore.Ore;
import com.mygdx.game.event.ore.OreListener;
import com.mygdx.game.items.resources.IResource;
import com.mygdx.game.items.resources.Coal;
import com.mygdx.game.items.resources.Diamond;
import com.mygdx.game.items.resources.Gold;
import com.mygdx.game.items.resources.Iron;
import com.mygdx.game.items.resources.Lapiz;
import com.mygdx.game.items.resources.Redstone;

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

    private final int fuelCost = 250;
    private final int hullCost = 250;
    private final int fuelUpgradeCost = 250;
    private final int hullUpgradeCost = 250;

    private float previousSpeed = 0;


    /**
     * Constructor which takes in world in order to create our Miner.
     *
     * @param world
     */
    public MinerModel(World world) {
        this.ft = new FuelTank();
        this.hull = new Hull();
        this.miner = new Miner(world);
        this.inventory = new Inventory();
        Listener.BUS.addListener(this);
        OreListener.ORE.addListener(this);
    }


    public void update() {
        if (minerFell()) hull.adjustHull(previousSpeed);

        ft.adjustFuel((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);
        HudUpdater.BUS.updateHud(new HudData(ft.getFuelString(), hull.getHullString(), inventory.getCashString(), ft.fuelColor()));

        previousSpeed = miner.b2body.getLinearVelocity().y;
    }


    public boolean isAlive() {
        return (!hull.isEmpty() && !ft.isEmpty());
    }

    private boolean minerFell() {
        return miner.b2body.getLinearVelocity().y == 0 && previousSpeed < -10;
    }


    public Miner getMiner() {
        return this.miner;
    }


    @Override
    public void update(IResource resource) {
        if (resource instanceof Coal) {
            inventory.setCoal(inventory.getCoal() + 1);
        } else if (resource instanceof Iron) {
            inventory.setIron(inventory.getIron() + 1);
        } else if (resource instanceof Gold) {
            inventory.setGold(inventory.getGold() + 1);
        } else if (resource instanceof Lapiz) {
            inventory.setLapiz(inventory.getLapiz() + 1);
        } else if (resource instanceof Redstone) {
            inventory.setRedstone(inventory.getRedstone() + 1);
        } else if (resource instanceof Diamond) {
            inventory.setDiamond(inventory.getDiamond() + 1);
        }

    }


    @Override
    public void update(Shout shout) {
        if (shout.getTag() == Shout.Tag.FUELREPAIR) {
            if (inventory.getCash() >= fuelCost) {
                ft.repair();
                inventory.decreaseCash(fuelCost);
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.FUELREPAIR, fuelCost));
            } else {
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.ERROR, 0));
            }

        } else if (shout.getTag() == Shout.Tag.HULLREPAIR) {
            if (inventory.getCash() >= hullCost) {
                hull.repair();
                inventory.decreaseCash(hullCost);
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.HULLREPAIR, hullCost));
            } else {
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.ERROR, 0));
            }

        } else if (shout.getTag() == Shout.Tag.FUELUPGRADE) {
            if (inventory.getCash() >= fuelUpgradeCost) {
                ft.upgrade();
                inventory.decreaseCash(fuelUpgradeCost);
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.FUELUPGRADE, fuelUpgradeCost));
            } else {
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.ERROR, 0));
            }

        } else if (shout.getTag() == Shout.Tag.HULLUPGRADE) {
            if (inventory.getCash() >= hullUpgradeCost) {
                hull.upgrade();
                inventory.decreaseCash(hullUpgradeCost);
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.HULLUPGRADE, hullUpgradeCost));
            } else {
                MessageListener.BUS.updateMessage(new MessageData(MessageData.MessageType.ERROR, 0));
            }
        }
    }

    @Override
    public void update(Ore ore) {
        if (ore.getCommand() == Ore.OreCommand.SELL) {
            inventory.sellInventory();
            System.out.println(inventory.getCash());
        }
    }
}
