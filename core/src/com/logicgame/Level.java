package com.logicgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex on 2/15/2015.
 */
public class Level {
    MyInput[] inputs;
    MyOutput[] outputs;
    int[][] ins;
    int[][] outs;
    public int num = 0;
    public int max;
    Board board;
    public String instructions;
    public Level(MyInput[] inputs, MyOutput[] outputs, int[][] ins, int[][] outs, Board board, String instructions) {
        this.num = num;
        this.inputs = inputs;
        this.outputs = outputs;
        this.ins = ins;
        this.outs = outs;
        this.board = board;
        this.max = ins.length;
        this.instructions = instructions;

    }
    public void init() {
        for(int i = 0; i < inputs.length; i++) {
            board.addComponent(inputs[i]);
        }
        for(int i = 0; i < outputs.length; i++) {
            board.addComponent(outputs[i]);
        }
    }
    public void setIn() {
        for(int i = 0; i < inputs.length; i++) {
            inputs[i].setState(ins[num][i]);
        }
    }
    public boolean testOut() {
        for(int i = 0; i < outputs.length; i++) {
            if(outputs[i].state != outs[num][i]) {
                System.out.println("fail");
                return false;
            }
        }
        return true;
    }

}
