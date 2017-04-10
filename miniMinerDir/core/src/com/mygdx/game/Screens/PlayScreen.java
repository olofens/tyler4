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

    MapProperties prop;

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
        world = new World(new Vector2(0, Miner.GRAVITY), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        this.miner = new Miner(world);



        //adding ground layer
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constants.PPM, (rect.getY() + rect.getHeight() / 2) / Constants.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Constants.PPM, rect.getHeight() / 2 / Constants.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        TiledObjectUtil.parseTiledObject(world, map.getLayers().get("Edges").getObjects());
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

        gameCam.position.x = miner.b2body.getPosition().x;
        gameCam.position.y = miner.b2body.getPosition().y;


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
    }
}
