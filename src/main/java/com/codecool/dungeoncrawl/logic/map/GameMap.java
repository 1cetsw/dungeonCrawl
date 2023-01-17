package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.logic.actors.Player;
import lombok.Getter;
import lombok.Setter;

public class GameMap {
    @Getter
    private final int width;
    @Getter
    private final int height;
    private final Cell[][] cells;
    @Getter @Setter
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
