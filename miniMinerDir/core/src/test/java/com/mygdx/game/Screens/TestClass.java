package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by walling on 2017-04-10.
 */
public class TestClass {
    @org.junit.Test
    public void fuelTankTest() throws Exception {


        FuelTank ft =new FuelTank();
        assertTrue(ft.getFuel() == 100000);
        ft.adjustFuel(90,0);
        assertTrue(ft.getFuel() != 100000);


    }
    @Test
    public void mapTest(){
        //TODO
        /*
         TmxMapLoader mapLoader;
         TiledMap map;
         OrthogonalTiledMapRenderer renderer;

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMap.tmx");
        */
    }
    @Test
    public void testUpgrades()throws Exception{
        FuelTank ft = new FuelTank();

        int originalFuel = ft.getFuel();

        assertTrue(originalFuel == 100000);
        ft.upgrade();
        ft.repair();
        assertTrue(ft.getFuel() == (originalFuel + ft.fuelUpgrade));
    }

    @Test
    public void testHullRepair(){
        Hull hull = new Hull();
        float originalDamagfactor = hull.getDamageFactor();

        assertTrue(originalDamagfactor == 3);
        hull.upgrade();
        assertTrue(hull.getDamageFactor() == originalDamagfactor - hull.damageFactorUpgrade);

    }

    @Test
    public void testInventory(){
        Inventory inv = new Inventory();

        inv.setDiamond(2);
        assertTrue(inv.getCash() == 0);
        assertTrue(inv.getDiamond() == 2);
        inv.sellInventory();
        assertTrue(inv.getCash() == (Constants.DIAMOND_VALUE*2));
        assertTrue(inv.getDiamond() == 0);


    }


}