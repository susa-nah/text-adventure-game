/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author susan
 */
public class TestRoom {
    private Room bath = new Room("Bath", "A digny bathroom.");
    
    @Test
    void testRoomConstructorName() {
        assertEquals("Bath", bath.getName(), 
                "Name submitted should be name.");
    }
    
    @Test
    void testRoomConstructorDescription() {
        assertEquals("A digny bathroom.", bath.getDescription(), 
                "Description should be returned by getDescription.");
    }
    
    @Test
    void testNoItemsVisibleOnInitialisation() {
        assertEquals(0, bath.getVisibleObjects().size(), "Initialised list should be empty.");
    }
    
    @Test
    void testNoItemsHiddenOnInitialisation() {
        assertEquals(0, bath.getHiddenObjects().size(), "Initialised list should be empty.");
    }
}
