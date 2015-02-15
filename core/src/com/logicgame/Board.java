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
        this.wires = new Wire[width+2][height+2];
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
        if(wires[c.x][c.y] != null) {
            removeWire(wires[c.x][c.y]);
        }
        if(bridges[c.x][c.y] != null) {
            removeBridge(bridges[c.x][c.y]);
        }
        if(gates[c.x][c.y] != null && !gates[c.x][c.y].isIndestructable) {
            removeComp(gates[c.x][c.y]);
        }
        if(gates[c.x][c.y] != null && !gates[c.x][c.y].isIndestructable) {
            gatesList.add(c);
            gates[c.x][c.y] = c;
        }
    }
    public void addWire(Wire w) {
        if(wires[w.x][w.y] != null) {
            removeWire(wires[w.x][w.y]);
        }
        if(bridges[w.x][w.y] != null) {
            removeBridge(bridges[w.x][w.y]);
        }
        if(gates[w.x][w.y] != null && !gates[w.x][w.y].isIndestructable) {
            removeComp(gates[w.x][w.y]);
        }
        if(!gates[w.x][w.y].isIndestructable) {
            wiresList.add(w);
            wires[w.x][w.y] = w;
        }
    }
    public void addBridge(Bridge b) {
        if(wires[b.x][b.y] != null) {
            removeWire(wires[b.x][b.y]);
        }
        if(bridges[b.x][b.y] != null) {
            removeBridge(bridges[b.x][b.y]);
        }
        if(gates[b.x][b.y] != null && !gates[b.x][b.y].isIndestructable) {
            removeComp(gates[b.x][b.y]);
        }
        if(!gates[b.x][b.y].isIndestructable) {
            bridgesList.add(b);
            bridges[b.x][b.y] = b;
        }
    }
    public void removeComp(Component c) {
        gatesList.remove(gatesList.indexOf(c));
        gates[c.x][c.y] = null;
    }
    public void removeWire(Wire w) {
        wiresList.remove(wiresList.indexOf(w));
        wires[w.x][w.y] = null;
    }
    public void removeBridge(Bridge b) {
        bridgesList.remove(bridgesList.indexOf(b));
        bridges[b.x][b.y] = null;
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
