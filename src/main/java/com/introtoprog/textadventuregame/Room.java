/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

/**
 *
 * @author susan
 */
import java.util.ArrayList;
import java.util.Objects;
public class Room {
    private String name;
    private String description;
    private int[] navNSEW;
    private ArrayList<Item> visibleObjects;
    private ArrayList<Item> hiddenObjects;
    private boolean locked;
    
    public Room(String name, String description, boolean locked) {
        this.name = name;
        this.description = description;
        this.navNSEW = new int[] {-1, -1, -1, -1};
        this.visibleObjects = new ArrayList<>();
        this.hiddenObjects = new ArrayList<>();
        this.locked = locked;
    }
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.navNSEW = new int[] {-1, -1, -1, -1};
        this.visibleObjects = new ArrayList<>();
        this.hiddenObjects = new ArrayList<>();
        this.locked = false;
    }
    
    /*
    Identity methods.
    */
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    /*
    Navigation methods.
    */
    
    public int[] getNavigation() {
        return navNSEW;
    }
    
    public void setNavigation(int north, int south, int east, int west) {
        this.navNSEW[0] = north;
        this.navNSEW[1] = south;
        this.navNSEW[2] = east;
        this.navNSEW[3] = west;
    }
    
    /*
    Inventory management methods
    */
    
    public void addVisibleObject(Item item) {
        visibleObjects.add(item);
    }
    
    public ArrayList getVisibleObjects() {
        return visibleObjects;
    }
    
    public void addHiddenObject(Item item) {
        hiddenObjects.add(item);
    }
    
    public ArrayList getHiddenObjects() {
        return hiddenObjects;
    }
    
    public void hideObject(Item item) {
        // Check item in visible objects
        if (visibleObjects.contains(item)) {
            visibleObjects.remove(item);
            hiddenObjects.add(item);
        }
    }
    
    public void revealObject(Item item) {
        // Check item is in hidden objects
        if (hiddenObjects.contains(item)) {
            hiddenObjects.remove(item);
            visibleObjects.add(item);
        }
    }
    
    public boolean contains(Item item) {
        if (hiddenObjects.contains(item) || visibleObjects.contains(item)) {
            return true;
        }
        return false;
    }
    
    public boolean isHidden(Item item) {
        if (hiddenObjects.contains(item)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isVisible(Item item) {
        if (visibleObjects.contains(item)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removeObject(Item item) {
        if (visibleObjects.contains(item) && item.isCollectable()) {
            visibleObjects.remove(item);
            return true;
        } else {
            return false;
        }
    }
    
    /*
    Lock roome methods.
    */
    
    public boolean isLocked() {
        return locked;
    }
    
    public void lock() {
        this.locked = true;
    }
    
    public void unlock() {
        this.locked = false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.visibleObjects);
        hash = 97 * hash + Objects.hashCode(this.hiddenObjects);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.visibleObjects, other.visibleObjects)) {
            return false;
        }
        return Objects.equals(this.hiddenObjects, other.hiddenObjects);
    }
}
