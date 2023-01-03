package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class AidKit extends Item {

    private int aidkit = 10;

    public AidKit(Cell cell, String name) {
        super(cell, name);
    }

    public int getAidkit() {
        return aidkit;
    }

    @Override
    public String getTileName() {
        return "aidkit";
    }
}