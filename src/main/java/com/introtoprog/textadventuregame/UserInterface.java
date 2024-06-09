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
import java.util.ArrayList;
import java.util.Collections;
public class UserInterface {
    private Scanner in;
    private ArrayList<String> validCommands;
    private CommandParser parser;
    
    public UserInterface() {
        this.in = new Scanner(System.in);
        this.validCommands = new ArrayList<>();
        Collections.addAll(validCommands, "EXAMINE", "LOOK", "LOOK INSIDE", 
                "GET","DROP","TALK","NORTH","SOUTH","EAST","WEST","HELP");
        Collections.sort(validCommands);
        this.parser = new CommandParser(
                new String[] { "GO", "SHOW", "PRINT", "DISPLAY" },
		new String[] { "LOOK INSIDE", "PICK UP", "PUT DOWN"});
    }
    
    /*
    Uses the split index to parse the command into standard forms.
    */
    public String tokenizeCommand(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
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
    
    public boolean isValidCommand(String command) {
        return validCommands.contains(command);
    }
    
    public void helpMenu() {
        System.out.println("This game is designed to recieve input in the form 'VERB' 'NOUN'. "
                + "Common commands include LOOK, EXAMINE, GET, DROP, LOOK, TALK, as well as the cardinal directions. "
                + "Other commands may be possible within a specific context. Pay attention to the text for clues.");
    }
    
    public void lookAction(Room room) {
        System.out.println(room);
    }
    
    public void lookAction(Item item) {
        System.out.println(item.getDescription());
    }
}
