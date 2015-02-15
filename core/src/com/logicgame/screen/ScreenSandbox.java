package com.logicgame.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.Board;
import com.logicgame.LogicGame;
import com.logicgame.Not;
import com.logicgame.util.UtilDraw;

/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenSandbox implements Screen, InputProcessor{
    Stage stage;

    Skin skin;

    Game game;
    Board board;
    BitmapFont font;

    SpriteBatch spriteBatch;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public ScreenSandbox(Game g) {
        game = g;
        spriteBatch = new SpriteBatch();
        board = new Board(0, Gdx.graphics.getHeight() - 10 * Board.component_size, 10,10, spriteBatch);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        board.render();
        board.update();
        spriteBatch.end();
        stage.act();
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && LogicGame.backDelay == 0){
            game.setScreen(new ScreenLevelSelect(game));
            LogicGame.backDelay = 30;
        }
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

        TextButton directionButton = new TextButton("▲", skin); // Use the initialized skin
        directionButton.setWidth(300);
        directionButton.setHeight(300);
        directionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenLevelSelect(game));
            }
        });
        directionButton.setPosition(0, board.y - directionButton.getHeight());
        stage.addActor(directionButton);

        TextButton sandboxButton = new TextButton("Sandbox", skin); // Use the initialized skin
        sandboxButton.setWidth(500);
        sandboxButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenSandbox(game));
            }
        });
        sandboxButton.setPosition(Gdx.graphics.getWidth() / 2 - sandboxButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 277);
        stage.addActor(sandboxButton);


        Gdx.input.setInputProcessor(stage);// Make the stage consume events
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX);
        if(screenX < board.width * board.component_size && (Gdx.graphics.getHeight() - screenY - board.y ) > 0) {
            board.addComponent(new Not(screenX / board.component_size + 1, (Gdx.graphics.getHeight() - screenY - board.y)/ board.component_size + 1, 0, board));
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}