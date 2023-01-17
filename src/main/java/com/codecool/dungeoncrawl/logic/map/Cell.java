package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.view.Drawable;
import com.codecool.dungeoncrawl.logic.actors.abstraction.Actor;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    /** getNeighbor method tries to fetch a cell from the GameMap object that is in position relative to the Cell object
     *  on which the method is being called. gameMap.getCell(x + dx, y + dy) will return a neighboring Cell if it exists
     *  or null if it does not exist.*/
    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}