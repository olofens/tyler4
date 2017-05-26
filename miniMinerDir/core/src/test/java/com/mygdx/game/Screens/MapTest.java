package com.mygdx.game.Screens;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.items.GameWorld;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;

/**
 * Created by erikstrid on 2017-05-26.
 */

public class MapTest {

    Object o;

    @Test
    public void testInit() throws ClassNotFoundException,
    InstantiationException, IllegalAccessException,
    IllegalArgumentException, InvocationTargetException {

        Class clazz = Class.forName("GameWorld");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor=null;
        System.out.println(constructors[0].getName());

        constructor=constructors[0];
        constructor.setAccessible(true);
        o = constructor.newInstance();
        ((GameWorld) o).getMap();


    }

    @Test
    public void mapCreationTest() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException{


        testInit();

        assertTrue(((GameWorld) o).getMap() != null);



    }
}
