package com.logicgame;

/**
 * Created by Alex on 2/14/2015.
 */
public class Board {
    int width;
    int height;
    Wire[][] wires;
    Component[][] gates;
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.wires = new Wire[width][height];
        this.gates = new Component[width][height];
    }
}
