package com.logicgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenMainMenu implements Screen {

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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(img, x, y);
        spriteBatch.end();
        x += xVel;
        y += yVel;
        if(x > Gdx.graphics.getWidth() - 600) {
            xVel *= -1;
//            x = Gdx.graphics.getWidth() - 600;
        }
        if(x < 0){
            xVel *= -1;
//            x = 0;
        }
        if(y > Gdx.graphics.getHeight() - 600) {
            yVel *= -1;
//            y = Gdx.graphics.getHeight() - 600;
        }
        if(y < 0){
            yVel *= -1;
//            y = 0;
        }

        if (Gdx.input.justTouched())
            System.out.println("Touch");
//            myGame.setScreen(new GameScreen());
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        spriteBatch = new SpriteBatch();
    }
}
