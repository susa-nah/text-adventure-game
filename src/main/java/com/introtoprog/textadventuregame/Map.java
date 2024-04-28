/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

/**
 *
 * @author susan
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Map {
    /*
    Contains methods for initalising the game map, placing rooms, 
    configuring their navigation, and tracking them.
    */
    private ArrayList<Room> rooms;
    private ArrayList<ArrayList<Integer>> grid;
    
    public Map(ArrayList<Room> rooms) {
        this.rooms = rooms;
        this.grid = new ArrayList<>();
    }
    
    public Map() {
        this.rooms = new ArrayList<>();
        this.grid = new ArrayList<>();
    }
    
    public ArrayList getRooms() {
        return rooms;
    }
    
    public ArrayList<ArrayList<Integer>> getGrid() {
        return this.grid;
    }
    
    public void setGrid() {
        // Create ArrayList row filled with -1.
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            row.add(-1);
        }
        
        // Add new ArrayList for each row.
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> toAdd = new ArrayList<>();
            toAdd.addAll(row);
            grid.add(toAdd);
        }
    }
    
    public void placeRoom(Room room, int row, int col) {
        // Add room to list if not there.
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
        
        // Get room index in list.
        int index = rooms.indexOf(room);
        
        // Create update row.
        ArrayList<Integer> copyRow = grid.get(row);

        // Change coord y to index.
        copyRow.set(col, index);
        
        // Change index x to new row.
        grid.set(row, copyRow);
    }
    
    public int getRoomIndexAt(int row, int col) {
        int index;
        ArrayList<Integer> workingRow;
        // confirm row and column are in range or return null
        if (row < 0 || row > 4 || col < 0 || col > 4) {
            return -1;
        }
        
        // find number at coordinates
        workingRow = grid.get(row);
        index = workingRow.get(col);
        
        // get that number from the room list
        return index;
    }
    
    // Used for debugging when building the story. Not exposed to user.
    public void printGrid() {
        for (ArrayList row: grid) {
            System.out.println(row);
        }
    }
    
    public boolean setRoomNavigation(Room room) {
        Integer index = rooms.indexOf(room);
        int row = -1;
        int column = -1;
        ArrayList<Integer> workingRow;
        int north;
        int south;
        int east;
        int west;
        
        // Iterate through rows to find matching row.
        for (int i = 0; i < 5; i ++) {
            if (grid.get(i).contains(index)) {
                row = i;
            }
        }
        
        // If row isn't found, return false.
        if (row == -1) {
            return false;
        }
        
        // If row is found, get index of search number in column.
        workingRow = grid.get(row);
        column = workingRow.indexOf(index);
        
        // Calculate the relative room positions and get index of room or -1.
        north = getRoomIndexAt(row-1, column);
        south = getRoomIndexAt(row+1, column);
        east = getRoomIndexAt(row, column+1);
        west = getRoomIndexAt(row, column-1);
        
        // Set navigation for the room.
        room.setNavigation(north, south, east, west);
        return true;
    }
}
