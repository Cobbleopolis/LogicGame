package com.logicgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */

public class Component {
    int x, y, rot, width, height;
    int state, newState;
    Board board;
    SpriteBatch spriteBatch;
    Texture texOn = new Texture("wire_on.png");
    public Boolean isIndestructable = false;

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
        if (x > 0 && board.wires[x - 1][y] != null && (state & 1) == 1 && !board.wires[x - 1][y].state)
            board.wires[x - 1][y].update();
        if (y < board.height  && board.wires[x][y + 1] != null && (state & 2) == 2 && !board.wires[x][y + 1].state)
            board.wires[x][y + 1].update();
        if (x < board.width  && board.wires[x + 1][y] != null && (state & 4) == 4 && !board.wires[x + 1][y].state)
            board.wires[x + 1][y].update();
        if (y > 0 && board.wires[x][y - 1] != null && (state & 8) == 8 && !board.wires[x][y - 1].state)
            board.wires[x][y - 1].update();

    }
    public void update() {

    }
    public void render() {

    }
    public void reState() {
        state = newState;
    }
}
