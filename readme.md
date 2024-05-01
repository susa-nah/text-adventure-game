# Overview

ABuilding a text based adventure game from scratch in Java for fun and profit.

**Requirements**

- Game process that can be flexibly adapted for different stories so I don't have to decide the story now.
- Game documentation.
- Unit tests for everything.
- ASCII art?

## The story

- You wake up in a Hotel and don't know where you are or how you got there.
- The hotel has an indoor pool.
- There is a mysterious husk.

## Programming problems

### Locations

Require
- Description
- Menu (Exits, You seee:)
- Object inventory
- NPC inventory
- Location on the map
- Interface / actions.

### Items

Have a name, description, and two booleans for if they can be picked up by the user, and if they are highlighted objects in Menus.

### Rooms

Room objects have a name and description, ArrayLists of visibleObjects and hiddenObjects, a locked status boolean, and a Navigation object storing the locations of other rooms.

### Map

Map object builds a 5 x 5 grid of ArrayList objects that are set to -1. 
Rooms are added to an ArrayList and their index is placed on the grid location.
The index of room in the Map object is set into the Room object, with indexes of other rooms being stored in a Navigation object within the room.

### Player

- Inventory
- Pickup and drop methods.

### User interface

- Scanner object.
- Parse user input into commands and objects.

### Achievements

Not sure how to implement these yet.
- Plot dependent things that are fulfilled by user actions.
- Touch the husk
- Swim

### Actions

Not sure if these should be their own class, or just methods in the objects, or methods in the UI. 