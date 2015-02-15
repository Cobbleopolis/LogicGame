package com.logicgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Board {
    public int width;
    public int height;
    Wire[][] wires;
    Component[][] gates;
    ArrayList<Wire> wiresList;
    ArrayList<Component> gatesList;
    SpriteBatch spriteBatch;
    int component_size = 64;
    public Board(int width, int height, SpriteBatch spriteBatch) {
        this.width = width;
        this.height = height;
        this.wires = new Wire[width][height];
        this.gates = new Component[width][height];
        this.wiresList = new ArrayList<Wire>();
        this.gatesList = new ArrayList<Component>();
        this.spriteBatch = spriteBatch;

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
        for(int i = 0; i < gatesList.size(); i++) {
            gatesList.get(i).render();
        }
        for(int i = 0; i < wiresList.size(); i++) {
            wiresList.get(i).render();
        }
    }
    public void addComponent(Component c) {
        gatesList.add(c);
        gates[c.x][c.y] = c;
    }
    public void addWire(Wire w) {
        wiresList.add(w);
        wires[w.x][w.y] = w;
    }
}
