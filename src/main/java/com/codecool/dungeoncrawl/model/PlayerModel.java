package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.Collections;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int def;
    private int att;

    private int x;
    private int y;
    private List<String> inventory;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();

        this.hp = player.getHealth();

    }

    public PlayerModel(String string) {
        super();
    }

    public PlayerModel(Player player, String savedGameName) {
        super();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }
    public int getDef() {
        return def;
    }
    public int getAtt() {
        return att;
    }

    public void setDef(int def) {
        this.def = def;
    }
    public void setAtt(int att) {
        this.att = att;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setInventory(String inventory) {
        this.inventory = Collections.singletonList(inventory);
    }
}
