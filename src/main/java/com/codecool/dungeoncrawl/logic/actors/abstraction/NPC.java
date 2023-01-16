package com.codecool.dungeoncrawl.logic.actors.abstraction;

import com.codecool.dungeoncrawl.logic.map.Cell;
import java.util.ArrayList;

public abstract class NPC extends Actor {
    ArrayList<String> story;
    ArrayList<Items> gifts;
    String name;

    public NPC(Cell cell, String name, ) {
        super(cell);
        this.name = name;

    }
}
