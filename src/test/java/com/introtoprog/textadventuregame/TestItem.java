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
public class TestItem {
    // Test items.
    Item key = new Item("Key", "A small silver key.", true);
    Item sink = new Item("Sink", "A oval basin with taps.", false);
    
    /*
    Test constructor
    */
    @Test
    void testGetName() {
        assertEquals("Key", key.getName(), "GetName should return value passed to constructor.");
    }
    
    @Test
    void testGetDescription() {
        assertEquals("A small silver key.", key.getDescription(), 
                "getDescription should return value passed to constructor.");
    }
    
    @Test
    void testIsCollectable() {
        assertTrue(key.isCollectable(), "Collectable objects should return true.");
        assertFalse(sink.isCollectable(), "Not collectable objects should return false.");
    }
    
    /*
    Test actions
    */
    @Test
    void setDescriptionUpdatesDescription() {
        key.setDescription("A bent piece of metal, useless.");
        assertEquals("A bent piece of metal, useless.", key.getDescription(),
                "setDescription should change description variable.");
    }
}
