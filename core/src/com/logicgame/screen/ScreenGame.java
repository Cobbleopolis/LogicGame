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
    public ScreenGame(Game g) {
        game = g;
        spriteBatch = new SpriteBatch();
        board = new Board(20,20, spriteBatch);
        board.addComponent(new Not(10,10,0,board));
        board.addComponent(new Not(9,10,2,board));
        board.addWire(new Wire(8, 10, board));
        board.addWire(new Wire(8, 11, board));
        board.addWire(new Wire(9, 11, board));
        board.addWire(new Wire(10, 11, board));
        board.addWire(new Wire(11, 11, board));
        board.addWire(new Wire(11, 10, board));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(img, x, y);
        board.update();
        spriteBatch.end();
        stage.act();
        stage.draw();
//        if(Gdx.input.justTouched())
//            game.setScreen(new ScreenLevelSelect(game));
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
        if(screenX < board.width)
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
