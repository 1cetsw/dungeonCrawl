package com.codecool.dungeoncrawl.logic.items;



import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item {

    private int defense = 5;

    public Armor(Cell cell, String name) {
        super(cell, name);
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}