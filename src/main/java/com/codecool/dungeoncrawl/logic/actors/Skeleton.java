package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.actors.abstraction.Actor;
import com.codecool.dungeoncrawl.logic.map.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
