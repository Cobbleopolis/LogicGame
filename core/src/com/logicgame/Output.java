package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Alex on 2/15/2015.
 */
public class Output extends Component{
    Texture texOn = new Texture("write_on.png");
    Texture texOff = new Texture("write_off.png");
    public Output(int x,int y,int rot, int width, int height, int state, Board board) {
        super(x, y, rot, width, height, state, board);
        isIndestructable = true;
    }
    public void update() {
        this.state = 15;
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
        if(y + yOffset2>= board.height && board.wires[x][y + yOffset2] != null && !board.wires[x][y + yOffset2].state) {
            board.lightBridges(4, x, y, yOffset2);
            board.wires[x][y + yOffset2].update();
        }
    }
    public int getState() {
        return state;
    }
    public void render() {
        if(state > 0) {
            board.spriteBatch.draw(texOn, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        } else {
            board.spriteBatch.draw(texOff, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        }
    }
}