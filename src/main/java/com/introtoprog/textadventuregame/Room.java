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
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        visibleObjects = new ArrayList<>();
        hiddenObjects = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
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
