package com.logicgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex on 2/15/2015.
 */
public class Level {
    ArrayList<MyInput> inputs;
    ArrayList<MyOutput> outputs;
    int[][] ins;
    int[][] outs;
    public int num = 0;
    public int max = 4;
    public Level(int num, ArrayList<MyInput> inputs, ArrayList<MyOutput> outputs, int[][] ins, int[][] outs) {
        this.num = num;
        this.inputs = inputs;
        this.outputs = outputs;
        this.ins = ins;
        this.outs = outs;

    }
    public void setIn() {
        for(int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setState(ins[num][i]);
        }
    }
    public boolean testOut() {
        for(int i = 0; i < outputs.size(); i++) {
            if(outputs.get(i).state != outs[num][i]) {
                return false;
            }
        }
        return true;
    }
}
