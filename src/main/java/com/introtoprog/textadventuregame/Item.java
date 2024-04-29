/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

import java.util.Objects;

/**
 *
 * @author susan
 */
public class Item {
    protected String name;
    protected String description;
    protected boolean highlight;
    protected boolean collectable;
    
    public Item(String name, String description, boolean collectable, boolean highlight) {
        this.name = name;
        this.description = description;
        this.collectable = collectable;
        this.highlight = highlight;
    }
    
    public Item(String name, String description, boolean collectable) {
        this.name = name;
        this.description = description;
        this.collectable = collectable;
        this.highlight = false;
    }
    
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.collectable = false;
        this.highlight = false;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCollectable() {
        return collectable;
    }
    
    public void examine() {
        System.out.println(description);
    }
    
    public void setDescription(String info) {
        this.description = info;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + (this.collectable ? 1 : 0);
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
        final Item other = (Item) obj;
        if (this.collectable != other.collectable) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
