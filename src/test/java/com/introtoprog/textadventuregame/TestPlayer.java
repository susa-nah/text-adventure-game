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
public class TestPlayer {
    
    private Player player = new Player();
    private Item key = new Item("Key", "A small silver key.", true);
    private Item towl = new Item("Towl", "A fluffy, clean towl.", true);
    
    // Test constructor
    @Test
    void inventoryIsInitialisedEmpty() {
        assertEquals(0, player.getInventory().size());
    }
    
    // Test add and remove method.
    
    @Test
    void itemIsOnlyAddedOnce() {
        player.addToInventory(key);
        player.addToInventory(key);
        assertEquals(1, player.getInventory().size());
    }
    
    @Test
    void removeItemNotInListDoesNothing() {
        player.addToInventory(towl);
        player.dropItem(key);
        assertEquals(1, player.getInventory().size());
    }
    
    @Test
    void removeItemFromListRemovesItem() {
        player.addToInventory(towl);
        player.dropItem(towl);
        assertEquals(0, player.getInventory().size());
    }
    
    // Test toString method
    @Test
    void testReturnItemsInInvetoryAsString() {
        player.addToInventory(key);
        player.addToInventory(towl);
        String expected = "Key\nTowl";
        assertEquals(expected, player.toString());
    }
}
