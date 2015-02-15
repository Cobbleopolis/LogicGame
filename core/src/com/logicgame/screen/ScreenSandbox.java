package com.logicgame.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.*;
import com.logicgame.util.UtilDraw;


/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenSandbox implements Screen, InputProcessor{

    String type;
    Stage stage;

    Skin skin;

    Game game;
    Board board;
    Button1 rotate;
    Button2 none;
    Button2 wire;
    Button2 not;
    Button2 bridge;

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
        board.addComponent(new Xor(5,5, 1, board));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        rotate.render();
        none.render();
        wire.render();
        not.render();
        bridge.render();
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
        rotate = new Button1(0, board.y - 320, 320, 320, new Texture("arrow.png"), 32, 32, board);
        none = new Button2("none", 330, board.y - 160, 160, 160, new Texture("empty.png"), 16, 16, board);
        wire = new Button2("wire", 330, board.y - 320, 160, 160, new Texture("wire_on.png"), 16, 16, board);
        not = new Button2("not", 500, board.y - 160, 160, 160, new Texture("not_on.png"), 16, 16, board);
        bridge = new Button2("bridge", 500, board.y - 320, 160, 160, new Texture("bridge1.png"), 16, 16, board);
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
            if(type == "none")
                board.removeObj(screenX / board.component_size + 1, (Gdx.graphics.getHeight() - screenY - board.y)/ board.component_size + 1);
            if(type == "wire")
                board.addWire(new Wire(screenX / board.component_size + 1, (Gdx.graphics.getHeight() - screenY - board.y)/ board.component_size + 1, board));
            if(type == "not")
                board.addComponent(new Not(screenX / board.component_size + 1, (Gdx.graphics.getHeight() - screenY - board.y)/ board.component_size + 1, rotate.rot, board));
            if(type == "bridge")
                board.addBridge(new Bridge(screenX / board.component_size + 1, (Gdx.graphics.getHeight() - screenY - board.y) / board.component_size + 1, board));
        }
        if (rotate.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            rotate.onPress();
        }

        if (none.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            setSelected(none.type);
            type = none.type;
        }

        if (wire.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            setSelected(wire.type);
            type = wire.type;
        }
        if (not.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            setSelected(not.type);
            type = not.type;
        }

        if (bridge.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            setSelected(bridge.type);
            type = bridge.type;
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

    public void setSelected(String sel){
        if(none.type == sel) {
            none.isSelected = true;
        } else {
            none.isSelected = false;
        }

        if(wire.type == sel) {
            wire.isSelected = true;
        } else {
            wire.isSelected = false;
        }

        if(not.type == sel) {
            not.isSelected = true;
        } else {
            not.isSelected = false;
        }

        if(bridge.type == sel) {
            bridge.isSelected = true;
        } else {
            bridge.isSelected = false;
        }
    }
}