package com.codecool.dungeoncrawl.logic.actors.characteristics;

import com.codecool.dungeoncrawl.logic.weaponry.Weapon;

public class Damage {
    private Integer damage;
    public Damage(Integer strength) {

    }
    public Damage(Integer strength, Weapon weapon) {
        if(weapon == null) {

        }
    }
    protected Integer getDamage() {
        return damage;
    }
    protected void updateDamage() {}

}
