package com.logicgame;

import com.badlogic.gdx.Game;
import com.logicgame.screen.ScreenMainMenu;

public class LogicGame extends Game {
    @Override
    public void create() {
        this.setScreen(new ScreenMainMenu(this));
    }
}
