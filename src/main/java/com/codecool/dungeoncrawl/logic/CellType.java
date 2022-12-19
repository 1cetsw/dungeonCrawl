package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    GRASS("grass"),
    ICE("ice"),
    WALL("wall"),
    ENEMY("enemy"),
    KEY("key"),
    WEAPON("weapon"),
    BOSS("boss"),
    POTION("potion"),
    PLAYER("player"),
    DOOR("door");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
