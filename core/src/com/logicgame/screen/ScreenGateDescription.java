package com.logicgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class ScreenGateDescription implements Screen {

    Texture wireTex = new Texture("wire_on.png");
    Texture notGate = new Texture("not_on.png");
    Texture bridgeGate = new Texture("bridge1.png");
    Texture andGate = new Texture("and_on.png");
    Texture nandGate = new Texture("nand_on.png");

    BitmapFont font;

    Stage stage;

    Skin skin;

    SpriteBatch spriteBatch;

    Game game;

    public ScreenGateDescription(Game g) { this.game = g; }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        skin = UtilDraw.createBasicSkin();
        font = UtilDraw.font;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        spriteBatch.begin();

        spriteBatch.draw(wireTex, 10, Gdx.graphics.getHeight() - 200, 100, 100);
        font.drawMultiLine(
                spriteBatch, "Carries signal.", 130, Gdx.graphics.getHeight() - (200 - (float) (font.getBounds("Carries signal").height * 1.5)));

        spriteBatch.draw(notGate, 10, Gdx.graphics.getHeight() - 380, 100, 100);
        font.drawMultiLine(
                spriteBatch, "Inverts signal.", 130, Gdx.graphics.getHeight() - (350 - (float)(font.getBounds("Inverts Signal").height * 1.5)));

        spriteBatch.draw(bridgeGate, 10, Gdx.graphics.getHeight() - 560, 100, 100);
        font.drawMultiLine(
                spriteBatch, "Allows two lines to intersect\nwithout affecting the signals.", 130, Gdx.graphics.getHeight() - (520 - (float)(font.getBounds("Inverts Signal").height * 1.5)));

        spriteBatch.draw(andGate, 10, Gdx.graphics.getHeight() - 740, 100, 100);
        font.drawMultiLine(
                spriteBatch, "Both inputs must be on for\nits output to be on.", 130, Gdx.graphics.getHeight() - (700 - (float)(font.getBounds("Inverts Signal").height * 1.5)));

        spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && LogicGame.backDelay == 0){
            game.setScreen(new ScreenMainMenu(game));
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

    }

    @Override
    public void dispose() {

    }
}
