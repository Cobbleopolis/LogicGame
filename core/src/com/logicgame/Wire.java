package com.logicgame;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Wire {
    int x, y;
    Board board;
    boolean state;

    public Wire(int x,int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.board.wires[x][y] = this;
        this.state = false;
    }
    public void update() {
        this.state = true;
        if(!board.wires[x - 1][y].state)
            board.wires[x - 1][y].update();
        if(!board.wires[x + 1][y].state)
            board.wires[x + 1][y].update();
        if(!board.wires[x][y - 1].state)
            board.wires[x][y - 1].update();
        if(!board.wires[x][y + 1].state)
            board.wires[x][y + 1].update();
    }
    public void reset() {
        this.state = false;
    }
}
