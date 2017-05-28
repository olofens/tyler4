package com.mygdx.game.model;


import com.badlogic.gdx.physics.box2d.World;
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
public class MinerModel implements com.mygdx.game.event.general.IListener, com.mygdx.game.event.ore.IOreListener {

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
        com.mygdx.game.event.general.Listener.BUS.addListener(this);
        com.mygdx.game.event.ore.OreListener.ORE.addListener(this);
    }


    public void update() {
        if (minerFell()) hull.adjustHull(previousSpeed);

        ft.adjustFuel((int) miner.b2body.getLinearVelocity().x, (int) miner.b2body.getLinearVelocity().y);
        com.mygdx.game.event.hud.HudUpdater.BUS.updateHud(new com.mygdx.game.event.hud.HudData(ft.getFuelString(), hull.getHullString(), inventory.getCashString(), ft.fuelColor()));

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
    public void update(com.mygdx.game.event.general.Shout shout) {
        if (shout.getTag() == com.mygdx.game.event.general.Shout.Tag.FUELREPAIR) {
            if(inventory.getCash() >= fuelCost) {
                ft.repair();
                inventory.decreaseCash(fuelCost);
                com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.FUELREPAIR, fuelCost));
            }
            else{
                com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.ERROR, 0));
            }

        } else if (shout.getTag() == com.mygdx.game.event.general.Shout.Tag.HULLREPAIR) {
            if(inventory.getCash() >= hullCost) {
                hull.repair();
                inventory.decreaseCash(hullCost);
                com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.HULLREPAIR, hullCost));
            }
            else{
                com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.ERROR, 0));
            }

        } else if (shout.getTag() == com.mygdx.game.event.general.Shout.Tag.FUELUPGRADE) {
                if(inventory.getCash() >= fuelUpgradeCost) {
                    ft.upgrade();
                    inventory.decreaseCash(fuelUpgradeCost);
                    com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.FUELUPGRADE, fuelUpgradeCost));
                }
                else{
                    com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.ERROR, 0));
                }

        } else if (shout.getTag() == com.mygdx.game.event.general.Shout.Tag.HULLUPGRADE) {
                if(inventory.getCash() >= hullUpgradeCost) {
                    hull.upgrade();
                    inventory.decreaseCash(hullUpgradeCost);
                    com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.HULLUPGRADE, hullUpgradeCost));
                }
                else{
                    com.mygdx.game.event.messages.MessageListener.BUS.updateMessage(new com.mygdx.game.event.messages.MessageData(com.mygdx.game.event.messages.MessageData.MessageType.ERROR, 0));
                }
        }
    }

    @Override
    public void update(com.mygdx.game.event.ore.Ore ore) {
        if (ore.getCommand() == com.mygdx.game.event.ore.Ore.OreCommand.SELL) {
            inventory.sellInventory();
            System.out.println(inventory.getCash());
        }
    }
}
