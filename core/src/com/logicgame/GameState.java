package com.logicgame;

/**
 * Created by Alex on 2/14/2015.
 */
enum State {
    TITLE_STATE,
    MAINGAME_STATE,
    PAUSE_STATE
}
public class GameState {
    State state;
    public GameState(State state) {
        this.state = state;
    }
    void GameStateUpdate() {
        // handle update
        switch (state) {
            case TITLE_STATE:
                // stuff
                break;
            case MAINGAME_STATE:
                // stuff
                break;
            case PAUSE_STATE:
                //stuff
                break;
        }
    }
}