package com.mygdx.game.event.ore;

import com.mygdx.game.items.IResource;
import com.mygdx.game.items.TileTemplate;

/**
 * Created by walling on 5/16/2017.
 */

public interface IOreListener {
    public void update(IResource resource);
    public void update(Ore ore);
}
