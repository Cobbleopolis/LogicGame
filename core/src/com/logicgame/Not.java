package com.logicgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Alex on 2/14/2015.
 */
public class Not extends Component {
    public Not(int x, int y, int rot, Board board) {
        super(x, y, rot, 1, 1, 1, board);

    }
    Texture texOn = new Texture("not_on.png");
    Texture texOff = new Texture("not_off.png");
    @Override
    public void update() {
//        if (this.state != 0) {
        int xOffset1 = -1;
        int xOffset2 = 1;
        int yOffset1 = -1;
        int yOffset2 = 1;
            switch (rot) {
                case 2:
                    while(x + xOffset1 >= 0 && board.bridges[x + xOffset1][y] != null) {
//                        board.bridges[x + xOffset1][y].state = board.bridges[x + xOffset1][y].state | 1;
                        xOffset1--;
                    }
                    if (x >= -xOffset1 && (board.gates[x + xOffset1][y] != null && (board.gates[x + xOffset1][y].state & 4) == 4) || (board.wires[x + xOffset1][y] != null && board.wires[x + xOffset1][y].state)) {
                        this.newState = 0;
                        board.lightBridges(1, x, y, -xOffset1);
                        System.out.println("1");
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 3:
                    while(y + yOffset2 < board.height + 1 && board.bridges[x][y + yOffset2] != null) {
//                        board.bridges[x][y + yOffset2].state = board.bridges[x][y + yOffset2].state | 2;
                        yOffset2++;
                    }
                    if (y + yOffset2< board.height + 1 && (board.gates[x][y + yOffset2] != null && (board.gates[x][y + yOffset2].state & 8) == 8) || (board.wires[x][y + yOffset2] != null && board.wires[x][y + yOffset2].state)) {
                        this.newState = 0;
                        board.lightBridges(4, x, y, yOffset2);
//                        System.out.println("2");135
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 0:
                    while(x + xOffset2 < board.width + 1 && board.bridges[x + xOffset2][y] != null) {
//                        board.bridges[x + xOffset2][y].state = board.bridges[x + xOffset2][y].state | 1;
                        xOffset2++;
                    }
                    if (x + xOffset2 < board.width + 1 && (board.gates[x + xOffset2][y] != null && (board.gates[x + xOffset2][y].state & 1) == 1) || (board.wires[x + xOffset2][y] != null && board.wires[x + xOffset2][y].state)) {
//                        System.out.println("3");
                        this.newState = 0;
                        board.lightBridges(2, x, y, xOffset2);
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 1:
                    while(y + yOffset1 >= 0 && board.bridges[x][y + yOffset1] != null) {
//                        board.bridges[x][y + yOffset1].state = board.bridges[x][y + yOffset1].state | 2;
                        yOffset1--;
                    }
                    if (y > -yOffset1 && (board.gates[x][y + yOffset1] != null && (board.gates[x][y + yOffset1].state & 2) == 2) || (board.wires[x][y + yOffset1] != null && board.wires[x][y + yOffset1].state)) {
                        this.newState = 0;
                        board.lightBridges(3, x, y, -yOffset1);
//                        System.out.println("4");
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
            }

//        } else {
//            this.newState = 1 << rot;
//        }
    }
    @Override
    public void render() {

        if(state > 0) {
            board.spriteBatch.draw(texOn, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        } else {
            board.spriteBatch.draw(texOff, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        }
    }
}
