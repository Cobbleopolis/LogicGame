package com.logicgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LogicGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    //1920x1080
    int x = 0;
    int y = 0;
    int xVel = 4;
    int yVel = 4;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("doge-600.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
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
	}
}
