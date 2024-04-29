/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author susan
 */
public class TestMap {
    
    /*
    Setup test data
    */
    private Room bath = new Room("Bath", "A digny bathroom.");
    private Room bedroom = new Room("Bedroom", "A small bedroom with a double bed and dresser.");
    private Room closet = new Room("Closet", "A small closet.", true);
    private Room hall = new Room("Hallway", "A long and narrow corridoor.");
    private Room living = new Room("Living room", "A dimly lit living room with a broken television, couch, and table.");
    private Room porch = new Room("Porch", "A narrow rectangle of concrete wrapped in cast iron.");
    
    private String newline = System.lineSeparator();
    // output data
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    // create Map.
    private  Map gameMap = new Map();
    

    
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    // Set grid
    @Test
    void testSetGridIsNegative() {
        gameMap.printGrid();
        String expected = "[-1, -1, -1, -1, -1]" + newline 
                + "[-1, -1, -1, -1, -1]" + newline + "[-1, -1, -1, -1, -1]" + newline 
                + "[-1, -1, -1, -1, -1]" + newline + "[-1, -1, -1, -1, -1]" + newline;
        assertEquals(expected, outContent.toString(),"Grid should print 5 arrays of -1.");
    }
    
    // place room
    @Test
    void testAddRoomsToGrid() {
        gameMap.placeRoom(bath, 1, 3); // index 0
        gameMap.placeRoom(bedroom, 1, 2); // index 1
        gameMap.placeRoom(closet, 2, 3); // index 2
        gameMap.placeRoom(hall, 1, 1); // index 3
        gameMap.placeRoom(living, 2, 1); // index 4
        gameMap.placeRoom(porch, 0, 2); // index 5
        gameMap.printGrid();
        String expected = "[-1, -1, 5, -1, -1]" + newline 
                + "[-1, 3, 1, 0, -1]" + newline + "[-1, 4, -1, 2, -1]" + newline 
                + "[-1, -1, -1, -1, -1]" + newline + "[-1, -1, -1, -1, -1]" + newline;
        assertEquals(expected, outContent.toString(),"Failed to correctly place rooms in grid.");
    }
    
    @Test
    void testGetRoomIndexFromCoord() {
        gameMap.placeRoom(bath, 1, 3); // index 0
        gameMap.placeRoom(bedroom, 1, 2); // index 1
        gameMap.placeRoom(closet, 2, 3); // index 2
        gameMap.placeRoom(hall, 1, 1); // index 3
        gameMap.placeRoom(living, 2, 1); // index 4
        gameMap.placeRoom(porch, 0, 2); // index 5
        int index = gameMap.getRoomIndexAt(2, 3);
        assertEquals(2, index, "Room index should be retrieved by coordinates.");
    }
    
    // set room navigation
    @Test
    void testSetRoomNavigation() {
        // Set rooms
        gameMap.placeRoom(bath, 1, 3); // index 0
        gameMap.placeRoom(bedroom, 1, 2); // index 1
        gameMap.placeRoom(closet, 2, 3); // index 2
        gameMap.placeRoom(hall, 1, 1); // index 3
        gameMap.placeRoom(living, 2, 1); // index 4
        gameMap.placeRoom(porch, 0, 2); // index 5
        // Get room list
        ArrayList<Room> rooms = gameMap.getRooms();
        // Set navigation for closet.
        gameMap.setRoomNavigation(closet);
        gameMap.setRoomNavigation(bedroom);
        String expected = "[0, -1, -1, -1]";
        String expectedBed = "[5, -1, 0, 3]";
        assertEquals(expected, Arrays.toString(closet.getNavigation()));
        assertEquals(expectedBed, Arrays.toString(bedroom.getNavigation()));
    }
}
