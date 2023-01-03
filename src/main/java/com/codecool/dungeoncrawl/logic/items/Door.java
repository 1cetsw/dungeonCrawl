package com.codecool.dungeoncrawl.logic.items;


import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Item {
    private int level;

    public Door(Cell cell, String name, int level) {
        super(cell, name);
        this.level = level;
    }
    @Override
    public String getTileName() {
        return "door";
    }
}