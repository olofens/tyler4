package com.mygdx.game.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walling on 5/16/2017.
 */

public enum OreListener {
    ORE;


    private List<IOreListener> listeners = new ArrayList<IOreListener>();

    public void addListener(IOreListener newListener){listeners.add(newListener);}

    public void update(Ore ore){
        for(IOreListener listener : listeners){
            listener.update(ore);
        }
    }
}
