package com.codecool.dungeoncrawl.logic.strategies;
import com.codecool.dungeoncrawl.logic.GameElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Path {
    final GameElement departurePoint;
    final GameElement destination;
    final Integer maximumAllowedRange;

}
