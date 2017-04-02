package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MiniMiner;

/**
 * Created by erikstrid on 2017-04-02.
 */

public class PlayScreen implements Screen {

    private MiniMiner game;
    private OrthographicCamera gameCam;
    private Viewport viewPort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;



    public PlayScreen(MiniMiner game){
        this.game = game;
        gameCam = new OrthographicCamera();
        viewPort = new FitViewport(480,800, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tmxtest.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(viewPort.getWorldWidth()/2, 1400, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gameCam.position.x += 200 * dt;
        }
    }

    public void update(float dt){
        handleInput(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();


        game.batch.setProjectionMatrix(gameCam.combined);
        //game.batch.begin();
        //game.batch.draw(texture, 0, 0);
        //game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width,height);

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
