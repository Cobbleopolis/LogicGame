package com.logicgame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class UtilDraw {

    public static BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

    public static Skin createBasicSkin(){
        Skin skin;
        //Create a font
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setScale(1f);
        skin = new Skin();
        skin.add("font", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.get("font", BitmapFont.class);
        skin.add("default", textButtonStyle);

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle2 = new TextButton.TextButtonStyle();
        textButtonStyle2.up = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle2.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle2.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle2.over = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle2.font = skin.get("font", BitmapFont.class);
        skin.add("disabled", textButtonStyle2);

        return skin;
    }

    public static float getXFrom1080(int x){
        return ((float)x / (float)1080) * (float)Gdx.graphics.getWidth();
    }

    public static float getYFrom1920(int y){
        return ((float)y / (float)1920) * (float)Gdx.graphics.getHeight();
    }
}
