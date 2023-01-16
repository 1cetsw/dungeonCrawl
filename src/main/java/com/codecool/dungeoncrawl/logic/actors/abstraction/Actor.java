package com.codecool.dungeoncrawl.logic.actors.abstraction;

import com.codecool.dungeoncrawl.logic.GameElement;
import com.codecool.dungeoncrawl.logic.map.Cell;

public abstract class Actor extends GameElement {
    private Cell cell;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }


    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
