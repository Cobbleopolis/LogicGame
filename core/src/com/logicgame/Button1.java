package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Alex on 2/15/2015.
 */
public class Button1 {
    public int x, y, width, height, rot, textureWidth, textureHeight;
    Texture texture;
    Board board;
    public Button1(int x,int y, int width, int height, Texture texture , int textureWidth, int textureHeight, Board board) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rot = 0;
        this.texture = texture;
        this.board = board;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }
    public boolean isPressed(int x, int y) {
        if(x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
            return true;
        } else {
            return false;
        }
    }
    public void onPress() {
        rot++;
        if(rot > 3)
            rot = 0;
    }
    public void render() {
        board.spriteBatch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, -rot * 90 + 90,0,0, textureWidth, textureHeight,false,false);
    }
}
