package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.DrillButtonView;
import com.mygdx.game.Tools.TouchpadController;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.GameModel;
import com.mygdx.game.items.GameWorld;
import com.mygdx.game.items.Miner;
import com.mygdx.game.items.MinerModel;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Tools.Box2DWorldCreator;
import com.mygdx.game.Tools.MinerWorldContactListener;
import com.mygdx.game.Utils.Constants;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class MinerTest extends GameTest {

    @Test
    public void MinerCreationTest(){


        MinerModel minerModel = new MinerModel(new World(new Vector2(0,0),false));

        assertTrue(minerModel.isAlive());


    }
}
