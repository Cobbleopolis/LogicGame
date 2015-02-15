package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Wire {
    int x, y;
    Board board;
    boolean state;
    Texture texOn = new Texture("wire_on.png");
    Texture texOff = new Texture("wire_off.png");

    public Wire(int x,int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.board.wires[x][y] = this;
        this.state = false;
    }
    public void update() {
        this.state = true;
        if(x > 0 && board.wires[x - 1][y] != null && !board.wires[x - 1][y].state)
            board.wires[x - 1][y].update();
        if(x < board.width - 1 && board.wires[x + 1][y] != null && !board.wires[x + 1][y].state)
            board.wires[x + 1][y].update();
        if(y > 0 && board.wires[x][y - 1] != null && !board.wires[x][y - 1].state)
            board.wires[x][y - 1].update();
        if(y < board.height - 1 && board.wires[x][y + 1] != null && !board.wires[x][y + 1].state)
            board.wires[x][y + 1].update();
    }
    public void reset() {
        this.state = false;
    }
    public void render() {
        if(state) {
            board.spriteBatch.draw(texOn, x * 16, y * 16);
        } else {
            board.spriteBatch.draw(texOff, x * 16, y * 16);
        }
    }
}
