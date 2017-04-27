package com.mygdx.game.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olofenstrom on 2017-04-27.
 */

public class Listener {
    private List<IListener> listeners = new ArrayList<IListener>();

    public void addListener(IListener newListener) {
        listeners.add(newListener);
    }

    public void update() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update();
        }
    }
}
