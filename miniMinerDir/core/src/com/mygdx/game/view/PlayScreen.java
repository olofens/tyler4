package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Tools.MinerDrawOptions;
import com.mygdx.game.model.GameModel;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.model.GameWorld;

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

    private ScreenSetter sh;

    private OrthogonalTiledMapRenderer renderer;

    // Hud variables
    private Hud hud;
    private HudView hudView;

    // GameModel variables
    private GameModel gameModel;
    private GameWorld gameWorld;



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

        gameWorld = GameWorld.getInstance();

        state = State.RESUME;
        this.game = game;

        sh = new ScreenSetter(game);

        this.gameModel = new GameModel();

        // Our camera and our viewport, this is where the camera focuses during the game
        gameCam = new OrthographicCamera();
        viewPort = new FitViewport(Constants.V_WIDTH / Constants.PPM,
                Constants.V_HEIGHT / Constants.PPM, gameCam);
        hud = new Hud(game.batch);
        hudView = HudView.getInstance(game.batch);


        renderer = new OrthogonalTiledMapRenderer(gameWorld.getMap(), 1 / Constants.PPM);
        gameCam.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

        //TODO MOVE TO ASSETHANDLER
        minerIMG = new Texture("driller_neutral_right1.png");
        minerSprite = new Sprite(minerIMG);

        minerIMG2 = new Texture("driller_projekt_down1.png");
        minerSpriteDrillDown = new Sprite(minerIMG2);

        minerIMG3 = new Texture("driller_projekt_Rocket1.png");
        minerSpriteRocket = new Sprite(minerIMG3);

        state = State.RESUME;
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

        v2 = hud.hudView.getTouchpadController().handleInput();

        gameModel.update(v2);

        updateCamera(gameCam, gameWorld.getMapPixelWidth(), gameWorld.getMapPixelHeight());

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
        Gdx.input.setInputProcessor(hudView.stage);
        update(dt);

        renderer.render();

        game.batch.begin();
        drawMiner();
        game.batch.end();


        game.batch.setProjectionMatrix(hudView.stage.getCamera().combined);
        hudView.stage.draw();
        hudView.stage3.act();
        hudView.stage3.draw();
        game.batch.setProjectionMatrix(gameCam.combined);

        if (gameModel.gameOver()) {
            hudView.dispose();
            //timer prevents hud sticking on to screen
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    sh.createGameOverScreen();
                }
            }, 0.2f);
        }
    }

    private void renderPause(float dt) {
        checkState();

        hud.getTable2().setVisible(true);
        Gdx.input.setInputProcessor(hudView.stage2);

        hudView.stage2.act();
        hudView.stage2.draw();
        if(hud.isNewScreen()){
            sh.createStartMenuScreen();
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

        width /= Constants.PPM;
        height /= Constants.PPM;

        Vector3 position = cam.position;

        startX += Constants.V_WIDTH / 2 / Constants.PPM;
        startY += Constants.V_HEIGHT / 2 / Constants.PPM;

        height -= Constants.V_HEIGHT / 2 / Constants.PPM;
        width -= Constants.V_WIDTH / 2 / Constants.PPM;


        position.x = gameModel.getMinerModel().getMiner().b2body.getPosition().x;
        position.y = gameModel.getMinerModel().getMiner().b2body.getPosition().y;

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

        cam.position.set(position);
        cam.update();
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
        gameWorld.dispose();
        renderer.dispose();
        hud.hudView.dispose();

    }

}
