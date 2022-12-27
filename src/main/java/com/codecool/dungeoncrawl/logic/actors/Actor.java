package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private String name;
    private Cell cell;
    private ArrayList<Item> inventory = new ArrayList<>();

    protected int maxHealth = 100;
    protected int maxDefense = 100;
    protected int health = 100;
    protected int strength = 0;
    protected int defense = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);

        //for nickname "godmode" walk in wall and max ATK , DEF and HP
        if (godMode()) {
            this.maxHealth = 999;
            this.health = 999;
            this.strength = 999;
            this.defense = 999;
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // player movement restrictions
        if (cell.isPlayer() && !nextCell.isEnemy() && nextCell.goingThrough()) {
        }
        // enemies movement restrictions
        if (nextCell.goingThrough() && !nextCell.isPlayer() && !nextCell.isEnemy()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        // ghost movement no restrictions TODO block area errors
        if (cell.isGhost() && !nextCell.isPlayer() && !nextCell.isEnemy() && nextCell.ghostMode()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


    public boolean godMode() {//if player name "godmode" the player can walk through walls
        return (cell.isPlayer() && (this.name.equals("godmode")));
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void pickUpItem(Item item) {//TODO  Disable the ability to pick up items when I have a full state
        this.inventory.add(item);
        if (item instanceof AidKit) { //OK restrictions 100/100 and add HP its working
            health += ((AidKit) item).getAidkit();
            if (health >= maxHealth) health = maxHealth;
            }
            if (item instanceof Armor) {//OK restrictions 100/100 and add DEF its working
                defense += ((Armor) item).getDefense();
                if (defense >= maxDefense) defense = maxDefense;
            }
            if (item instanceof Sword) {//OK no-restrictions and add ATT its working
                strength += ((Sword) item).getAttack();
            }
            item.getCell().setType(CellType.FLOOR); // TODO delete an item from memory,You can pick up an item all the time

        }


    public String itemInInventory() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this.inventory) {
            sb.append(item.getName()).append(",\n ");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }


    public boolean isOpen() {

        boolean isKey = false;
        for (Item item : inventory) {
            isKey = item instanceof Key || godMode();
        }
        System.out.println(isKey);
        return isKey;
    }



}