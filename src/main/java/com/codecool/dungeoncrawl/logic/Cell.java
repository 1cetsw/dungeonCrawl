package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;

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

    /** getNeighbor method tries to fetch a cell from the GameMap object that is neighbor to the Cell object on
     * which the method is being called. gameMap.getCell(x + dx, y + dy) will return the Cell only if it exists and
     * belongs to the array of Cells contained by the GameMap Object. Otherwise, an ArrayIndexOutOfBound exception
     * is thrown by the getCell(x + dx, y + dy) method in a GameMap object. This exception is being caught here and
     * a null value is returned as a result of that caught, which can be used by the Actors as a feedback on the
     * existence of the neighbor cell.
     */
    public Cell getNeighbor(int dx, int dy) {

         try {
             return gameMap.getCell(x + dx, y + dy);

         }
         catch (IndexOutOfBoundsException e) {
             return null;
         }

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
