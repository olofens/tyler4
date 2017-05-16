package com.mygdx.game.Tools;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.Utils.Constants;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class StoreHandler {

    private Dialog storePopup;


    public StoreHandler(){

        //Store
        
        Skin storeSkin = new Skin(new FileHandle("skins/rusty-robot-ui.json"), new TextureAtlas(new FileHandle("skins/rusty-robot-ui.atlas")));
        Window.WindowStyle ws = new Window.WindowStyle();
        ws.titleFont = new BitmapFont();
        storeSkin.add("default", ws);
        storeSkin.add("default", new TextButton.TextButtonStyle());

        Texture storepopupImage = new Texture("storepopup.png");
        storeSkin.add("popUpImage", storepopupImage);

        ws.background = storeSkin.getDrawable("popUpImage");

        storePopup = new Dialog("Store", storeSkin);
        storePopup.setBounds((Constants.V_WIDTH - storepopupImage.getWidth()) / 2,
                (Constants.V_HEIGHT - storepopupImage.getHeight() - 10),
                storepopupImage.getWidth(), storepopupImage.getHeight());

        //storePopup.text("Welcome");
        storePopup.button("T1 baby");

        storePopup.setVisible(false);


    }

    public Dialog getStorePopup(){
        return storePopup;
    }
}
