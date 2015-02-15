package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Alex on 2/15/2015.
 */
public class Bridge {
    int x, y;
    Board board;
    int state = 0;
    Texture tex1 = new Texture("bridge1.png");
    Texture tex2 = new Texture("bridge2.png");
    Texture tex3 = new Texture("bridge3.png");
    Texture tex4 = new Texture("bridge4.png");

    public Bridge(int x,int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.board.bridges[x][y] = this;
    }
    public void reset() {
        state = 0;
    }
    public void render() {
       switch(state) {
           case 0:
               board.spriteBatch.draw(tex1, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
               break;
           case 2:
               board.spriteBatch.draw(tex2, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
               break;
           case 1:
               board.spriteBatch.draw(tex3, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
               break;
           case 3:
               board.spriteBatch.draw(tex4, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size, board.component_size);
               break;
        }
    }
}
