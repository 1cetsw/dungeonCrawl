package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Actor {
    public Boss(Cell cell) {
        super(cell);
        maxHealth = 150;
        health = 150;
        strength = 4;
        defense = 5;
    }
    @Override
    public String getTileName() {
        return "boss";
    }
}
