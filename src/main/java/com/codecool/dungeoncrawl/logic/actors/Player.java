package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.actors.abstraction.Fighter;
import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.actors.characteristics.Damage;

public class Player extends Fighter {
    private Integer strength;
    private Damage damage;
    Float accuracy; // the more experienced the player is with a certain kind of weapon the more accurately can use it and there are more chances for him to perform a max damage.

    public Player(Cell cell) {
        super(cell);
        this.strength = 10;
        this.damage = new Damage(strength);
    }
    public Player(Cell cell, Integer strength) {
        super(cell);
        this.strength = strength;
        this.damage = new Damage(strength);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public Float getAccuracy() {
        return null; // the more experience in fight and a given type of Weapon a player has, and the lighter the armor is, the more accurate the player can be.
    }


}
