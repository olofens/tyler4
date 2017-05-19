package com.mygdx.game.event.drill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olof Enström on 2017-05-18.
 */

public enum DrillListener {

    BUS;

    private List<IDrillListener> listeners = new ArrayList<IDrillListener>();

    public void addListener(IDrillListener listener) {
        listeners.add(listener);
    }

    public void update(DrillData drillData) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update(drillData);
        }
    }
}
