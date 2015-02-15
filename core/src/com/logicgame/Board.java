package com.logicgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2015.
 */
public class Board {
    public int width;
    public int height;
    public int x, y;
    Wire[][] wires;
    Component[][] gates;
    ArrayList<Wire> wiresList;
    ArrayList<Component> gatesList;
    SpriteBatch spriteBatch;
    public static int component_size = 108;
    Texture texture = new Texture("grid.png");
    public Board(int x, int y, int width, int height, SpriteBatch spriteBatch) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wires = new Wire[width+1][height+1];
        this.gates = new Component[width+1][height+1];
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
    public void render() {
        spriteBatch.draw(texture,x,y,width * component_size * 2, height * component_size * 2);
    }
}
