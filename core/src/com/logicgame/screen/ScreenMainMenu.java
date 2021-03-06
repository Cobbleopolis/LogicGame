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

public class ScreenMainMenu implements Screen {

    boolean backBool = false;

    BitmapFont font;

    Stage stage;

    Skin skin;

    Game game;

    SpriteBatch spriteBatch;

    public static float fontAlpha = 0f;

    /**
     * Constructor for the splash screen
     *
     * @param g Game which called this splash screen.
     */
    public ScreenMainMenu(Game g) {
        spriteBatch = new SpriteBatch();
        game = g;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && LogicGame.backDelay == 0) //{
            Gdx.input.setCatchBackKey(false);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (fontAlpha < 1f) {
            fontAlpha += .025f;
        }
        if (fontAlpha > 1f) {
            fontAlpha = 1f;
        }
        stage.act();
        stage.draw();
        spriteBatch.begin();
        font.setColor(1.0f, 1.0f, 1.0f, fontAlpha);
        font.setScale(2.5f);
        font.draw(spriteBatch, "Logic Bit", Gdx.graphics.getWidth() / 2 - font.getBounds("Logic Bit").width / 2, Gdx.graphics.getHeight() * 3 / 4);
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
        Gdx.input.setCatchBackKey(true);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage = new Stage();
        skin = UtilDraw.createBasicSkin();


        font = UtilDraw.font;

        TextButton levelSelectButton = new TextButton("Level Select", skin); // Use the initialized skin
        levelSelectButton.setWidth(UtilDraw.getXFrom1080(500));
        levelSelectButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenLevelSelect(game));
            }
        });
        levelSelectButton.setPosition(Gdx.graphics.getWidth() / 2 - levelSelectButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage.addActor(levelSelectButton);

        TextButton sandboxButton = new TextButton("Sandbox", skin); // Use the initialized skin
        sandboxButton.setWidth(UtilDraw.getXFrom1080(500));
        sandboxButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenSandbox(game));
            }
        });
        sandboxButton.setPosition(Gdx.graphics.getWidth() / 2 - sandboxButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - UtilDraw.getYFrom1920(277));
        stage.addActor(sandboxButton);

        TextButton gateDescriptionButton = new TextButton("Gate Descriptions", skin); // Use the initialized skin
        gateDescriptionButton.setWidth(UtilDraw.getXFrom1080(600));
        gateDescriptionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScreenGateDescription(game));
            }
        });
        gateDescriptionButton.setPosition(Gdx.graphics.getWidth() / 2 - gateDescriptionButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - UtilDraw.getYFrom1920(554));
        stage.addActor(gateDescriptionButton);


        Gdx.input.setInputProcessor(stage);// Make the stage consume events
    }
}
