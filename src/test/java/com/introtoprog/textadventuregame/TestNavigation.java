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
public class TestNavigation {
    
    Navigation allBlank = new Navigation();
    Navigation onlyWest = new Navigation(-1, -1, -1, 2);
    Navigation northThenEast = new Navigation(2, -1, 0, -1);
    Navigation allValues = new Navigation(3, 2, 1, 0);
    
    /*
    To string methods
    */
    @Test
    void testBlankNavigationReturnsNull() {
        assertEquals(null, allBlank.toString(), "Empty constructors should return null.");
    }
    
    @Test
    void testListOnlyContainsWest() {
        assertEquals("West", onlyWest.toString());
    }
    
    @Test
    void testCaseHalfPopulated() {
        assertEquals("North, East", northThenEast.toString());
    }
    
    @Test
    void allValuesPopulated() {
        assertEquals("North, South, East, West", allValues.toString());
    }
}
