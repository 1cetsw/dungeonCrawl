package com.codecool.dungeoncrawl.logic.items;



import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item {

    private int strength = 50;

    public Sword(Cell cell, String name) {
        super(cell, name);
    }

    public int getAttack() {
        return strength;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}