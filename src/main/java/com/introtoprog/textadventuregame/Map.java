/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

/**
 * Map is the class that controls the location of Rooms and their
 * relationships.
 * 
 * @author - Susannah Bourke
 */

public class Map {
	/**
	 * Current location on the map
	 */
	private int currentRoom;
	/**
	 * The array of possible locations
	 */
	private Room[] map;
	/**
	 * An array of labels used when generating directions to display to users
	 */
	private String[] labels;

	/**
	 * Simple constructor for Map class 5 x 5 grid
	 * Defaults to North, South, East, West as direction labels
	 */
	public Map() {
		this.currentRoom = -1;
		this.map = new Room[26];
		this.labels = new String[] { "North", "South", "East", "West" };
	}

	/**
	 * Constructor for supplying custom direction labels
	 * @param northLabel display string for north direction
	 * @param southLabel display string for south direction
	 * @param eastLabel display string for east direction
	 * @param westLabel display string for west direction
	 */
	public Map(String northLabel, String southLabel, String eastLabel, String westLabel) {
		this.currentRoom = -1;
		this.map = new Room[26];
		this.labels = new String[] { northLabel, southLabel, eastLabel, westLabel };
	}

	/**
	 * Get index of current place
	 * @return index of the current place
	 */
	public int getCurrentIndex() {
		return currentRoom;
	}
	
	/**
	 * Get Room at current map index
	 * @ return Room at current index
	 */
	public Room getCurrentRoom() {
		return map[currentRoom];
	}

	/**
	 * Set index of current place if it is valid
	 * @param index the new map location
	 */
	public void setCurrentRoom(int index) {
		if (isValidIndex(index)) {
			currentRoom = index;
		}
	}

	/**
	 * Assign a Room to a map index
	 * @param place a game location
	 * @param index location to put place
	 */
	public void setRoom(Room place, int index) {
		if (isValidIndex(index)) {
			map[index] = place;
		}
	}

	/**
	 * Get place at map location
	 * @param index location of place
	 * @return the location at this location on the map
	 */
	public Room getRoomAtIndex(int index) {
		return map[index];
	}

	/**
	 * Get all places on map
	 * @return array of 25 map locations including places and nulls for unassigned indexes
	 */
	public Room[] getRooms() {
		return map;
	}

	/**
	 * Checks if index is valid for a 5 x 5 grid
	 * @param x index to be checked
	 * @return true if 0 or greater but less than 25
	 */
	public boolean isValidIndex(int x) {
		return (x >= 0 && x < 25);
	}

	// Get [row, column] coordinates from int x.
	/**
	 * Calculate row and column coordinates for any index
	 * @param x index map location index
	 * @return returns row and column unless index is invalid, which returns -1 for both
	 */
	public int[] getCoords(int x) {
		int row;
		int col;

		// Check that index is on the grid
		if (!isValidIndex(x)) {
			return new int[] { -1, -1 };
		}

		// Avoid dividing by zero
		if (x == 0) {
			return new int[] { 0, 0 };
		}

		// Uses integer division and remainder to determine row and column
		row = x / 5;
		col = x % 5;

		return new int[] { row, col };
	}

	/**
	 * Calculate new index based on direction
	 * @param currentIndex map index to move from
	 * @param direction direction of movement either 'N', 'S', 'E', or 'W'
	 * @return new map index if valid, otherwise -1
	 */
	public int getDirectionIndex(int currentIndex, char direction) {
		int newIndex;

		// Check if new integer will still be on the board when moving East or West.
		if ((direction == 'W' && currentIndex % 5 == 0) || (direction == 'E' && (currentIndex + 1) % 5 == 0)) {
			return -1;
		}

		// Calculate new index based on direction.
		switch (direction) {
		case 'N':
			newIndex = currentIndex - 5;
			break;
		case 'S':
			newIndex = currentIndex + 5;
			break;
		case 'E':
			newIndex = currentIndex + 1;
			break;
		case 'W':
			newIndex = currentIndex - 1;
			break;
		default:
			newIndex = -1;
		}

		// Check index is still on the board for North/South and has a value at the index
		if (newIndex < 0 || newIndex > 24) {
			return -1;
		} else if (map[newIndex] == null) {
			return -1;
		} else {
			return newIndex;
		}
	}

	/**
	 * Calculate new index based on direction and map current index
	 * @param direction direction of movement either 'N', 'S', 'E', or 'W'
	 * @return new map index if valid, otherwise -1
	 */
	public int getDirectionIndex(char direction) {
		return getDirectionIndex(currentRoom, direction);
	}

	/**
	 * Add String to array at first null index and return the array
	 * @param length number of items to be added to array
	 * @param array current array
	 * @param toAdd item to be added to array
	 * @return array with new item added to first empty index
	 */
	public String[] addListItem(int length, String[] array, String toAdd) {
		for (int i = 0; i < length; i++) {
			if (array[i] == null) {
				array[i] = toAdd;
				return array;
			}
		}
		return array;
	}

	/**
	 * Create a human readable list of valid directions around a location
	 * @return string containing direction labels separated with commas
	 */
	public String getDirections() {
		return getDirections(this.currentRoom);
	}

	/**
	 * Create a human readable list of valid directions around a location
	 * @param currentIndex index on the map to generate directions for
	 * @return string containing direction labels separated with commas
	 */
	public String getDirections(int currentIndex) {
		String[] directions;
		int countValid = 0;
		boolean[] hasDirection = new boolean[4];
		char[] nsew = new char[] { 'N', 'S', 'E', 'W' };

		// Check if there is a room at the query index. Note that list will need to have
		// 26 items so -1 doesn't call the last one.
		for (int i = 0; i < 4; i++) {
			int localIndex = getDirectionIndex(currentIndex, nsew[i]);
			// change local index to last item on list if -1
			if (localIndex == -1) {
				localIndex = map.length - 1;
			}
			// Checks if the index is not null.
			hasDirection[i] = (map[localIndex] != null);
		}

		// Counts the number of valid indexes
		for (int i = 0; i < 4; i++) {
			if (hasDirection[i]) {
				countValid++;
			}
		}
		
		// If there is nowhere to go
		if (countValid == 0) {
			return null;
		}

		// Create empty output String of the same length as the number of valid directions
		directions = new String[countValid];

		// Check each index in the list to see if it is valid, and add if so
		if (hasDirection[0]) {
			directions = addListItem(countValid, directions, this.labels[0]);
		}
		if (hasDirection[1]) {
			directions = addListItem(countValid, directions, this.labels[1]);
		}
		if (hasDirection[2]) {
			directions = addListItem(countValid, directions, this.labels[2]);
		}
		if (hasDirection[3]) {
			directions = addListItem(countValid, directions, this.labels[3]);
		}

		// Create a comma separated String for output
		return String.join(", ", directions);
	}
}
