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
//        this.board.wires[x][y] = this;
        this.state = false;
    }
    public void update() {
        this.state = true;
        int xOffset1 = -1;
        int xOffset2 = 1;
        int yOffset1 = -1;
        int yOffset2 = 1;

        while(x + xOffset1 >= 0 && board.bridges[x + xOffset1][y] != null) {
//            board.bridges[x + xOffset1][y].state = board.bridges[x + xOffset1][y].state | 1;
            xOffset1--;
        }
//        if(x >= -xOff)
        if(x >= -xOffset1 && board.wires[x + xOffset1][y] != null && !board.wires[x + xOffset1][y].state) {
            board.lightBridges(1, x, y, -xOffset1);
            board.wires[x + xOffset1][y].update();
        }

        while(x + xOffset2 < board.width && board.bridges[x + xOffset2][y] != null) {
//            board.bridges[x + xOffset2][y].state = board.bridges[x + xOffset2][y].state | 1;
            xOffset2++;
        }
        if(x + xOffset2 < board.width && board.wires[x + xOffset2][y] != null && !board.wires[x + xOffset2][y].state) {
            board.lightBridges(2, x, y, xOffset2);
            board.wires[x + xOffset2][y].update();
        }

        while(y + yOffset1 >= 0 && board.bridges[x][y + yOffset1] != null) {
//            board.bridges[x][y + yOffset1].state = board.bridges[x][y + yOffset1].state | 2;
            yOffset1--;
        }
        if(y >= -yOffset1 && board.wires[x][y + yOffset1] != null && !board.wires[x][y + yOffset1].state) {
            board.lightBridges(3, x, y, -yOffset1);
            board.wires[x][y + yOffset1].update();
        }


        while(y + yOffset2 < board.height && board.bridges[x][y + yOffset2] != null) {
//            board.bridges[x][y + yOffset2].state = board.bridges[x][y + yOffset2].state | 2;
            yOffset2++;
        }
        if(y + yOffset2 < board.height && board.wires[x][y + yOffset2] != null && !board.wires[x][y + yOffset2].state) {
            board.lightBridges(4, x, y, yOffset2);
            board.wires[x][y + yOffset2].update();
        }
    }
    public void reset() {
        this.state = false;
    }
    public void render() {
        if(state) {
            board.spriteBatch.draw(texOn, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
        } else {
            board.spriteBatch.draw(texOff, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
        }
    }
}
