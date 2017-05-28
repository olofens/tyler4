package com.mygdx.game.event.ore;

import com.badlogic.gdx.utils.Sort;

/**
 * Created by walling on 5/16/2017.
 */

public class Ore {
    public enum OreCommand{
        SELL
    }
    private final OreCommand command;
    public Ore(OreCommand command){this.command = command;}
    public OreCommand getCommand(){return this.command;}
}
