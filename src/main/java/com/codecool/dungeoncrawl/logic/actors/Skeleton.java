package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.actors.abstraction.Fighter;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.actors.characteristics.Damage;

public class Skeleton extends Fighter {
    private Damage damage;
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public Float getAccuracy() {
        return null;
    }

}
