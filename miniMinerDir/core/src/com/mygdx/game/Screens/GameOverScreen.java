package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;
import com.mygdx.game.Utils.Constants;
import com.mygdx.game.event.Listener;
import com.mygdx.game.event.Shout;

/**
 * Created by erikstrid on 2017-05-16.
 */

public class GameOverScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    Pixmap background;
    private ShapeRenderer shapeRenderer;

    private MiniMiner game1;
    private TextButton playAgainButton;
    private Table table;
    private Game game;

    public GameOverScreen(Game game) {

        this.game = game;
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, ((MiniMiner) game).batch);


        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        //font.font.getData().scale(1);
        Gdx.input.setInputProcessor(stage);

        //table.setFillParent(true);
        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"), new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));


        playAgainButton = new TextButton("Play again", storeSkin);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createPlayScreen();
            }
        });
        table = new Table(storeSkin);
        table.center();
        table.setBounds(10, 80, 190, 290);
        table.add(playAgainButton).width(150).height(100);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(5)));
        stage.addActor(table);




    }

    public void createPlayScreen(){
        game.setScreen(new PlayScreen((MiniMiner)game));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ShapeRenderer shapeRenderer = new ShapeRenderer();

/*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0.1f, 0, 0, 0.5f));
        shapeRenderer.rect(0, 0, Constants.V_WIDTH, Constants.V_HEIGHT);
        shapeRenderer.end();

*/
        stage.act();
        stage.draw();
/*
        shapeRenderer = new ShapeRenderer();


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        shapeRenderer.rect(0, 0, Constants.V_WIDTH, Constants.V_HEIGHT);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        */
    }

    @Override
    public void resize(int width, int height) {

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
