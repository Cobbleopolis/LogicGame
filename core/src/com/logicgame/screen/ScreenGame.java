package com.logicgame.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.logicgame.*;
import com.logicgame.util.UtilDraw;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenGame implements Screen, InputProcessor{
    Stage stage;
    String type;
    Skin skin;

    Game game;
    Board board;
    Button1 rotate;
    Button1 test;
    Button2 none;
    Button2 wire;
    Button2 not;
    Button2 bridge;
    int status = 0;
    Texture win = new Texture("win.png");
    Texture fail = new Texture("fail.png");

    SpriteBatch spriteBatch;
    Level thisLevel;
    boolean testing = false;
    int timeout = 0;
    BitmapFont font = UtilDraw.font;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public ScreenGame(Game g, int level) {
        game = g;
        spriteBatch = new SpriteBatch();
        board = new Board(0, Gdx.graphics.getHeight() - 10 * Board.component_size, 10,10, spriteBatch);
        thisLevel = getLevel(level);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        rotate.render();
        test.render();
        none.render();
        wire.render();
        not.render();
        bridge.render();
        font.drawMultiLine(spriteBatch, thisLevel.instructions, 700, board.y - 20);
        board.render();

        board.update();
        if(status == 1) {
            spriteBatch.draw(win,330,board.y - 640, 320, 320);
        }
        if(status == 2) {
            spriteBatch.draw(fail,330,board.y - 640, 320, 320);
        }

        spriteBatch.end();
        stage.act();
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && LogicGame.backDelay == 0){
            game.setScreen(new ScreenLevelSelect(game));
            LogicGame.backDelay = 30;
        }
        if(testing) {
            status = 0;
            thisLevel.setIn();

            timeout++;
            if(timeout == 100) {
                if (thisLevel.testOut()) {
                    thisLevel.num++;
                    timeout = 0;
                }
            }
            if(timeout == 100) {
                if(thisLevel.testOut()) {
                    thisLevel.num++;
                    timeout = 0;
                }
                status = 2;
                thisLevel.num = 0;
                timeout = 0;
                testing = false;
            }
            if(thisLevel.num == thisLevel.max) {
                status = 1;
                thisLevel.num = 0;
                timeout = 0;
                testing = false;
            }
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
        thisLevel.init();
        skin = UtilDraw.createBasicSkin();
        rotate = new Button1(0, board.y - 320, 320, 320, new Texture("arrow.png"), 32, 32, board);
        test = new Button1(0, board.y - 640, 320, 320, new Texture("test.png"), 32, 32, board);
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
        if(!testing && screenX < board.width * board.component_size && (Gdx.graphics.getHeight() - screenY - board.y ) > 0) {
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
        if(test.isPressed(screenX, Gdx.graphics.getHeight() - screenY)) {
            testing = true;
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
    public Level getLevel(int lvl) {
        MyInput[] temp0 = {new MyInput(5, 1, board)};
        MyOutput[] tempt = {new MyOutput(6, 9, board)};
        int[][] tempc = {{0}, {15}};
        int[][] tempu = {{0}, {15}};
        Level level = new Level(temp0, tempt, tempc, tempu, board, "Match input with output");
        switch(lvl) {
            case 1:
                MyInput[] temp1 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp2 = {new MyOutput(5, 10, board)};
                int[][] temp3 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp4 = {{0}, {15}, {15}, {15}};
                level = new Level(temp1, temp2, temp3, temp4, board, "Turn on output when\n either input is on");
            break;
            case 2:
                MyInput[] temp5 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp6 = {new MyOutput(5, 10, board)};
                int[][] temp7 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp8 = {{15}, {0}, {0}, {0}};
                level = new Level(temp5, temp6, temp7, temp8, board, "Turn on output when\n neither input is on");
            break;
            case 3:
                MyInput[] temp9 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp10 = {new MyOutput(5, 10, board)};
                int[][] temp11 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp12 = {{15}, {15}, {15}, {0}};
                level =  new Level(temp9, temp10, temp11, temp12, board, "Turn off output when\n both inputs are on");
            break;
            case 4:
                MyInput[] temp13 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp14 = {new MyOutput(5, 10, board)};
                int[][] temp15 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp16 = {{0}, {0}, {0}, {15}};
                level =  new Level(temp13, temp14, temp15, temp16, board, "Turn on output when\n both inputs are on");
                break;
            case 5:
                MyInput[] temp17 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp18 = {new MyOutput(5, 10, board)};
                int[][] temp19 = {{0, 0}, {15, 0}, {0, 0}, {0, 15}, {0, 0}};
                int[][] temp20 = {{0}, {15}, {15}, {0}, {0}};
                level =  new Level(temp17, temp18, temp19, temp20, board, "Turn on output when\n first input is on.\n Then, keep output on\n until second input\n turns on.");
                break;
            case 6:
                MyInput[] temp21 = {new MyInput(3, 1, board), new MyInput(7, 1, board)};
                MyOutput[] temp22 = {new MyOutput(5, 10, board)};
                int[][] temp23 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp24 = {{0}, {15}, {15}, {0}};
                level =  new Level(temp21, temp22, temp23, temp24, board, "Turn on output when\nonly one input is on.");
                break;
            case 7:
                MyInput[] temp25 = {new MyInput(2, 1, board), new MyInput(5, 1, board)};
                MyOutput[] temp26 = {new MyOutput(5, 10, board)};
                int[][] temp27 = {{0, 0}, {15, 0}, {0, 15}, {15, 15}};
                int[][] temp28 = {{0}, {15}, {15}, {0}};
                level =  new Level(temp25, temp26, temp27, temp28, board, "Turn on output when\nonly one input is on.");
                break;



        }
        return level;
    }
}
