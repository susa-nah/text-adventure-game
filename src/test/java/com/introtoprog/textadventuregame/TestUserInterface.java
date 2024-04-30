/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author susan
 */
public class TestUserInterface {
    UserInterface ui = new UserInterface();
    
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
    
}
