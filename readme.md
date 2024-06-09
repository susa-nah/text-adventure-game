# Overview

Building a text based adventure game from scratch in Java for fun and profit.

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

Have a name, description, and two booleans for if they can be picked up by the user, and if they are highlighted objects in Menus. This allows for creating more depth so the user can try X NOUN and if the noun is not highlighted it will still display the description.

### Rooms

Room objects have a name and description, ArrayLists of visibleObjects and hiddenObjects, and a locked status boolean. 

### Map

Map object builds a 5 x 5 grid based on a single array object, on which Rooms are placed. 
Moves on the grid are calculated based on valid indexes (on the grid, not null). 

The edges of the Map are calcualted using: 

    (direction == 'W' && currentIndex % 5 == 0) || (direction == 'E' && (currentIndex + 1) % 5 == 0)

The top and bottom of the map are calculated using:

    newIndex < 0 || newIndex > 24

Then checks if the index is null.

Map also handles the generation of available direction strings to the user, eg. "North, South, West". This uses an array rather than an ArrayList to play with using methods to create array based on size.

Outstanding problem:

- How to handle adjacent map indexes that have no door between them.
- Considered also checking if the location is locked, however this seems desirable to display to the user. This would provide a way to control adjacent rooms however.


### Player

Not sure this class is necessary? Player could be implicit in the UI.

- Inventory
- Pickup and drop methods.

### User interface

- Scanner object.
- Parse user input into commands and objects.

### CommandParser

A class that is configured with two ArrayList objects: 

- special cases (2+ word commands, eg. Look inside) and 
- prefixes (words put in front of commands without more meaning, eg. Go) 

Based on the config it returns a command string and/or an object string.

### Achievements

Not sure how to implement these yet.
- Plot dependent things that are fulfilled by user actions.
- Touch the husk
- Swim

Considering:
- GameState class: has `addAchievement(String)` method, controls win and lose conditions. Has many booleans.
    - How would this be configured to match the script?
- Simple ArrayList object in the UI. Set with basic achievements, these are overwritten by other achievements.

### Actions

Not sure if these should be their own class, or just methods in the objects, or methods in the UI. 
Last attempt used `Switch` statements on command tokens (another `Switch` to normalise commands), however this required special loops for locations with character interaction or other conditions.
Could potentially work if a `Character` class was used to keep track of character interaction. (Character state booleans, scripts)