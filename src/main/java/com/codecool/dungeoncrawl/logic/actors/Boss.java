package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Actor {
    public Boss(Cell cell) {
        super(cell);
        maxHealth -= 50;
        health -= 50;
        strength -= 4;
    }
    @Override
    public String getTileName() {
        return "boss";
    }
}
