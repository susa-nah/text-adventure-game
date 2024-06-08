/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author susan
 */
public class TestMap {
    // create Map.
    private  Map map = new Map("Up","Down","Right","Left");
    
    @BeforeEach
    void setupMap() {
    	map.setRoom(new Room("1", "Description of place 1"), 1);
		map.setRoom(new Room("2", "Description of place 2"), 2);
		map.setRoom(new Room("3", "Description of place 3"), 3);
		map.setRoom(new Room("7", "Description of place 7"), 7);
		map.setRoom(new Room("8", "Description of place 8"), 8);
		map.setRoom(new Room("11", "Description of place 11"), 11);
		map.setRoom(new Room("12", "Description of place 12"), 12);
		map.setRoom(new Room("17", "Description of place 17"), 17);
		map.setRoom(new Room("18", "Description of place 18"), 18);
		map.setRoom(new Room("19", "Description of place 19"), 19);
		map.setRoom(new Room("21", "Description of place 21"), 21);
		map.setRoom(new Room("22", "Description of place 22"), 22);

		map.setCurrentRoom(1);
    }

    
    /*
    test test map process
    */
    @ParameterizedTest
    @CsvSource({"0,true", 
        "25,false", 
        "-3,false", 
        "26,false",
        "8,true"})
    void testIsValidIndex(int x, boolean expected) {
        boolean result = map.isValidIndex(x);
        assertEquals(expected, result);
    }
    
    @ParameterizedTest
    @CsvSource({"0,0,0", 
        "24,4,4", 
        "-3,-1,-1", 
        "12,2,2",
        "8,1,3"})
    void testGetCoordinates(int x, int e1, int e2) {
        int[] expected = new int[]{e1, e2};
        int[] outcome = map.getCoords(x);
        assertTrue(Arrays.equals(expected, outcome));
    }
    
    @ParameterizedTest
    @CsvSource({"0,N,-1",
        "0,S,-1", //empty room case
        "24,N,19",
        "24,E,-1",
        "16,E,17", 
        "16,S,21",
        "15,W,-1",
        "14,E,-1"})
    void testGetDirectionIndex(int current, char direction, int expected) {
        int outcome = map.getDirectionIndex(current, direction);
        assertEquals(expected, outcome);
    }
    
    @ParameterizedTest
    @CsvSource({"0,Right",
    	"-1,",
        "1,Right",
        "2,Down Right Left",
        "7,Up Down Right",
        "19,Left", 
        "18, Right Left",
        "12,Up Down Left",
        "22, Up Left"})
    void testGetDirections(int currentIndex, String expected) {
    	if (expected == null) {
    		
    	} else if (expected.contains(" ")) {
    		String[] parts = expected.split(" ");
    		expected = String.join(", ", parts);
    	}
    	
    	String outcome = map.getDirections(currentIndex);
    	assertEquals(expected, outcome);
    }
}