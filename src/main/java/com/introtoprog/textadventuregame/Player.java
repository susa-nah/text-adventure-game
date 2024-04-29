/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;
import java.util.ArrayList;
/**
 *
 * @author susan
 */
public class Player {
    private ArrayList<Item> inventory;
    
    public Player() {
        this.inventory = new ArrayList<>();
    }
    
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    
    public void addToInventory(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }
    
    public void dropItem(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
        }
    }
    
    @Override
    public String toString() {
        // Convert ArrayList to String[]
        String[] itemList = new String[inventory.size()];
        for (int i = 0; i < inventory.size(); i++) {
            itemList[i] = inventory.get(i).getName();
        }
        
        // Join items in list and retun
        return String.join("\n", itemList);
    }
}
