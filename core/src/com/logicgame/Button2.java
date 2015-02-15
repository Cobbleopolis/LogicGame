package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

public class Button2 {
    public int x, y, width, height, rot, textureWidth, textureHeight;
    Texture texture;
    Texture selectedTexture;
    Board board;
    public String type;
    public boolean isSelected;

    public Button2(String type, int x, int y, int width, int height, Texture texture, int textureWidth, int textureHeight, Board board) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rot = 0;
        this.texture = texture;
        this.selectedTexture = new Texture("selected.png");
        this.board = board;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public boolean isPressed(int x, int y) {
        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
            return true;
        } else {
            return false;
        }
    }

    public void render() {
        board.spriteBatch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, -rot * 90 + 90, 0, 0, textureWidth, textureHeight, false, false);
        if(isSelected)
            board.spriteBatch.draw(selectedTexture, x, y, width / 2, height / 2, width, height, 1, 1, -rot * 90 + 90, 0, 0, textureWidth, textureHeight, false, false);
    }
}
