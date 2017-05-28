package com.mygdx.game.event.ore;

import com.mygdx.game.items.resources.IResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walling on 5/16/2017.
 */

public enum OreListener {

    ORE;


    private List<IOreListener> listeners = new ArrayList<IOreListener>();

    public void addListener(IOreListener newListener){listeners.add(newListener);}

    public void update(IResource resource){
        for(IOreListener listener : listeners){
            listener.update(resource);
        }
    }
    public void update(Ore ore){
        for(IOreListener listener : listeners){
            listener.update(ore);
        }
    }
}
