package com.mygdx.game.event.ore;

import com.mygdx.game.items.resources.IResource;

/**
 * Created by walling on 5/16/2017.
 */

public interface IOreListener {
    public void update(IResource resource);
    public void update(Ore ore);
}
