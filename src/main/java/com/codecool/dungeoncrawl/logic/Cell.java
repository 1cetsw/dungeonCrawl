package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
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

    public boolean setType(CellType type) {
        this.type = type;
        return false;
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
                || this.type.equals(CellType.GRASS) || this.type.equals(CellType.DOOR);
        }
    public boolean openDoor () {
        return this.type.equals(CellType.DOOR);
    }
        // walk in wall
        public boolean ghostMode () {
            return this.getType().equals(CellType.FLOOR) || this.isItem() || this.type.equals(CellType.ICE)
                    || this.type.equals(CellType.GRASS) || this.type.equals(CellType.DOOR)
                    || this.type.equals(CellType.WALL) || this.type.equals(CellType.EMPTY);
//                || this.type.equals(CellType.PLAYER); this option clear mobs
        }


        //https://www.baeldung.com/java-instanceof
        public boolean isEnemy () {
            return this.actor instanceof Monster || this.actor instanceof Ork || this.actor instanceof Ghost || this.actor instanceof Boss;
        }

        public boolean isPlayer () {
            return this.actor instanceof Player;
        }
        public boolean isGhost () {
            return this.actor instanceof Ghost;
        }


        public boolean isDoor () {
            return this.type.equals(CellType.DOOR);
        }

        public Item getItem () {
            return item;
        }
    }
