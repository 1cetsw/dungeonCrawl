package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.actors.Ork;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.items.AidKit;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS);
                            break;
                        case '%':
                            cell.setType(CellType.ICE);
                            break;
                        case 'm':
                            cell.setType(CellType.MONSTER);
                            new Monster(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.ORK);
                            new Ork(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.BOSS);
                            new Boss(cell);
                            break;
                        case 's':
                            cell.setType(CellType.SWORD);
                            new Sword(cell, "Sword Excalibur");
                            break;
                        case 'a':
                            cell.setType(CellType.ARMOR);
                            new Armor(cell, "Armor");
                            break;
                        case '+':
                            cell.setType(CellType.AIDKIT);
                            new AidKit(cell, "Aid Kit");
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell, "Gold key", 1);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            new Key(cell, "Door", 1);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
