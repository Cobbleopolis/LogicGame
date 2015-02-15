package com.logicgame;

import com.badlogic.gdx.graphics.Texture;

import javax.xml.soap.Text;

/**
 * Created by Alex on 2/15/2015.
 */
public class MyInput extends Component{
    Texture texOn = new Texture("wire_on.png");
    Texture texOff = new Texture("wire_off.png");
    public MyInput(int x, int y, Board board) {
        super(x, y, 0, 1, 1, 15, board);
        isIndestructable = true;
    }
    public void setState(int state) {
        this.state = state;
    }
    public void render() {
        if(state > 0) {
            board.spriteBatch.draw(texOn, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        } else {
            board.spriteBatch.draw(texOff, (x - 1) * board.component_size + board.x, (y - 1) * board.component_size + board.y, board.component_size / 2, board.component_size / 2, board.component_size, board.component_size, 1, 1, -rot * 90,0,0,16,16,false,false);
        }
    }
}
