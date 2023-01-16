package com.codecool.dungeoncrawl.logic.strategies;

import com.codecool.dungeoncrawl.logic.GameElement;
import com.codecool.dungeoncrawl.logic.map.Cell;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ShortestPath extends Path {
    @Getter @Setter
    Integer maximumAllowedLength;

    public ShortestPath(GameElement departurePoint, GameElement destination, Integer maximumAllowedRange) {
        super(departurePoint, destination, maximumAllowedRange);
    }

    class Node {
        Cell cell;
    }
    private List<Node> nodeList;
}
