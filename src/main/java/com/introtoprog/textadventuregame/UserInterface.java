/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.introtoprog.textadventuregame;

/**
 *
 * @author susan
 */
import java.util.Scanner;
public class UserInterface {
    private Scanner in;
    
    public UserInterface() {
        in = new Scanner(System.in);
    }
    
    public String parseCommand(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }
        
        // Normalise input
        input = input.toUpperCase();
        
        // Conditions for leading text and length.
        if (input.startsWith("LOOK INSIDE")) {
            return "LOOK INSIDE";
        } else if (input.startsWith("GO ")) { 
            input = input.replace("GO ","");
        } else if (input.length() > 1) {
            input = input.substring(0, input.indexOf(" "));
        }
        
        // Checks if command might be abbreviated to single char 
        // and translates or returns input.
        if (input.length() == 1) {
            switch(input) {
            case "X":
                return "EXAMINE";
            case "N":
                return "NORTH";
            case "S":
                return "SOUTH";
            case "E":
                return "EAST";
            case "W":
                return "WEST";
            case "L":
                return "LOOK";
            default:
                return input;
        }
        } else {
            return input;
        }
    }
}
