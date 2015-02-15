package com.logicgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.logicgame.screen.ScreenMainMenu;

public class LogicGame extends Game {

    public static int backDelay = 30;
    @Override
    public void create() {
        this.setScreen(new ScreenMainMenu(this));
    }

    @Override
    public void render(){
        if(backDelay > 0)
            backDelay--;
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

}
