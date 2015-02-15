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

/**
 * Created by Alex on 2/14/2015.
 */
public class ScreenLevelSelect implements Screen {

    BitmapFont font;

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
    public ScreenLevelSelect(Game g) {
        game = g;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        spriteBatch.begin();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.setScale(2f);
        float h = font.getBounds("Level Select").height;
        font.draw(spriteBatch, "Level Select", Gdx.graphics.getWidth() / 2 - font.getBounds("Level Select").width / 2, Gdx.graphics.getHeight() - (h + 30));
        font.setScale(.5f);
        font.draw(spriteBatch, "-Coming Soon-", Gdx.graphics.getWidth()/2 - font.getBounds("Level Select").width/2, Gdx.graphics.getHeight() - (h + 125));
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() { //button height is 177.0
        Gdx.input.setCatchBackKey(true);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        skin = UtilDraw.createBasicSkin();
        font = UtilDraw.font;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 5; j++){
                final String lvl = "" + (j + (i * 5) + 1);
                TextButton button = new TextButton(lvl, skin.get("disabled", TextButton.TextButtonStyle.class)); // Use the initialized skin
                button.setWidth(150);
                button.setHeight(150);
                button.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new ScreenGame(game, lvl));
                    }
                });

                button.setPosition(50 + (200 * j), Gdx.graphics.getHeight() - (200 * (i + 1)) - 300);

                stage.addActor(button);
            }
        }

        Gdx.input.setInputProcessor(stage);// Make the stage consume events
    }
}
