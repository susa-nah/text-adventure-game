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
    @CsvSource({"Look inside barrel,LOOK INSIDE", 
        "Examine inside barrel,EXAMINE", 
        "X barrel,EXAMINE", 
        "go north,NORTH",
        "n,NORTH",
        "look at barrel,LOOK",
        ","})
    void testParseCommand(String input, String expected) {
        String output = ui.parseCommand(input);
        assertEquals(expected, output);
    }
    
}
