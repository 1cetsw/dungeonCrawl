package com.codecool.dungeoncrawl.logic.items;



import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item {

    private int attack = 10;

    public Sword(Cell cell, String name) {
        super(cell, name);
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}