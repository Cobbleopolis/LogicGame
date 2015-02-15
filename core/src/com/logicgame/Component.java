package com.logicgame;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */

public class Component {
    int x, y, rot, width, height;
    int state, newState;
    Board board;

    public Component(int x,int y,int rot, int width, int height, int state, Board board) {
        this.x = x;
        this.y = y;
        this.rot = rot;
        this.width = width;
        this.height = height;
        this.state = state;
        this.newState = state;
        this.board = board;
    }
    public void updateWires() {
        if ((state & 1) == 1 && !board.wires[x - 1][y].state)
            board.wires[x - 1][y].update();
        if ((state & 2) == 2 && !board.wires[x][y + 1].state)
            board.wires[x][y + 1].update();
        if ((state & 4) == 4 && !board.wires[x + 1][y].state)
            board.wires[x + 1][y].update();
        if ((state & 8) == 8 && !board.wires[x][y - 1].state)
            board.wires[x][y - 1].update();

    }
    public void update() {
        System.out.println("something went wrong");
    }
    public void reState() {
        state = newState;
    }
}
