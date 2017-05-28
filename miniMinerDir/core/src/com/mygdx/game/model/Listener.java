package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olofenstrom on 2017-04-27.
 */

public enum Listener {


    BUS;


    private List<IListener> listeners = new ArrayList<IListener>();

    public void addListener(IListener newListener) {
        listeners.add(newListener);
    }

    public void update(Shout shout) {
        for(IListener listener : listeners){
            listener.update(shout);
        }
    }


}
