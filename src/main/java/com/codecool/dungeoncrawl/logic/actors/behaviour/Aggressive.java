package com.codecool.dungeoncrawl.logic.actors.behaviour;

import com.codecool.dungeoncrawl.logic.actors.abstraction.Actor;
import com.codecool.dungeoncrawl.logic.strategies.Path;

import java.util.ArrayList;

interface Aggressive {
    ArrayList<Actor> scanForEnemy (int range); // chase an enemy, otherwise if the enemy is out of range, do standard movement sequence
    Path chase(Actor actor);
}
