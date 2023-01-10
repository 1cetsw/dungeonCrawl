package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {
    private int level;
    public Key(Cell cell, String name, int level) {
        super(cell, name);
        this.level = level;
    }
    @Override
    public String getTileName() {
        return "key";
    }
}