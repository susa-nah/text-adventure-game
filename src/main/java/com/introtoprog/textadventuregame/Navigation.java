/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

/**
 *
 * @author susan
 */
public class Navigation {
    private int north;
    private int south;
    private int east;
    private int west;
    
    // default contstructor
    public Navigation() {
        this.north = -1;
        this.south = -1;
        this.east = -1;
        this.west = -1;
    }
    
    // complete constructor
    public Navigation(int n, int s, int e, int w) {
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
    }
    
    // Set navigation
    public void setNavigation(int n, int s, int e, int w) {
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
    }
    
    // return array of elements;
    public int[] getNavigation() {
        int[] nav = new int[]{north, south, east, west};
        return nav;
    }
    
    // Add value to list at first null location
    public String[] addListItem(int length, String[] array, String toAdd) {
        for (int i = 0; i < length; i++) {
            if (array[i] == null) {
                array[i] = toAdd;
                return array;
            }
        }
        return array;
    }
    
    @Override
    public String toString() {
        // Initialise variables
        int countNeg1 = 0;
        int arrayLength;
        String[] nav;
        
        int[] current = getNavigation();
        
        // figure out how many blank cells.
        for (int item: current) {
            if (item < 0) {
                countNeg1++;
            }
        }
        arrayLength = 4 - countNeg1;
        
        // Empty navigation shouldn't happen so return null.
        if (arrayLength == 0) {
            return null;
        }
        
        // Set final array length
        nav = new String[arrayLength];
        
        // Check the cardinal directions and add to list if not -1.
        if (north >= 0) {
            nav = addListItem(arrayLength, nav, "North");
        }
        if (south >= 0) {
            nav = addListItem(arrayLength, nav, "South");
        }
        if (east >= 0) {
            nav = addListItem(arrayLength, nav, "East");
        }
        if (west >= 0) {
            nav = addListItem(arrayLength, nav, "West");
        }
        
        return String.join(", ", nav);
    }
    
}
