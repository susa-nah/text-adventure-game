/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.introtoprog.textadventuregame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
/**
 *
 * @author susan
 */
public class TestRoom {
    private Room bath = new Room("Bath", "A digny bathroom.");
    
    /*
    Test object constructor.
    */
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
    
    @Test
    void defaultConstructorIsUnlocked() {
        assertFalse(bath.isLocked(), "Not passing bool to locked should be unlocked.");
    }
    
    @Test
    void testNavigationDevaultsToNeg1() {
        int[] nav = bath.getNavigation();
        int[] expected = new int[]{-1, -1, -1, -1};
        assertTrue(Arrays.equals(expected, nav), 
        "Navigation should default to -1 for all values.");
        assertEquals(4, nav.length, "Navigation should contain 4 items.");
    }
    
    /*
    Test navigation initalisation
    */
    @Test
    void testSetNavigationCoords() {
        bath.setNavigation(0, 1, 2, 3);
        int[] expected = new int[]{0, 1, 2, 3};
        assertTrue(Arrays.equals(bath.getNavigation(), expected), 
                "Navigation should be set consistent with input.");
    }
    
    /*
    Test adding objects to list.
    */
    Item towl = new Item("Towl", "A fluffy, clean towl.", true);
    Item key = new Item("Key", "A small silver key.", true);
    
    @Test
    void addTowlToVisibleItems() {
        bath.addVisibleObject(towl);
        assertTrue(bath.contains(towl), "Add method should add object.");
        assertTrue(bath.isVisible(towl), "Add method to visible should create visible object.");
        assertEquals(1, bath.getVisibleObjects().size(), 
                "Adding 1 item should return list of size 1.");
    }
    
    @Test
    void addKeyToHiddenItems() {
        bath.addHiddenObject(key);
        assertTrue(bath.contains(key), "Add method should add object.");
        assertTrue(bath.isHidden(key), "Add method to hidden should create hidden object.");
        assertEquals(1, bath.getHiddenObjects().size(), 
                "Adding 1 item should return list of size 1.");
    }
    
    @Test
    void revealObject() {
        bath.addHiddenObject(key);
        bath.revealObject(key);
        assertTrue(bath.isVisible(key), "Reveal method should make object visible.");
    }
    
    @Test
    void hideObject() {
        bath.addVisibleObject(towl);
        bath.hideObject(towl);
        assertTrue(bath.isHidden(towl), "Hide method should make object hidden.");
    }
    
    /*
    Test handling of immovable objects.
    */
    Item sink = new Item("Sink", "A oval basin with taps.", false);
    
    @Test
    void cantRemoveUncollectableObjects() {
        bath.addVisibleObject(sink);
        assertFalse(bath.removeObject(sink), "Should not be able to remove immovable object.");
        assertTrue(bath.contains(sink), "Immovable object should remain in room.");
    }
    
    @Test
    void cantRemoveHiddenObject() {
        bath.addHiddenObject(key);
        assertFalse(bath.removeObject(key), "Hidden objects should not be removed.");
        assertTrue(bath.contains(key), "Hidden object should remain in room.");
    }
    
    @Test
    void canRemoveVisibleCollectable() {
        bath.addVisibleObject(towl);
        assertTrue(bath.removeObject(towl), "Visible objects should be removed.");
        assertFalse(bath.contains(towl), "Visible objects should not be in room.");
    }
    
    /*
    Test locking functionality.
    */
    Room closet = new Room("Closet", "A small square room.", true);
    @Test
    void testCreatingLockedRoom() {
        assertTrue(closet.isLocked(), "Room created with true should be locked.");
    }
    
    @Test
    void testUnlockLockedRoom() {
        closet.unlock();
        assertFalse(closet.isLocked(), "Unlocked room should be unlocked.");
    }
    
    @Test
    void testLockRoom() {
        bath.lock();
        assertTrue(bath.isLocked(), "Locked room should be locked.");
    }
}
