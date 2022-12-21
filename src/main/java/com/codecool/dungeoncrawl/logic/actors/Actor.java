package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private String name;
    private Cell cell;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);

        if (godMode()) { //for nickname "godmode" walk in wall
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        // player movement restrictions
        if (cell.isPlayer() && !nextCell.isEnemy() && nextCell.goingThrough()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // enemies movement restrictions
        if (nextCell.goingThrough() && !nextCell.isPlayer() && !nextCell.isEnemy()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // ghost movement no restrictions TODO block area errors
        if (cell.isGhost() && !nextCell.isEnemy() && nextCell.ghostMode()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


    public boolean godMode() {//if player name "godmode" the player can walk through walls
        return (cell.isPlayer() && (this.name.equals("godmode")));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
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
