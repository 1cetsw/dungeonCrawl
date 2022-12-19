# dungeonCrawl
Tasks
0%
SHOW ALL CRITERIA
Analyze the project
Understand the existing code, classes, and tests so you can make changes. Do this before planning anything else. It helps you understand what is going on.

A class diagram is created in a digialized format which contains the following.

enums, classes, interfaces with all fields, methods
connections between classes: inheritance, aggregation, composition
multiplicity of connections (1..1, 1..*, ..)
CRITERIA
Restrict movement
Create a game logic which restricts the movement of the player so they cannot run into walls and monsters.

The hero is not able to move into walls.

The hero is not able to move into monsters.

CRITERIA
Dungeon items
There are items lying around the dungeon. They are visible in the GUI.

There are at least two item types, such as a key and a sword.

There can be one item in a map square.

A player can stand on the same square as an item.

The item must be displayed on screen (unless somebody stands on the same square).

CRITERIA
Pick up items
Create a feature that allows the hero to pick up an item.

There is a "Pick up" button on the right side of screen.

After the player clicks the button, the item the hero is standing on is gone from map.

CRITERIA
Show picked up items
Show picked up items in the inventory list.

There is an Inventory list on the screen.

After the hero picks up an item, it appears in the inventory.

CRITERIA
Attack monsters
Make the hero able to attack monsters by moving into them.

Attacking a monster removes 5 health points. If the health of a monster goes below 0, it dies and disappears.

If the hero attacks a monster and it does not die, it also attacks the hero at the same time (it only removes 2 health points).

Having a weapon increases attack strength.

Different monsters have different health and attack strengths.

CRITERIA
Doors and keys
Create doors in the dungeon that are opened using keys.

There are two new square types, closed door and open door.

The hero cannot pass through a closed door unless there is a key in the inventory. After moving through, the closed door becomes an open door.

CRITERIA
Different monsters
Create three different monster types with different behaviors.

There are at least three different monster types with different behaviors.

One type of monster does not move at all.

One type of monster moves randomly. It cannot go trough walls or open doors.

CRITERIA
OPTIONAL TASK:
Better movement AI
Create a more sophisticated movement AI.

One type of monster moves around randomly and teleports to a random free square every few turns.

A custom movement logic is implemented (such as Ghosts that can move trough walls, or a monster that chases the player).

CRITERIA
More map features
Create maps that have more varied scenery. Take a look at the tile sheet (tiles.png). Get inspired!

At least three more tiles are used. These can be more monsters, items, or background. At least one of them must be not purely decorative, but have some effect on gameplay.

CRITERIA
OPTIONAL TASK:
Character name
Allow the player to set a name for the character. This name can also function as a cheat code.

There is a Name label and text field on the screen.

If the name given is one of the game developers' name, the player can walk through walls.

CRITERIA
OPTIONAL TASK:
Sounds
Make the game sound fun by implementing audio effects, played when the player or enemies do stuff.

There is a footstep sound that plays whenever the player takes a step.

There is an attack sound whenever the player or an enemy attacks someone This sound might vary depending on the weapon (sword, axe, arrow).

Enemies, such as skeletons or ghosts, play characteristic sounds randomly every few seconds.

Some background music is added to the game.

CRITERIA
Implement more levels
Add the possibility to add more levels.

There are at least two levels.

There is a square type "stairs down". Entering this square moves the player to a different map.

CRITERIA
Levels are bigger than the window.
Implement bigger levels than the game window.

Levels are larger than the game window (for example three screens wide and three screens tall).

When the player moves, the player character stays in the center of the view.

CRITERIA
Hints
Start with the smaller tasks, and then move into the more difficult ones.

Make sure you understand the whole starting code before making any changes.

Open the project in IntelliJ IDEA. This is a Maven project, so you need to open pom.xml. The project uses JavaFX, so use the javafx Maven plugin to build and run the program. Build using mvn javafx:compile, and run using mvn javafx:run.

Adding a button on the sidebar will disable the onKeyPressed of the Scene unless you set the button with setFocusTraversable(false)

Do not delve into JavaFX technicalities too much, most of the GUI is ready.
