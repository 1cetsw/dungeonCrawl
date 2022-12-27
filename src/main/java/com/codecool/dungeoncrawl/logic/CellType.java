package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    GRASS("grass"),
    ICE("ice"),
    WALL("wall"),
    ORK("ork"),
    GHOST("ork"),
    MONSTER("monster"),
    BOSS("boss"),
    KEY("key"),
    SWORD("sword"),
    ARMOR("armor"),
    POTION("potion"),
    PLAYER("player"),
    DOOR("door"),
    AIDKIT("aidkit");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
