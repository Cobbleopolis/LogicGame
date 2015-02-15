package com.logicgame;

/**
 * Created by Alex on 2/14/2015.
 */
public class Not extends Component {
    public Not(int x, int y, int rot, Board board) {
        super(x, y, rot, 1, 1, 1, board);

    }
    @Override
    public void update() {
        if (this.state != 0) {
            switch (rot) {
                case 0:
                    if ((board.gates[x - 1][y].state & 4) == 4 || (board.wires[x - 1][y].state))
                        this.newState = 0;
                    break;
                case 1:
                    if ((board.gates[x][y + 1].state & 8) == 8 || (board.wires[x][y + 1].state))
                        this.newState = 0;
                    break;
                case 2:
                    if ((board.gates[x + 1][y].state & 1) == 1 || (board.wires[x + 1][y].state))
                        this.newState = 0;
                    break;
                case 3:
                    if ((board.gates[x][y - 1].state & 2) == 2 || (board.wires[x][y - 1].state))
                        this.newState = 0;
                    break;
            }

        } else {
            this.newState = 1 << rot;
        }
    }
}
