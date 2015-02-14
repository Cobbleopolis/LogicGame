package com.logicgame;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Wire {
    int x, y;
    Board board;
    boolean state, newState;

    public Wire(int x,int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.board.wires[x][y] = this;
        this.state = false;
        this.newState = false;
    }
    public void update() {
        this.state = true;
    }
    public void reset() {
        this.state = false;
    }
}
