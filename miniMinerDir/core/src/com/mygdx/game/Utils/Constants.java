package com.mygdx.game.Utils;

/**
 * Created by Olof Enström on 2017-04-10.
 */

public final class Constants {

    /**
     * The scaling constant for our game to scale properly.
     */
    public static final float PPM = 32;
    public static final int GRAVITY = -10;
    public static final int V_WIDTH = 240;
    public static final int V_HEIGHT = 400;
    //Constants for categorybits in binary
    public static final short DEFAULT_BIT = 1;
    public static final short MINER_BIT = 2;
    public static final short TILE_BIT = 4;
    public static final short STONE_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short COAL_BIT = 32;
    public static final short IRON_BIT = 64;
    public static final short REDSTONE_BIT = 128;
    public static final short LAPIZ_BIT = 256;
    public static final short GOLD_BIT = 512;
    public static final short DIAMOND_BIT = 1024;

    //Value of all minerals
    public static final int COAL_VALUE = 20;
    public static final int IRON_VALUE =50;
    public static final int GOLD_VALUE =120;
    public static final int LAPIZ_VALUE =250;
    public static final int REDSTONE_VALUE =350;
    public static final int DIAMOND_VALUE = 520;


}
