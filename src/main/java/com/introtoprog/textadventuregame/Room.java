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
    private ArrayList<Item> visibleObjects;
    private ArrayList<Item> hiddenObjects;
    private boolean locked;
    
    public Room(String name, String description, boolean locked) {
        this.name = name;
        this.description = description;
        this.visibleObjects = new ArrayList<>();
        this.hiddenObjects = new ArrayList<>();
        this.locked = locked;
    }
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
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
    Inventory management methods
    */
    
    public void addObject(Item item) {
        visibleObjects.add(item);
    }
    
    public ArrayList getObjects() {
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
        return hiddenObjects.contains(item) || visibleObjects.contains(item);
    }
    
    public boolean hasObjectNamed(String name) {
        for (Item item: visibleObjects) {
            if (item.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isHidden(Item item) {
        return hiddenObjects.contains(item);
    }
    
    public boolean isVisible(Item item) {
        return visibleObjects.contains(item);
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
    public String toString() {
        return name + "\n" + description;
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
