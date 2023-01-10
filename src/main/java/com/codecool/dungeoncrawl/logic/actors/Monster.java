package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Monster extends Actor {
    public Monster(Cell cell) {
        super(cell);
        maxHealth = 30;
        health = 30;
        strength = 1;
        defense = 1;
    }

    @Override
    public String getTileName() {
        return "monster";
    }
}
