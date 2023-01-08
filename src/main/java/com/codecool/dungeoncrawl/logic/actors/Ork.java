package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Ork extends Actor {
    public Ork(Cell cell) {
        super(cell);
        maxHealth = 20;
        health = 20;
        strength += 2;
    }

    @Override
    public String getTileName() {
        return "ork";
    }
}
