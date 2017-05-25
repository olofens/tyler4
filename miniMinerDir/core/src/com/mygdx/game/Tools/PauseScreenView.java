package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by olofenstrom on 2017-05-25.
 */

public class PauseScreenView {
    private TextButton resumeButton;
    private TextButton menuButton;
    private Table table;

    public PauseScreenView() {
        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        resumeButton = new TextButton("Resume", storeSkin);
        menuButton = new TextButton("Main Menu", storeSkin);

        table = new Table(storeSkin);
        table.center();
        table.setBounds(10, 90, 190, 210);
        table.add(resumeButton);
        table.row();
        table.add(menuButton);
    }

    public Table getTable() {
        return table;
    }

    public TextButton getResumeButton() {
        return resumeButton;
    }

    public TextButton getMenuButton() {
        return menuButton;
    }
}
