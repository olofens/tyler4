package com.mygdx.game.items;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Utils.Constants;


public abstract class Edge {

    /**
     * Oklart, någon får förklara denna.
     * @param world
     *      World used to create the body in the game world.
     * @param objects
     *      The objects, in this case the edges of the map which are connected lines (in the shape
     *      of a rectangle).
     */
    public static void parseTiledObject(World world, MapObjects objects) {
        //Explanation: this method is used to create a mathematical line, a polyline (which
        //in essence is just connected lines). In this situation the lines connect to create a
        //rectangle.

        //in the for-loop, the lines are gotten from the Map and added to a mathematical line.
        for(MapObject object : objects) {
            Shape shape;
            if (object instanceof PolylineMapObject) {
                //here the lines are added to the polyline
                shape = createPolyline((PolylineMapObject) object);
            } else {
                continue;
            }

            //once the ChainShape is fully created, the body is created in the world
            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f).setFriction(0);
            body.getFixtureList().get(0).setUserData("edge");
            shape.dispose();
        }
    }


    //Method that creates a chain of lines.
    private static ChainShape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / Constants.PPM,
                                            vertices[i * 2 + 1] / Constants.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }
}
