package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


public class Ork extends Actor {
    public Ork(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ork";
    }
}
