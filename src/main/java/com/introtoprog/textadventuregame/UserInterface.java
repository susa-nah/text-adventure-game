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
    
    /*
    Figure out where the command ends and the object name begins
    */
    public int getSplitIndex(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return -1;
        }
        
        // Normalise input
        input = input.toUpperCase();
        
        // Conditions for leading text and length.
        if (input.startsWith("LOOK INSIDE")) {
            return "LOOK INSIDE ".length()-1;
        } else if (input.startsWith("GO ")) { 
            String minusGo = input.replace("GO ","");
            if (minusGo.length() <=5) {
                return input.length();
            } else if (minusGo.contains(" ")) {
                return minusGo.indexOf(" ") + 3;
            } else {
                return -1; // Not sure what to do here.
            }
        } else if (input.length() > 1) {
            return input.indexOf(" ");
        } else {
            return 0;
        }
    }
    
    /*
    Uses the split index to parse the command into standard forms.
    */
    public String parseCommand(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }
        
        // Normalise input
        input = input.toUpperCase();
        
        // Use getSplitIndex() to determine start of command.
        int splitIndex = getSplitIndex(input);
        
        if (splitIndex > 0) {
            input = input.substring(0,splitIndex);
        }
        
        // Handle Go direction commands
        if (input.startsWith("GO ")) {
            input = input.replace("GO ","");
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
            case "H":
                return "HELP";
            default:
                return input;
        }
        } else {
            return input;
        }
    }
     
    /*
    Cleans object part of string for comparison
    */
    public String parseObject(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }
        
        // Get split index
        int splitIndex = getSplitIndex(input);
        
        
        // Return null for command only entries.
        if (splitIndex == input.length() || splitIndex < 1) {
            return null;
        }
        
        // Normalise to uppercase.
        input = input.toUpperCase();
        
        // If cardinal direction, return null;
        if (splitIndex == 1 && 
                !(input.startsWith("X")||input.startsWith("L"))) {
            return null;
        }
        
        // Get substring and trim whitespace
        input = input.substring(splitIndex).trim();
        
        // Get rid of common leading text
        String[] nonfiling = new String[] {"THE ","A ", "AN ","THAT ","THIS ",
            "INSIDE ", "AT "};
        for (String text : nonfiling) {
            if (input.startsWith(text)) {
                input = input.replace(text, "");
            }
        }
        
        return input;
        
    }
}
