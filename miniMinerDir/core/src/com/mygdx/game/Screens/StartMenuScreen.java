package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Tools.ScreenHandler;
import com.mygdx.game.Utils.Constants;

import java.awt.Font;

/**
 * Created by Omaroueidat on 03/04/17.
 */

public class StartMenuScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private Game game;
    private SpriteBatch sb;
    private Texture texture;

    private ScreenHandler sh;

    private OrthographicCamera gameCam;

    private Button startGameButton;
    private Button QuitGameButton;
    private Table table;

    public StartMenuScreen(Game game){

        sb = new SpriteBatch();
        this.game = game;

        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH,
                Constants.V_HEIGHT, gameCam);
        stage = new Stage(viewport, ((MiniMiner) game).batch);

        sh = new ScreenHandler(game);

        Gdx.input.setInputProcessor(stage);


        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));
        texture = new Texture(Gdx.files.internal("minerBackGround.png"));

        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("skins/font-export.fnt"),
                Gdx.files.internal("skins/rusty-robot-ui.png"), false);
        Label.LabelStyle font = new Label.LabelStyle(bitmapFont, Color.WHITE);
        Label miniMinerLabel = new Label("MINIMINER", font);
        miniMinerLabel.scaleBy(0.5f);


        startGameButton = new TextButton("Play", storeSkin);
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        sh.createPlayScreen();
                    }
                }, 0.2f);
            }
        });

        QuitGameButton = new TextButton("Quit Game", storeSkin);
        QuitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //quit
                Gdx.app.exit();
            }
        });

        table = new Table(storeSkin);
        table.center();
        table.setBounds(10, 90, 190, 210);
        table.add(startGameButton);
        table.row();
        table.add(QuitGameButton);

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(3)));
        stage.addActor(table);


    }

    public void update(float dt){

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        sb.begin();
        sb.draw(texture,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
