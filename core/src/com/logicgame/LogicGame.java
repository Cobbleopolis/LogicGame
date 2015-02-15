package com.logicgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.logicgame.screen.ScreenMainMenu;

public class LogicGame extends Game {

    public static int backDelay = 0;
    @Override
    public void create() {
        this.setScreen(new ScreenMainMenu(this));
    }

    @Override
    public void render(){
//        System.out.println(backDelay);
        if(backDelay > 0)
            backDelay--;
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

}
