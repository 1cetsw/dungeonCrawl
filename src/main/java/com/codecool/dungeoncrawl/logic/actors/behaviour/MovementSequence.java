package com.codecool.dungeoncrawl.logic.actors.behaviour;

public abstract class MovementSequence {
    /** number of cells the character can move per turn */
    private int pace;
    abstract void moveToDestination();
}
