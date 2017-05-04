package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.Box2DWorldCreator;
import com.mygdx.game.Tools.MinerWorldContactListener;
import com.mygdx.game.Tools.SoundHandler;
import com.mygdx.game.event.Listener;
import com.mygdx.game.items.FuelTank;
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
    private Texture minerIMG;
    private Texture minerIMG2;
    private Texture minerIMG3;
    private Sprite minerSprite;
    private Sprite minerSpriteDrillDown;
    private Sprite minerSpriteRocket;
    private Vector2 minerPos;
    private boolean isFacingRight;

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

    private FuelTank ft;
    /**
     * The main playscreen where the game is actually interacted with and controlled
     *
     * @param game
     *      Brings in MiniMiner variable in order to get Width and Height of desired screen.
     */
    public PlayScreen(MiniMiner game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        viewPort = new FitViewport(Constants.V_WIDTH / Constants.PPM,
                                    Constants.V_HEIGHT / Constants.PPM, gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MiniMinerMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PPM);
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

        prop = map.getProperties();

        // Create a new world
        world = new World(new Vector2(0, Constants.GRAVITY), true);
        b2dr = new Box2DDebugRenderer();

        new Box2DWorldCreator(world,map);
        this.miner = new Miner(world);

        world.setContactListener(new MinerWorldContactListener());

        isFacingRight = true;


        minerIMG = new Texture("driller_neutral_right.png");
        minerSprite = new Sprite(minerIMG);

        minerIMG2 = new Texture("driller_drill_down.png");
        minerSpriteDrillDown = new Sprite(minerIMG2);

        minerIMG3 = new Texture("driller_projekt_RocketTest.png");
        minerSpriteRocket = new Sprite(minerIMG3);

        ft = new FuelTank();


    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

        //if body has contact with ground & touchpadY < -0.5
        //THEN drill
        //if().......
        float knobPercentX = 0;
        float knobPercentY = 0;


        if(hud.touchpad.getKnobPercentY() > 0){
            miner.b2body.applyForceToCenter(0, 18f*hud.touchpad.getKnobPercentY(), true);
            knobPercentY = hud.touchpad.getKnobPercentY()*10;
            ft.adjustFuel((int)knobPercentY);
        }

        if(hud.touchpad.getKnobPercentX() != 0){
            miner.b2body.setLinearVelocity(new Vector2(5f*hud.touchpad.getKnobPercentX(),miner.b2body.getLinearVelocity().y));
            knobPercentX = hud.touchpad.getKnobPercentX()*10;
            ft.adjustFuel((int)knobPercentX);

        }

        else{
        }


    }



    public void update(float dt) {
        handleInput(dt);

        minerPos = miner.b2body.getPosition();

        world.step(1 / 60f, 6, 2);
        updateCamera(gameCam, getMapPixelWidth(), getMapPixelHeight());
        renderer.setView(gameCam);
    }

    public boolean drawUp(){
        if(hud.isTouchingUp()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean drawDown(){

        if(hud.isTouchingDown()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean drawRight(){
        //Check touchpad
        if(hud.isTouchingRight()){
            //RIGHT
            return true;
        }
        else if(hud.isTouchingLeft()){
            //LEFT
            return false;
        }
        //Check velocity
        else if(miner.b2body.getLinearVelocity().x > 0){
            //RIGHT
            isFacingRight = true;
            return true;
        }
        else if(miner.b2body.getLinearVelocity().x < 0){
            //LEFT
            isFacingRight = false;
            return false;
        }
        //Check last direction
        else if(isFacingRight){
            //RIGHT
            return true;
        }
        /*else if(isFacingRight){
            //LEFT
            return false;
        }*/
        else{
            return false;
        }
    }


    @Override
    public void render(float dt) {
        update(dt);

        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //render miner
        game.batch.begin();

        if(hud.isTouchingUp()){
            //Draw UP
            if(drawRight()){
                game.batch.draw(minerSpriteRocket, minerPos.x - 10 / Constants.PPM, minerPos.y - 27 / Constants.PPM,
                        12 / Constants.PPM, 15 / Constants.PPM);
            }
            else{
                game.batch.draw(minerSpriteRocket, minerPos.x - 1 / Constants.PPM, minerPos.y - 27 / Constants.PPM,
                        12 / Constants.PPM, 15 / Constants.PPM);
            }

        }

        if(hud.isTouchingDown()){
            //Draw DOWN
            game.batch.draw(minerSpriteDrillDown, minerPos.x - 12 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
                    25 / Constants.PPM, 28 / Constants.PPM);
        }
        else if(drawRight()){
            //Draw RIGHT
            game.batch.draw(minerSprite, minerPos.x - 15 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
                    35 / Constants.PPM, 25 / Constants.PPM);
        }
        else{
            //Draw LEFT
            game.batch.draw(minerSprite, minerPos.x + 15 / Constants.PPM, minerPos.y - 15 / Constants.PPM,
                    - 35 / Constants.PPM, 25 / Constants.PPM);
        }

        game.batch.end();


        //render box2d lines
        //b2dr.render(world, gameCam.combined);


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gameCam.combined);


    }

    private void updateCamera(OrthographicCamera cam, float width, float height) {

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
        startX += Constants.V_WIDTH / 2 / Constants.PPM;
        startY += Constants.V_HEIGHT / 2 / Constants.PPM;

        //same here but we subtract the width and height
        height -= Constants.V_HEIGHT / 2 / Constants.PPM;
        width -= Constants.V_WIDTH / 2 / Constants.PPM;

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
        cam.update();
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
