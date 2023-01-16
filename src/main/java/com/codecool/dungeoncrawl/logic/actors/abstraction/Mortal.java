package com.codecool.dungeoncrawl.logic.actors.abstraction;

import com.codecool.dungeoncrawl.logic.actors.characteristics.Health;
import com.codecool.dungeoncrawl.logic.map.Cell;
import lombok.Getter;
import lombok.Setter;

public abstract class Mortal extends Actor {
    public Mortal(Cell cell) {
        super(cell);
    }
    public Mortal(Cell cell, Integer health, Integer maxHealth) {
        super(cell);
        this.health = new Health(health, maxHealth);
    }
    @Getter @Setter
    Health health;

    void modifyHealthPoints(int healthPoints) {
        health.modifyHealthPoints(healthPoints);
    }
    void temporarilyModifyMaxHealth(int numberOfTurns, int maxHealthModifier) {
    };
}
