package com.mygdx.game.event;

import com.badlogic.gdx.utils.Sort;

/**
 * Created by walling on 5/16/2017.
 */

public class Ore {
    public enum OreSort{
        COAL,
        IRON,
        GOLD,
        LAPIZ,
        REDSTONE,
        DIAMOND;
    }
    private final OreSort sort;
    public Ore(OreSort sort){this.sort = sort;}
    public OreSort getSort(){return this.sort;}
}
