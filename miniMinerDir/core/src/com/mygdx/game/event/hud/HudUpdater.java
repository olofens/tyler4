package com.mygdx.game.event.hud;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omaroueidat on 15/05/17.
 */
public enum HudUpdater {

    BUS;

    private List<IHudUpdater> hudUpdaters = new ArrayList<IHudUpdater>();

    public void addListener(IHudUpdater newListener) {
        hudUpdaters.add(newListener);
    }

    public void updateHud(HudData data) {

        for (IHudUpdater hudUpdater : hudUpdaters) {
            hudUpdater.update(data);
        }
    }

}
