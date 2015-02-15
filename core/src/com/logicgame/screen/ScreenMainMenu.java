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
import com.logicgame.util.UtilDraw;

public class ScreenMainMenu implements Screen {

    Stage stage;

    Skin skin;

    Game game;

    SpriteBatch spriteBatch;
    Texture img = new Texture("doge-600.png");

    //1920x1080
    int x = 0;
    int y = 0;
    int xVel = 4;
    int yVel = 4;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public ScreenMainMenu(Game g) {
        game = g;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    @Override
    public void show() {
        stage = new Stage();
        skin = UtilDraw.createBasicSkin();
        TextButton button = new TextButton("New game", skin); // Use the initialized skin
//        button.padLeft(30f);
//        button.padRight(30f);
        button.pad(100f);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Touch");
            }
        });
        button.setPosition(Gdx.graphics.getWidth()/2 - button.getWidth()/2 , Gdx.graphics.getHeight()/2);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
    }
}
