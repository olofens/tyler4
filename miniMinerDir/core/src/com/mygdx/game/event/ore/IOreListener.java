package com.mygdx.game.event.ore;

import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 5/16/2017.
 */

public interface IOreListener {
    public void update(TileTemplate tileTemplate);
    public void update(Ore ore);
}
