package com.logicgame;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Board {
    int width;
    int height;
    Wire[][] wires;
    Component[][] gates;
    ArrayList<Wire> wiresList;
    ArrayList<Component> gatesList;
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.wires = new Wire[width][height];
        this.gates = new Component[width][height];
        this.wiresList = new ArrayList<Wire>();
        this.gatesList = new ArrayList<Component>();

    }
    public void update() {
        for(int i = 0; i < wiresList.size(); i++) {
            wiresList.get(i).reset();
        }
        for(int i = 0; i < gatesList.size(); i++) {
            gatesList.get(i).updateWires();
        }
        for(int i = 0; i < gatesList.size(); i++) {
            gatesList.get(i).update();
        }
        for(int i = 0; i < gatesList.size(); i++) {
            gatesList.get(i).reState();
        }
    }
}
