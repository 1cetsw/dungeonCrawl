package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        maxHealth = 100;
        health =100;
        strength = 7;
        defense = 5;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}