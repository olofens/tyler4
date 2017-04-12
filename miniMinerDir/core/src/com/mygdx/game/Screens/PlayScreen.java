package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.Box2DWorldCreator;
import com.mygdx.game.Utils.TiledObjectUtil;
import com.mygdx.game.items.Miner;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class PlayScreen implements Screen {

    // Game Variables
    private MiniMiner game;
    private OrthographicCamera gameCam;
    private Viewport viewPort;


    // Miner variables
    private Miner miner;

    // Tiledmap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Hud variables
    private Hud hud;

    // Box2D variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private MapProperties prop;

    private float startX;
    private float startY;


    /**
     * The main playscreen where the game is actually interacted with and controlled
     *
     * @param game
     */
    public PlayScreen(MiniMiner game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewPort = new FitViewport(MiniMiner.V_WIDTH / Constants.PPM,
                                    MiniMiner.V_HEIGHT / Constants.PPM, gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PPM);
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

        prop = map.getProperties();

        // Create a new world with 0 gravity for now
        world = new World(new Vector2(0, Constants.GRAVITY), true);
        b2dr = new Box2DDebugRenderer();

        new Box2DWorldCreator(world,map);
        this.miner = new Miner(world);

        TiledObjectUtil.parseTiledObject(world, map.getLayers().get("Edges").getObjects());

        //startX = gameCam.viewportWidth / 2;
        //startY = gameCam.viewportHeight / 2;
        System.out.println("Startx = " + startX);
        System.out.println("Starty = " + startY);
        System.out.println("pixelwidth: " + getMapPixelWidth());
        System.out.println("pixelheight: " + getMapPixelHeight());
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        if(hud.touchpad.getKnobPercentY() > 0){
            miner.b2body.applyForceToCenter(0, 15f*hud.touchpad.getKnobPercentY(), true);
        }

        if(hud.touchpad.getKnobPercentX() != 0){
            miner.b2body.applyLinearImpulse(new Vector2(0.05f*hud.touchpad.getKnobPercentX(),0),
                    miner.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        moveCamera(gameCam, getMapPixelWidth(), getMapPixelHeight());
        gameCam.update();

        renderer.setView(gameCam);
    }

    @Override
    public void render(float dt) {
        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //render box2d lines
        b2dr.render(world, gameCam.combined);

        //hud.blockSprite.setX(hud.blockSprite.getX() + hud.touchpad.getKnobPercentX() * hud.blockSpeed);
        //System.out.print(hud.blockSprite.getX());
        //hud.blockSprite.setY(hud.blockSprite.getY() + hud.touchpad.getKnobPercentY() * hud.blockSpeed);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gameCam.combined);
        //game.batch.begin();
        //game.batch.draw(texture, 0, 0);
        //game.batch.end();

        /*
        * Draw our miner
        */


    }

    private void moveCamera(OrthographicCamera cam, float width, float height) {

        float startX = 0;
        float startY = 0;
        //Divide by PPM since width and height are measurements in pixels and not tiles...
        //... and the camera's position is currently set in tiles. PPM is set to the side
        //of a tile (32px)
        width /= Constants.PPM;
        height /= Constants.PPM;

        //instantiate a position
        Vector3 position = cam.position;

        //mathematical understanding: startX is the position of the camera, which in turn is the
        //position of the player. The player is in the middle of the screen at all times.
        //to get the leftmost bit of the gameCam's view, we get HALF the width of the screen and
        //add it to startX. Same with startY
        startX += MiniMiner.V_WIDTH / 2 / Constants.PPM;
        startY += MiniMiner.V_HEIGHT / 2 / Constants.PPM;

        //same here but we subtract the width and height
        height -= MiniMiner.V_HEIGHT / 2 / Constants.PPM;
        width -= MiniMiner.V_WIDTH / 2 / Constants.PPM;

        //self-explanatory
        position.x = miner.b2body.getPosition().x;
        position.y = miner.b2body.getPosition().y;

        //if the position of the camera's left most bit is outside of the map, reset the cameras
        //position to the left most bit of the map
        if (position.x < startX) {
            position.x = startX;
        }

        if (position.y < startY) {
            position.y = startY;
        }

        if (position.x > width) {
            position.x = width;
        }

        if (position.y > height) {
            position.y = height;
        }

        //set the new camera position
        cam.position.set(position);
    }

    private int getMapPixelWidth() {
        int mapWidth = prop.get("width", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        return mapWidth * tilePixelWidth;

    }

    private int getMapPixelHeight() {
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        return mapHeight * tilePixelHeight;
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
