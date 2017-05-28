package com.mygdx.game.Screens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.items.resources.Coal;
import com.mygdx.game.items.resources.Resource;
import com.mygdx.game.model.GameWorld;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by walling on 2017-05-27.
 */

public class ResourceTest extends GameTest {


    @Test
    public void drillTest() throws Exception {
        GameWorld gameWorld = GameWorld.getInstance();
        Coal coal = new Coal(new World(new Vector2(0,0), false), gameWorld.getMap(), new Rectangle());

        coal.onDrillHit();


        assertTrue(coal != null);
    }


}
