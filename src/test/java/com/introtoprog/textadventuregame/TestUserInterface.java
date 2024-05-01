/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author susan
 */
public class TestUserInterface {
    // Setup print tests
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final Map map = new Map();
    private final Room testRoom = new Room("Limbo", "An ominous blank space before Story initialisation.");;
    private final Room secondRoom = new Room("Bedroom", "A spacious bedroom with a fluffy bed.");
    private final Item key = new Item("Key", "A small silver key.", true);
    private String nl = System.lineSeparator();
    
        @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        map.placeRoom(testRoom, 2, 3);
        map.placeRoom(secondRoom, 3, 3);
        map.setRoomNavigation(testRoom);
        map.setRoomNavigation(secondRoom);
    }
    
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    // Create test class
    UserInterface ui = new UserInterface();
    
    /*
    Test command parsing
    */
    @ParameterizedTest
    @CsvSource({"Look inside barrel,11", 
        "Examine inside barrel,7", 
        "X barrel,1", 
        "go north,8",
        "go east,7",
        "go west east,7",
        "n,0",
        "look at barrel,4",
        ",-1",
        " ,-1",
        "x white shirt,1"})
    void testGetSplitIndex(String input, int expected) {
        int output = ui.getSplitIndex(input);
        assertEquals(expected, output);
    }
    
    @ParameterizedTest
    @CsvSource({"Look inside barrel,LOOK INSIDE", 
        "Examine inside barrel,EXAMINE", 
        "X barrel,EXAMINE", 
        "go north,NORTH",
        "go east,EAST",
        "go west east,WEST",
        "s,SOUTH",
        "n,NORTH",
        "look at barrel,LOOK",
        ",",
        " ,",
        "xyz,XYZ",
        "help,HELP",
        "x white shirt,EXAMINE",})
    void testParseCommand(String input, String expected) {
        String output = ui.parseCommand(input);
        assertEquals(expected, output);
    }
    
    @ParameterizedTest
    @CsvSource({"Look inside barrel,BARREL", 
        "Examine inside barrel,BARREL", 
        "X barrel,BARREL", 
        "go north, ",
        "go east, ",
        "go west east,EAST",
        "x white shirt,WHITE SHIRT",
        "n,",
        "look at barrel,BARREL",
        ",",
        " ,",
        "xyz,",
        "help me,ME"})
    void testParseObject(String input, String expected) {
        String output = ui.parseObject(input);
        assertEquals(expected, output);
    }
    
    @ParameterizedTest
    @CsvSource({"LOOK INSIDE,true", 
        "EXAMINE,true", 
        "INSIDE,false", 
        "GO,false",
        "EAST,true",
        "WEST,true",
        "SOUTH,true",
        "FIND,false",
        "LOOK,true",
        ",false",
        " ,false",
        "XYZ,false",
        "HELP,true",
        "x white shirt,false",})
    void testIsValidCommand(String input, String expected) {
        boolean outcome = ui.isValidCommand(input);
        assertEquals(expected, String.valueOf(outcome));
    }
    
    /*
    Test simple return objects.
    */
    @Test
    void testLookAtRoom(){
        ui.lookAction(testRoom);
        String expected = "Limbo\n"
                + "An ominous blank space before Story initialisation.\n"
                + "Directions: South"+nl;
        assertEquals(expected, outContent.toString());
    }
    
    @Test
    void testLookAtItem(){
        ui.lookAction(key);
        String expected = "A small silver key."+nl;
        assertEquals(expected, outContent.toString());
    }
    
}
