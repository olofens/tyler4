package com.mygdx.game.Screens;

import com.mygdx.game.Utils.Constants;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by walling on 2017-04-10.
 */
public class GearTest {


    @org.junit.Test
    public void fuelTankTest() throws Exception {


        com.mygdx.game.model.FuelTank ft =new com.mygdx.game.model.FuelTank();
        assertTrue(ft.getFuel() == 100000);
        ft.adjustFuel(90,0);
        assertTrue(ft.getFuel() != 100000);


    }

    @Test
    public void testUpgrades()throws Exception{
        com.mygdx.game.model.FuelTank ft = new com.mygdx.game.model.FuelTank();

        int originalFuel = ft.getFuel();

        assertTrue(originalFuel == 100000);
        ft.upgrade();
        ft.repair();
        assertTrue(ft.getFuel() == (originalFuel + ft.fuelUpgrade));
    }

    @Test
    public void testHullRepair(){
        com.mygdx.game.model.Hull hull = new com.mygdx.game.model.Hull();
        float originalDamagfactor = hull.getDamageFactor();

        assertTrue(originalDamagfactor == 3);
        hull.upgrade();
        assertTrue(hull.getDamageFactor() == originalDamagfactor - hull.damageFactorUpgrade);

    }

    @Test
    public void testInventory(){
        com.mygdx.game.model.Inventory inv = new com.mygdx.game.model.Inventory();

        inv.setDiamond(2);
        int originalCash = inv.getCash();
        assertTrue(inv.getCash() == originalCash);
        assertTrue(inv.getDiamond() == 2);
        inv.sellInventory();
        assertTrue(inv.getCash() == originalCash+(Constants.DIAMOND_VALUE*2));
        assertTrue(inv.getDiamond() == 0);


    }

    @Test
    public void testUpgradingFuel(){

        com.mygdx.game.model.FuelTank ft = new com.mygdx.game.model.FuelTank();

        assertTrue(ft.getUpgradeLevel() == 0);
        ft.upgrade();
        ft.upgrade();
        ft.upgrade();
        ft.upgrade();
        ft.upgrade();
        assertTrue(ft.getUpgradeLevel() == ft.MAX_UPGRADE_LEVEL);
    }

    @Test
    public void testUpgradingHull(){

        com.mygdx.game.model.Hull hull = new com.mygdx.game.model.Hull();


        assertTrue(hull.getUpgradeLevel() == 0);
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        assertTrue(hull.getUpgradeLevel() == 5);
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        hull.upgrade();
        assertTrue(hull.getUpgradeLevel() == hull.MAX_UPGRADE_LEVEL);

    }

    @Test
    public void testDecreaseCash(){
        com.mygdx.game.model.Inventory inventory = new com.mygdx.game.model.Inventory();

        assertTrue(inventory.getCash() == 1000);
        inventory.decreaseCash(200);
        assertTrue(inventory.getCash() == 800);

    }



}