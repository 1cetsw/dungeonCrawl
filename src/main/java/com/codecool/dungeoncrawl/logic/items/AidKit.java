package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class AidKit extends Item {

    private int health = 5;

    public AidKit(Cell cell, String name) {
        super(cell, name);
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String getTileName() {
        return "aidkit";
    }
}