package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-16.
 */

public class GameOverScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private TextButton playAgainButton;
    private TextButton mainMenu;
    private Table table;
    private Game game;

    private ScreenSetter sh;

    public GameOverScreen(Game game) {

        this.game = game;
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MiniMiner) game).batch);

        sh = new ScreenSetter(game);
        Gdx.input.setInputProcessor(stage);

        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        playAgainButton = new TextButton("Play again", storeSkin);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sh.createPlayScreen();
            }
        });

        mainMenu = new TextButton("Main menu", storeSkin);
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sh.createStartMenuScreen();
            }
        });

        table = new Table(storeSkin);
        table.center();
        table.setBounds(10, 80, 190, 230);
        table.add(playAgainButton).width(150).height(100);
        table.row();
        table.add(mainMenu).width(150).height(100);

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
