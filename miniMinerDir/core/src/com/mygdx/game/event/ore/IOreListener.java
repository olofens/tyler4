package com.mygdx.game.event.ore;

import com.mygdx.game.items.resources.IResource;

/**
 * Created by walling on 5/16/2017.
 */

public interface IOreListener {
    void update(IResource resource);
    void update(Ore ore);
}
