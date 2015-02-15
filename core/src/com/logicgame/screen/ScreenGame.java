package com.logicgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.Board;
import com.logicgame.util.UtilDraw;

/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenGame implements Screen {
    Stage stage;

    Skin skin;

    Game game;
    Board board;

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
    public ScreenGame(Game g) {
        game = g;
        spriteBatch = new SpriteBatch();
        Board board = new Board(20,20);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(img, x, y);
        spriteBatch.end();
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
        TextButton button = new TextButton("Level 1", skin); // Use the initialized skin
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
