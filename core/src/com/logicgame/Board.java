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
    Bridge[][] bridges;
    ArrayList<Wire> wiresList;
    ArrayList<Component> gatesList;
    ArrayList<Bridge> bridgesList;
    SpriteBatch spriteBatch;
    public static int component_size = 108;
    Texture texture = new Texture("grid.png");
    public Board(int x, int y, int width, int height, SpriteBatch spriteBatch) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wires = new Wire[width+1][height+1];
        this.gates = new Component[width+2][height+2];
        this.bridges = new Bridge[width+2][height+2];
        this.wiresList = new ArrayList<Wire>();
        this.gatesList = new ArrayList<Component>();
        this.bridgesList = new ArrayList<Bridge>();
        this.spriteBatch = spriteBatch;

    }
    public void update() {
        for(int i = 0; i < wiresList.size(); i++) {
            wiresList.get(i).reset();
        }
        for(int i = 0; i < bridgesList.size(); i++) {
            bridgesList.get(i).reset();
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
    public void addComponent(Component c) {
        gatesList.add(c);
        gates[c.x][c.y] = c;
    }
    public void addWire(Wire w) {
        wiresList.add(w);
        wires[w.x][w.y] = w;
    }
    public void addBridge(Bridge b) {
        bridgesList.add(b);
        bridges[b.x][b.y] = b;
    }
    public void lightBridges(int dir, int x, int y, int length) {
        for(int i = 1; i < length; i++) {
            if(dir == 1) {
                bridges[x - i][y].state = bridges[x - i][y].state | 1;
            }
            if(dir == 2) {
                bridges[x + i][y].state = bridges[x + i][y].state | 1;
            }
            if(dir == 3) {
                bridges[x][y - i].state = bridges[x][y - i].state | 2;
            }
            if(dir == 4) {
                bridges[x][y + i].state = bridges[x][y + i].state | 2;
            }
        }

    }
    public void render() {
        spriteBatch.draw(texture,x,y,width * component_size * 2, height * component_size * 2);
        for(int i = 0; i < gatesList.size(); i++) {
            gatesList.get(i).render();
        }
        for(int i = 0; i < wiresList.size(); i++) {
            wiresList.get(i).render();
        }
        for(int i = 0; i < bridgesList.size(); i++) {
            bridgesList.get(i).render();
        }
    }
}
