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
    Texture texOn = new Texture("wire_on.png");
    Texture texOff = new Texture("wire_off.png");
    @Override
    public void update() {
//        if (this.state != 0) {
            switch (rot) {
                case 2:
                    if (x > 0 && (board.gates[x - 1][y] != null && (board.gates[x - 1][y].state & 4) == 4) || (board.wires[x - 1][y] != null && board.wires[x - 1][y].state)) {
                        this.newState = 0;
                        System.out.println("1");
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 1:
                    if (y < board.height - 1 && (board.gates[x][y + 1] != null && (board.gates[x][y + 1].state & 8) == 8) || (board.wires[x][y + 1] != null && board.wires[x][y + 1].state)) {
                        this.newState = 0;
                        System.out.println("2");
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 0:
                    if (x < board.width - 1 && (board.gates[x + 1][y] != null && (board.gates[x + 1][y].state & 1) == 1) || (board.wires[x + 1][y] != null && board.wires[x + 1][y].state)) {
                        System.out.println("3");
                        this.newState = 0;
                    } else {
                        this.newState = 1 << rot;
                    }
                    break;
                case 3:
                    if (y > 0 && (board.gates[x][y - 1] != null && (board.gates[x][y - 1].state & 2) == 2) || (board.wires[x][y - 1] != null && board.wires[x][y - 1].state)) {
                        this.newState = 0;
                        System.out.println("4");
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
            board.spriteBatch.draw(texOn, x * board.component_size, y * board.component_size, board.component_size, board.component_size);
        } else {
            board.spriteBatch.draw(texOff, x * board.component_size, y * board.component_size, board.component_size, board.component_size);
        }
    }
}
