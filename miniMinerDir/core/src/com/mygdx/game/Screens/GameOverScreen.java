package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

    private MiniMiner game1;
    private Viewport viewport;
    private Stage stage;

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

        //Label gameOverLabel = new Label("Game Over", font);


        playAgainButton = new TextButton("Play again", storeSkin);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button Pressed");
            }

        });
        table = new Table(storeSkin);
        table.center();
        table.setBounds(0, 0, 230, 290);
        table.add(playAgainButton).width(150).height(100);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(5)));
        stage.addActor(table);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.act();
        stage.draw();

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
