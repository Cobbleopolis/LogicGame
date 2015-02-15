package com.logicgame.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.Board;
import com.logicgame.LogicGame;
import com.logicgame.Not;
import com.logicgame.Wire;
import com.logicgame.util.UtilDraw;

/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenGame implements Screen, InputProcessor{
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
    public ScreenGame(Game g, String level) {
        game = g;
        spriteBatch = new SpriteBatch();
        board = new Board(0, Gdx.graphics.getHeight() - 10 * Board.component_size, 10,10, spriteBatch);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
//        spriteBatch.draw(img, x, y);
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage = new Stage();
        skin = UtilDraw.createBasicSkin();
        Gdx.input.setInputProcessor(this);// Make the stage consume events

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
            board.addComponent(new Not(screenX / board.component_size, (Gdx.graphics.getHeight() - screenY - board.y)/ board.component_size, 0, board));
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
