package com.logicgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.LogicGame;
import com.logicgame.util.UtilDraw;

public class ScreenMainMenu implements Screen {

    BitmapFont font;

    Stage stage;

    Skin skin;

    Game game;

    SpriteBatch spriteBatch;
    Texture img = new Texture("doge-600.png");

    //1920x1080
    int x = 0;
    int y = 0;
    float fontAlpha = 0f;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public ScreenMainMenu(Game g) {
        spriteBatch = new SpriteBatch();
//        spriteBatch.begin();
        game = g;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(fontAlpha < 1f) {
            fontAlpha += .025f;
        }
        if(fontAlpha > 1f) {
            fontAlpha = 1f;
        }
        stage.act();
        stage.draw();
        spriteBatch.begin();
        font.setColor(1.0f, 1.0f, 1.0f, fontAlpha);
        font.setScale(2.5f);
        font.draw(spriteBatch, "Logic Bit", Gdx.graphics.getWidth()/2 - font.getBounds("Logic Bit").width/2, Gdx.graphics.getHeight()*3/4);
        spriteBatch.end();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {
//        spriteBatch = new SpriteBatch();
//        spriteBatch.begin();
        Gdx.input.setCatchBackKey(false);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage = new Stage();
//        stage.setDebugAll(true);
        skin = UtilDraw.createBasicSkin();


        font = UtilDraw.font;

        TextButton button = new TextButton("New game", skin); // Use the initialized skin
        button.setWidth(500);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenLevelSelect(game));
            }
        });
        button.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
//        spriteBatch.end();
    }
}
