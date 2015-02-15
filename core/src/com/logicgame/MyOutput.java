package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Alex on 2/15/2015.
 */
public class MyOutput extends Component{
    Texture texOn = new Texture("wire_on.png");
    Texture texOff = new Texture("wire_off.png");
    public MyOutput(int x, int y, Board board) {
        super(x, y, 0, 1, 1, 0, board);
        isIndestructable = true;
    }
    public void update() {
        int xOffset1 = -1;
        int xOffset2 = 1;
        int yOffset1 = -1;
        int yOffset2 = 1;
        while(x + xOffset1 >= 0 && board.bridges[x + xOffset1][y] != null) {
//                        board.bridges[x + xOffset1][y].state = board.bridges[x + xOffset1][y].state | 1;
            xOffset1--;
        }
        if (x >= -xOffset1 && (board.gates[x + xOffset1][y] != null && (board.gates[x + xOffset1][y].state & 4) == 4) || (board.wires[x + xOffset1][y] != null && board.wires[x + xOffset1][y].state)) {
            this.newState = 15;
            board.lightBridges(1, x, y, -xOffset1);
            System.out.println("1");
        } else {
            this.newState = 0;
        }
        while(y + yOffset2 < board.height && board.bridges[x][y + yOffset2] != null) {
//                        board.bridges[x][y + yOffset2].state = board.bridges[x][y + yOffset2].state | 2;
            yOffset2++;
        }
        if (y + yOffset2< board.height + 1 && (board.gates[x][y + yOffset2] != null && (board.gates[x][y + yOffset2].state & 8) == 8) || (board.wires[x][y + yOffset2] != null && board.wires[x][y + yOffset2].state)) {
            this.newState = 15;
            board.lightBridges(4, x, y, yOffset2);
//                        System.out.println("2");
        } else {
            this.newState = 0;
        }
        while(x + xOffset2 < board.width && board.bridges[x + xOffset2][y] != null) {
//                        board.bridges[x + xOffset2][y].state = board.bridges[x + xOffset2][y].state | 1;
            xOffset2++;
        }
        if (x + xOffset2 < board.width + 1 && (board.gates[x + xOffset2][y] != null && (board.gates[x + xOffset2][y].state & 1) == 1) || (board.wires[x + xOffset2][y] != null && board.wires[x + xOffset2][y].state)) {
//                        System.out.println("3");
            this.newState = 15;
            board.lightBridges(2, x, y, xOffset2);
        } else {
            this.newState = 0;
        }
        while(y + yOffset1 >= 0 && board.bridges[x][y + yOffset1] != null) {
//                        board.bridges[x][y + yOffset1].state = board.bridges[x][y + yOffset1].state | 2;
            yOffset1--;
        }
        if (y > -yOffset1 && (board.gates[x][y + yOffset1] != null && (board.gates[x][y + yOffset1].state & 2) == 2) || (board.wires[x][y + yOffset1] != null && board.wires[x][y + yOffset1].state)) {
            this.newState = 15;
            board.lightBridges(3, x, y, -yOffset1);
//                        System.out.println("4");
        } else {
            this.newState = 0;
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