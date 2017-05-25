package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Tools.MinerDrawOptions;
import com.mygdx.game.items.GameModel;
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
    // TODO FIX LISTENER
    private Texture minerIMG;
    private Texture minerIMG2;
    private Texture minerIMG3;
    private Sprite minerSprite;
    private Sprite minerSpriteDrillDown;
    private Sprite minerSpriteRocket;

    private OrthogonalTiledMapRenderer renderer;

    // Hud variables
    private Hud hud;

    // GameModel variables
    private GameModel gameModel;

    Vector2 v2;

    public enum State {
        PAUSE,
        RESUME
    }

    private State state;

    /**
     * The main playscreen where everything is painted and shown
     *
     * @param game Brings in MiniMiner variable in order to get Width and Height of desired screen.
     */
    public PlayScreen(MiniMiner game) {

        state = State.RESUME;
        this.game = game;
        this.gameModel = new GameModel();

        // Our camera and our viewport, this is where the camera focuses during the game
        gameCam = new OrthographicCamera();
        viewPort = new FitViewport(Constants.V_WIDTH / Constants.PPM,
                Constants.V_HEIGHT / Constants.PPM, gameCam);
        hud = new Hud(game.batch);

        renderer = new OrthogonalTiledMapRenderer(gameModel.getMap(), 1 / Constants.PPM);
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

        minerIMG = new Texture("driller_neutral_right1.png");
        minerSprite = new Sprite(minerIMG);

        minerIMG2 = new Texture("driller_projekt_down1.png");
        minerSpriteDrillDown = new Sprite(minerIMG2);

        minerIMG3 = new Texture("driller_projekt_Rocket1.png");
        minerSpriteRocket = new Sprite(minerIMG3);
    }


    @Override
    public void show() {

    }

    private void checkState() {
        if (hud.isPaused()) {
            this.state = State.PAUSE;
        } else {
            this.state = State.RESUME;
        }
    }

    /**
     * @param dt
     */
    public void update(float dt) {
        checkState();

        //The Vector that our Touchpadhandler creates
        v2 = hud.tpHandler.handleInput();

        gameModel.update(v2);

        updateCamera(gameCam, getMapPixelWidth(), getMapPixelHeight());

        renderer.setView(gameCam);
    }


    @Override
    public void render(float dt) {

        if (state.equals(State.RESUME)) {
            renderResume(dt);
        } else {
            renderPause(dt);
        }
    }

    public void renderResume(float dt) {
        Gdx.input.setInputProcessor(hud.stage);
        update(dt);

        //render our game map
        renderer.render();

        //render miner
        game.batch.begin();
        drawMiner();
        game.batch.end();

        //Render b2dr lines
        //gameModel.getB2dr().render(gameModel.getWorld(), gameCam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(gameCam.combined);

        if (gameModel.gameOver()) {
            hud.dispose();
            //timer prevents hud sticking on to screen
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new GameOverScreen(game));
                }
            }, 0.2f);
        }
    }

    private void renderPause(float dt) {
        //update(dt);
        checkState();
        hud.table2.setVisible(true);
        Gdx.input.setInputProcessor(hud.stage2);

        hud.stage2.act();
        hud.stage2.draw();
        if(hud.isNewScreen()){
            game.setScreen(new StartMenuScreen(game));
            hud.setIsNewScreen(false);
        }
    }


    private void drawMiner() {

        Sprite mySprite;
        MinerDrawOptions mdo = gameModel.decideDirection();

        if (mdo.getStrSprite().equals("minerSpriteRocket")) {
            mySprite = minerSpriteRocket;
        } else if (mdo.getStrSprite().equals("minerSpriteDrillDown")) {
            mySprite = minerSpriteDrillDown;
        } else {
            mySprite = minerSprite;
        }

        game.batch.draw(mySprite, mdo.getX(), mdo.getY(),
                mdo.getX1(), mdo.getY1());
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
        position.x = gameModel.getMinerModel().getMiner().b2body.getPosition().x;
        position.y = gameModel.getMinerModel().getMiner().b2body.getPosition().y;

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
        int mapWidth = gameModel.getProp().get("width", Integer.class);
        int tilePixelWidth = gameModel.getProp().get("tilewidth", Integer.class);
        return mapWidth * tilePixelWidth;
    }

    private int getMapPixelHeight() {
        int tilePixelHeight = gameModel.getProp().get("tileheight", Integer.class);
        int mapHeight = gameModel.getProp().get("height", Integer.class);
        return mapHeight * tilePixelHeight;
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    @Override
    public void pause() {
        this.state = State.PAUSE;
    }

    @Override
    public void resume() {
        this.state = State.RESUME;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameModel.dispose();
        renderer.dispose();
        hud.dispose();

    }

}
