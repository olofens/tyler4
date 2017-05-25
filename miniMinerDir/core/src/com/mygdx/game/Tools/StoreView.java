package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by olofenstrom on 2017-05-25.
 */

public class StoreView {

    private TextButton topButton;
    private TextButton bottomButton;
    private Table table;

    public StoreView(String topButtonText, String bottomButtonText) {
        Skin storeSkin = new Skin(Gdx.files.internal("skins/rusty-robot-ui.json"),
                new TextureAtlas(Gdx.files.internal("skins/rusty-robot-ui.atlas")));

        topButton = new TextButton(topButtonText, storeSkin);
        bottomButton = new TextButton(bottomButtonText, storeSkin);

        table = new Table(storeSkin);
        table.background(storeSkin.get(Window.WindowStyle.class).background);
        table.center();
        table.setBounds(5, 80, 230, 290);
        table.add(topButton).width(150).height(100).pad(30, 0, 0, 0);
        table.row();
        table.add(bottomButton).width(150).height(100);
        table.setVisible(false);
    }

    public TextButton getTopButton() {
        return topButton;
    }

    public TextButton getBottomButton() {
        return bottomButton;
    }

    public Table getTable() {
        return table;
    }
}
