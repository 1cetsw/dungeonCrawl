package com.codecool.dungeoncrawl.logic.actors.abstraction;

import com.codecool.dungeoncrawl.logic.map.Cell;
import com.codecool.dungeoncrawl.logic.weaponry.Weapon;
import lombok.Getter;
import lombok.Setter;

public abstract class Fighter extends Mortal {
    @Setter @Getter
    private Weapon equippedWeapon;
    private Integer baseDamage;
    private Integer damage;
    private Float accuracy;

    public Integer getBaseDamage() {
        return baseDamage;
    }


    public abstract String getTileName();

    protected Integer getDamage() {
        if (equippedWeapon != null) {
            return baseDamage + equippedWeapon.getDamageModifier();
        } else {
            return baseDamage;
        }
    }
    Float getAccuracy() {
    return accuracy;
        };
    void updateDamage() {}; // if the player takes new weapon or gets poisoned, the damage he can make should be updated
    protected void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
}
