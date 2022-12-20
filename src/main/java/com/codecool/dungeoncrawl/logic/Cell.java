package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Ork;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;
    private Item item;

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

    public void setItem(Item item) {
        this.item = item;
    }
    //you can walk on item
    public boolean isItem() {
        return this.type.equals(CellType.KEY) || this.type.equals(CellType.SWORD)
                || this.type.equals(CellType.ARMOR) || this.type.equals(CellType.AIDKIT);

    }
    //you can walk on it
    public boolean goingThrough() {
        return this.getType().equals(CellType.FLOOR) || this.isItem() || this.type.equals(CellType.ICE)
                || this.type.equals(CellType.GRASS)  || this.type.equals(CellType.DOOR) ;
    }


    //https://www.baeldung.com/java-instanceof
    public boolean isEnemy() {
        return this.actor instanceof Monster || this.actor instanceof Ork;
    }

    public boolean isPlayer() {
        return this.actor instanceof Player;
    }

    public boolean isDoor() {
        return this.type.equals(CellType.DOOR);
    }
}
