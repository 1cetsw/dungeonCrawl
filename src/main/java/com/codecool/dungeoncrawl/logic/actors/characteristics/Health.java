package com.codecool.dungeoncrawl.logic.actors.characteristics;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static java.lang.Math.floorDiv;
@RequiredArgsConstructor
public class Health {
    @Getter @Setter
    @NonNull
    private Integer health;
    @Getter @Setter
    @NonNull
    private Integer maxHealth;

    public Health(Integer maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public int getPercentage() {
        return Math.floorDiv(health * 100, maxHealth);
    }
    public void modifyHealthPoints(int healthPoints) {
        if ((health + healthPoints) >= maxHealth) {
            health = maxHealth;
        } else if ((health + healthPoints) <= 0) {
            health = 0;
        }
        else health += healthPoints;
    }
    public void modifyMaxHealth(int healthPoints) {
        maxHealth += healthPoints;
    }
}
